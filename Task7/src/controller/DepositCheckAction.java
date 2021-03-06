package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.Model;
import dao.interfaces.CustomerDAO;
import dao.interfaces.TransactionDAO;
import databean.Customer;
import databean.Fund;
import databean.Transaction;
import formbean.CreateFundForm;
import formbean.DepositCheckForm;

public class DepositCheckAction extends Action {
	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;
	private DecimalFormat dfNumberCash = new DecimalFormat("#,##0.00");
	
	public DepositCheckAction(Model model){
		transactionDAO = model.getTransactionDAO();
		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {
		return "depositCheck.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// Set up error list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
	        // Load the form parameters into a form bean
	        DepositCheckForm form = new DepositCheckForm(request);

        	request.setAttribute("username",request.getParameter("customer_username"));
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "depositCheck.jsp";
	        }
	
	        // Check for any validation errors
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	        	return "depositCheck.jsp";
	        }
	
	        //get customer from db
	        Customer customer;
	        synchronized(customerDAO){
	        customer = customerDAO.read(form.getUsername());
	        }
	        if(customer==null){
	        	//if not found, return error.jsp
	        	errors.add("Username not found");
	        	return "depositCheck.jsp";
	        }
	        
	        Transaction transaction = new Transaction();
	        transaction.setAmount(form.getAmount());
	        transaction.setCustomer_id(customer.getCustomer_id());
	        transaction.setTransaction_type(Transaction.DEPOSIT);
	        transaction.setFund_id(-1);
	        transaction.setShares(-1);
	        transaction.setExecute_date(null);
	        
	        synchronized(transactionDAO){
	        transactionDAO.create(transaction);
	        }
	        
	        //update customer's cash, but not update available until transaction day
	        
	        synchronized(customerDAO){
	        	customer = customerDAO.read(form.getUsername());
	        	customer.setCash(customer.getCash() + form.getAmount());
	        	customerDAO.update(customer);
	        }
	
//			request.setAttribute("messages","Deposited Check amount : "+form.getAmount()/100+" for customer: "+form.getUsername());
			
			List<String> messages = new ArrayList<String>();
			messages.add("Deposited Check amount : "+dfNumberCash.format(((double)form.getAmount())/100)+" for customer: "+form.getUsername());
			request.setAttribute("messages",messages);
			return "depositCheck.jsp";
        }catch (Exception e) {
        	errors.add(e.getMessage());
        	return "depositCheck.jsp";
		}
	}

}
