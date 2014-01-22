package controller;

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
	        // Load the form parameters into a form bean
	        BuyFundFormBean form = new BuyFundFormBean(request);
	        
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
	        		latestPrice = Double.toString(((double)price) / 1000);
	        	}
	        	request.setAttribute("latestPrice", latestPrice);
	        }
	        
	        if (form.isPresent()) {
	        	//update customer session
	        	Customer customer = (Customer) request.getSession().getAttribute("customer");
	        	customer = customerDAO.read(customer.getUsername());
	        	request.getSession().setAttribute("customer", customer);
	        	if (form.getButton().equals("next") || form.getButton().equals("buy")) {
	        		//check for balance
	        		if (form.getAmount() > customer.getAvailable()) {
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
	
	        //deduct balance
	        errors.addAll(customerDAO.spend((Customer)request.getSession().getAttribute("customer"),form.getAmount()));
	        
	        //return errors if balance is not enough
	        if (errors.size() != 0) {
	        	return "buyNext.jsp";
	        }
	        
	        //add transaction to queue
			Transaction transaction = new Transaction();
	
			request.setAttribute("messages","Your transaction for buying " + fund.getName() + " has been successfully placed.");
	        return "transaction.jsp";
        }catch (Exception e) {
        	errors.add(e.getMessage());
			return "buy.jsp";
		}
	}

}
