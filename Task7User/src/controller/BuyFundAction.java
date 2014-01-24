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
import formbean.BuyFundFormBean;

public class BuyFundAction extends Action {
	
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
	
	public BuyFundAction(Model model){
		customerDAO = model.getCustomerDAO();
		fundDAO = model.getFundDAO();
		transactionDAO = model.getTransactionDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}

	@Override
	public String getName() {
		return "buy.do";
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
	        BuyFundFormBean form = new BuyFundFormBean(request);
	        request.setAttribute("form", form);
	        
	        amount = (double)form.getAmount() / 100;
	        stringAmount = dfNumberCash.format(amount);
	        request.setAttribute("stringAmount",stringAmount);
	        
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "buy.jsp";
	        }
	        
	        // Check for any validation errors
	        errors.addAll(form.getValidationErrors());
	        
	        Fund fund = fundDAO.read(form.getTicker());
	        if (fund == null) {
	        	errors.add("Ticker does not exist.");
	        } else {
	        	request.setAttribute("fund", fund);
	        	
	        	long price = fundPriceHistoryDAO.getLatestPrice(fund.getFund_id());
	        	String latestPrice;
	        	if (price == -1) {
	        		latestPrice = "N/A";
	        	} else {
	        		latestPrice = dfNumberCash.format((double)price / 100);
	        	}
	        	request.setAttribute("latestPrice", latestPrice);
	        }
	        
	        if (errors.size() != 0) {
		        if (form.getButton().equals("query")) {
		        	return "buy.jsp";
		        }
	        }
	        
	        if (form.getButton().equals("query")) {
	        	return "buyNext.jsp";
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
		        if (form.getButton().equals("query")) {
		        	return "buy.jsp";
		        } else if (form.getButton().equals("next")) {
		        	return "buyNext.jsp";
		        } else if (form.getButton().equals("buy")) {
		        	return "buyNext.jsp";
		        } else {
		        	return "buy.jsp";
		        }
	        }
	        
	        if (form.getButton().equals("next")) {
	        	return "buyConfirm.jsp";
	        }
	
	        //deduct balance
	        errors.addAll(customerDAO.spend((Customer)request.getSession().getAttribute("customer"),form.getAmount()));
	        
	        //return errors if balance is not enough
	        if (errors.size() != 0) {
	        	return "buyNext.jsp";
	        }
	        
	        //add transaction to queue
			Transaction transaction = new Transaction();
			transaction.setAmount(form.getAmount());
			transaction.setCustomer_id(((Customer)request.getSession().getAttribute("customer")).getCustomer_id());
			transaction.setFund_id(fund.getFund_id());
			transaction.setExecute_date(null);
			transaction.setTransaction_type("BUY");
			transaction.setShares(0);
			transactionDAO.create(transaction);
			
			Customer user = (Customer) request.getSession().getAttribute("customer");
        	user = customerDAO.read(user.getUsername());
        	request.getSession().setAttribute("customer", user);
			
			request.getSession().setAttribute("messages","Your transaction for buying " + fund.getName() + " has been successfully placed.");
	        return "myaccount.do";
        }catch (Exception e) {
        	errors.add(e.getMessage());
			return "buy.jsp";
		}
	}

}
