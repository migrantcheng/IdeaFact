package controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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

public class ViewAccountAction extends Action {
	
	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	private TransactionDAO transactionDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private PositionDAO positionDAO;

	private DecimalFormat dfNumberCash = new DecimalFormat("#,##0.00");
	private DecimalFormat dfNumberFund = new DecimalFormat("#,##0.000");
	private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

	private double available;
	private double cash;
	
	private String stringAvailable;
	private String stringCash;
	
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
	
	public ViewAccountAction(Model model){
		customerDAO = model.getCustomerDAO();
		fundDAO = model.getFundDAO();
		transactionDAO = model.getTransactionDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		positionDAO = model.getPositionDAO();
	}

	@Override
	public String getName() {
		return "myaccount.do";
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
        	
	        // Calculate and store cash and available balance
	        cash = (double)customer.getCash() / 100;
	        stringCash = dfNumberCash.format(cash);
	        available = (double)customer.getAvailable() / 100;
	        stringAvailable = dfNumberCash.format(available);
	        request.setAttribute("stringCash",stringCash);
	        request.setAttribute("stringAvailable",stringAvailable);
	        
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
	        	// calculate latest price to be total value
	        	String tempValue = null;
	        	if (tempLatestPrice <= 0) {
	        		tempValue = "-";
	        	} else {
	        		tempValue = dfNumberCash.format((double)(tempLatestPrice*tempPosition.getShares())/100000);
	        	}
	        	if (tempPosition.getShares() > 0) {
	        		positionList.add(new PositionList(dfNumberFund.format((double)tempPosition.getShares()/1000),tempFund,tempValue));
	        	}
	        }
	        
	        request.setAttribute("positionList", positionList);
	        
	        // get last transaction day
	        Transaction lastTransaction = transactionDAO.getLastTransaction(customer.getCustomer_id());
	        String lastTransactionDate = "N/A";
	        if (lastTransaction != null && lastTransaction.getExecute_date() != null) {
	        	lastTransactionDate = sdf.format(lastTransaction.getExecute_date());
	        }
	        request.setAttribute("lastTransactionDate", lastTransactionDate);
	        
	        return "viewAccount.jsp";
        }catch (Exception e) {
        	errors.add(e.getMessage());
			return "viewAccount.jsp";
		}
	}

}
