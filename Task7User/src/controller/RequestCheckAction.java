package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.Model;
import dao.interfaces.CustomerDAO;
import dao.interfaces.FundDAO;
import dao.interfaces.FundPriceHistoryDAO;
import dao.interfaces.TransactionDAO;
import databean.Customer;
import databean.Fund;
import databean.Transaction;
import formbean.RequestCheckFormBean;

public class RequestCheckAction extends Action {
	
	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	private TransactionDAO transactionDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	private DecimalFormat dfNumberCash = new DecimalFormat("#,##0.00");
	private DecimalFormat dfNumberFund = new DecimalFormat("#,##0.000");

	private double available;
	private double amount;
	
	private String stringAvailable;
	private String stringAmount;
	
	public RequestCheckAction(Model model){
		customerDAO = model.getCustomerDAO();
		fundDAO = model.getFundDAO();
		transactionDAO = model.getTransactionDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}

	@Override
	public String getName() {
		return "check.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// Set up error list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
            Customer customer = (Customer) request.getSession().getAttribute("customer");
        	customer = customerDAO.read(customer.getUsername());
        	request.getSession().setAttribute("customer", customer);
            
            available = (double)((Customer)request.getSession().getAttribute("customer")).getAvailable() / 100;
            stringAvailable = dfNumberCash.format(available);
            request.setAttribute("stringAvailable",stringAvailable);
            
	        // Load the form parameters into a form bean
        	RequestCheckFormBean form = new RequestCheckFormBean(request);
	        request.setAttribute("form", form);
	        
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "requestCheck.jsp";
	        }
	        
	        amount = (double)form.getAmount() / 100;
	        stringAmount = dfNumberCash.format(amount);
	        request.setAttribute("stringAmount",stringAmount);
	        
	        
	        // Check for any validation errors
	        errors.addAll(form.getValidationErrors());
	        
	        
	        if (errors.size() != 0) {
		        if (form.getButton().equals("next")) {
		        	return "requestCheck.jsp";
		        } else if (form.getButton().equals("confirm")) {
		        	return "requestCheckConfirm.jsp";
		        } else {
		        	return "requestCheck.do";
		        }
	        }
	        
	        if (form.isPresent()) {
	        	//update customer session
	        	customer = (Customer) request.getSession().getAttribute("customer");
	        	customer = customerDAO.read(customer.getUsername());
	        	request.getSession().setAttribute("customer", customer);
	        	if (form.getButton().equals("next") || form.getButton().equals("buy")) {
	        		//check for balance
	        		if (form.getAmount() > (customer.getAvailable())) {
	        			errors.add("Insufficient fund.");
	        		}
	        	}
	        }
	        
	        if (errors.size() != 0) {
		        if (form.getButton().equals("next")) {
		        	return "requestCheck.jsp";
		        } else if (form.getButton().equals("confirm")) {
		        	return "requestCheckConfirm.jsp";
		        } else {
		        	return "requestCheck.do";
		        }
	        }
	        
	        if (form.getButton().equals("next")) {
	        	return "requestCheckConfirm.jsp";
	        }
	
	        //deduct balance
	        errors.addAll(customerDAO.spend((Customer)request.getSession().getAttribute("customer"),form.getAmount()));
	        
	        //return errors if balance is not enough
	        if (errors.size() != 0) {
	        	return "requestCheck.jsp";
	        }
	        
	        //add transaction to queue
			Transaction transaction = new Transaction();
			transaction.setAmount(form.getAmount());
			transaction.setCustomer_id(((Customer)request.getSession().getAttribute("customer")).getCustomer_id());
			transaction.setFund_id(-1);
			transaction.setExecute_date(null);
			transaction.setTransaction_type(Transaction.WITHDRAW);
			transaction.setShares(-1);
			transactionDAO.create(transaction);
			
			Customer user = (Customer) request.getSession().getAttribute("customer");
        	user = customerDAO.read(user.getUsername());
        	request.getSession().setAttribute("customer", user);
			
			request.getSession().setAttribute("messages","Your request for a $" + dfNumberCash.format((double)form.getAmount() / 100) + " check has been successfully placed in queue.");
	        return "myaccount.do";
        }catch (Exception e) {
        	errors.add(e.getMessage());
			return "requestCheck.jsp";
		}
	}

}
