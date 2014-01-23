package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.Model;
import dao.interfaces.CustomerDAO;
import dao.interfaces.FundDAO;
import dao.interfaces.FundPriceHistoryDAO;
import dao.interfaces.PositionDAO;
import dao.interfaces.TransactionDAO;
import databean.Customer;
import databean.Fund;
import databean.Position;
import databean.Transaction;
import formbean.SellFundFormBean;

public class SellFundAction extends Action {
	
	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	private TransactionDAO transactionDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private PositionDAO positionDAO;

	private DecimalFormat dfNumberCash = new DecimalFormat("#,##0.00");
	private DecimalFormat dfNumberFund = new DecimalFormat("#,##0.000");

	private double available;
	private double amount;
	
	private String stringAvailable;
	private String stringAmount;
	
	public static class PositionList {
    	public String shares;
    	public Fund fund;
    	public String latestPrice;
    	
    	public PositionList(String shares, Fund fund, String latestPrice) {
    		this.shares = shares;
    		this.fund = fund;
    		this.latestPrice = latestPrice;
    	}
    	
    	public String getShares() {
    		return shares;
    	}
    	
    	public Fund getFund() {
    		return fund;
    	}
    	
    	public String getLatestPrice() {
    		return latestPrice;
    	}
    }
	
	public SellFundAction(Model model){
		customerDAO = model.getCustomerDAO();
		fundDAO = model.getFundDAO();
		transactionDAO = model.getTransactionDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		positionDAO = model.getPositionDAO();
	}

	@Override
	public String getName() {
		return "sell.do";
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
        	
	        // Load the form parameters into a form bean
	        SellFundFormBean form = new SellFundFormBean(request);
	        request.setAttribute("form", form);
	        
	        amount = (double)form.getAmount() / 1000;
	        stringAmount = dfNumberFund.format(amount);
	        request.setAttribute("stringAmount",stringAmount);
	        
	        // get position list from database
	        List<Position> positions = positionDAO.getAllPositions(customer.getCustomer_id());
	        
	        
	        // store all position information along with other information;
	        ArrayList<PositionList> positionList = new ArrayList<PositionList>();
	        java.util.Iterator<Position> iter = positions.iterator();
	        Position tempPosition;
	        Fund tempFund;
	        long tempLatestPrice;
	        while (iter.hasNext()) {
	        	tempPosition = iter.next();
	        	tempFund = fundDAO.read(tempPosition.getFund_id());
	        	tempLatestPrice = fundPriceHistoryDAO.getLatestPrice(tempPosition.getFund_id());
	        	String latestPrice;
	        	if (tempLatestPrice == -1) {
	        		latestPrice = "-";
	        	} else {
	        		latestPrice = dfNumberCash.format((double)tempLatestPrice / 100);
	        	}
	        	if (tempPosition.getShares() > 0) {
	        		positionList.add(new PositionList(dfNumberFund.format((double)tempPosition.getShares()/1000),tempFund,latestPrice));
	        	}
	        }
	        
	        request.setAttribute("positionList", positionList);
	        
	        
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "sell.jsp";
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
	        		latestPrice = "-";
	        	} else {
	        		latestPrice = dfNumberCash.format((double)price / 100);
	        	}
	        	request.setAttribute("latestPrice", latestPrice);
	        }
	        
	        if (errors.size() != 0) {
		        if (form.getButton().equals("query")) {
		        	return "sell.jsp";
		        }
	        }
	        Position position = positionDAO.read(fund.getFund_id(), customer.getCustomer_id());
	        available = (double)(position.getShares()) / 1000;
	        stringAvailable = dfNumberFund.format(available);
	        request.setAttribute("stringAvailable",stringAvailable);
	        request.setAttribute("position",position);
	        
	        if (form.getButton().equals("query")) {
	        	return "sellNext.jsp";
	        }
	        
	        
        	if (form.getButton().equals("next") || form.getButton().equals("buy")) {
        		//check for balance
        		if (form.getAmount() > (position.getShares())) {
        			errors.add("Not enough shares.");
        		}
        	}
	        
	        if (errors.size() != 0) {
		        if (form.getButton().equals("query")) {
		        	return "sell.jsp";
		        } else if (form.getButton().equals("next")) {
		        	return "sellNext.jsp";
		        } else if (form.getButton().equals("buy")) {
		        	return "sellNext.jsp";
		        } else {
		        	return "sell.jsp";
		        }
	        }
	        
	        if (form.getButton().equals("next")) {
	        	return "sellConfirm.jsp";
	        }
	
	        //deduct balance
	        errors.addAll(positionDAO.spend(position,form.getAmount()));
	        
	        //return errors if balance is not enough
	        if (errors.size() != 0) {
	        	return "sellNext.jsp";
	        }
	        
	        //add transaction to queue
			Transaction transaction = new Transaction();
			transaction.setAmount(0);
			transaction.setCustomer_id(((Customer)request.getSession().getAttribute("customer")).getCustomer_id());
			transaction.setFund_id(fund.getFund_id());
			transaction.setExecute_date(null);
			transaction.setTransaction_type("SELL");
			transaction.setShares((int)form.getAmount());
			transactionDAO.create(transaction);
			
			request.setAttribute("messages","Your transaction for selling " + fund.getName() + " has been successfully placed.");
			
			//renew position list
			positions = positionDAO.getAllPositions(customer.getCustomer_id());
	        
	        
	        // store all position information along with other information;
	        positionList = new ArrayList<PositionList>();
	        iter = positions.iterator();
	        while (iter.hasNext()) {
	        	tempPosition = iter.next();
	        	tempFund = fundDAO.read(tempPosition.getFund_id());
	        	tempLatestPrice = fundPriceHistoryDAO.getLatestPrice(tempPosition.getFund_id());
	        	String latestPrice;
	        	if (tempLatestPrice == -1) {
	        		latestPrice = "-";
	        	} else {
	        		latestPrice = dfNumberCash.format((double)tempLatestPrice / 100);
	        	}
	        	if (tempPosition.getShares() > 0) {
	        		positionList.add(new PositionList(dfNumberFund.format((double)tempPosition.getShares()/1000),tempFund,latestPrice));
	        	}
	        }
	        
	        request.setAttribute("positionList", positionList);
	        return "sell.jsp";
        }catch (Exception e) {
        	errors.add(e.getMessage());
			return "sell.jsp";
		}
	}

}
