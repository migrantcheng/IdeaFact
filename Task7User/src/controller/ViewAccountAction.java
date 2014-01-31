package controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import controller.TransactionHistoryAction.TransactionList;
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
	
	public class TransactionList {
    	public double unitPrice;
    	public Transaction transaction;
    	
    	public String operation;
    	public String stringShares;
    	public String stringDate;
    	public Fund fund;
    	public String stringAmount;
    	public String stringUnitPrice;
    	
    	public TransactionList(Transaction transaction, Fund fund) {
    		this.transaction = transaction;
    		this.fund = fund;
    		String transactionType = transaction.getTransaction_type();
    		if (transactionType.equals("BUY")) {
    			operation = "Buy";
    		} else if (transactionType.equals("SELL")) {
    			operation = "Sell";
    		} else if (transactionType.equals("DEPOSIT")) {
    			operation = "Deposit Check";
    		} else if (transactionType.equals("WITHDRAW")) {
    			operation = "Request Check";
    		} else {
    			operation = "N/A";
    		}
    		
    		if (transaction.getShares() <= 0) {
    			stringShares = "-";
    		} else {
    			stringShares = dfNumberFund.format((double)transaction.getShares()/1000);
    		}
    		
    		if (transaction.getExecute_date() == null) {
    			stringDate = "Pending";
    		} else {
    			stringDate = sdf.format(transaction.getExecute_date());
    		}
    		
    		if (transaction.getAmount() <= 0) {
    			stringAmount = "-";
    		} else {
    			stringAmount = "$" + dfNumberCash.format((double)transaction.getAmount()/100);
    		}
    		
    		if (transaction.getAmount() <= 0 || transaction.getShares() <= 0) {
    			stringUnitPrice = "-";
    		} else {
	    		unitPrice = (double)transaction.getShareprice()/100;
	    		stringUnitPrice = "$" + dfNumberCash.format(unitPrice);
    		}
    	}

    	public String getOperation() {
    		return operation;
    	}

    	public String getStringShares() {
    		return stringShares;
    	}
    	
    	public String getStringDate() {
    		return stringDate;
    	}
    	
    	public Fund getFund() {
    		return fund;
    	}
    	
    	public String getStringAmount() {
    		return stringAmount;
    	}
    	
    	public String getStringUnitPrice() {
    		return stringUnitPrice;
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
        	String messages = (String) request.getSession().getAttribute("messages");
        	request.setAttribute("messages", messages);
        	request.getSession().setAttribute("messages", null);
            Customer customer = (Customer) request.getSession().getAttribute("customer");
            synchronized (customerDAO) {
            	customer = customerDAO.read(customer.getUsername());
            }
        	request.getSession().setAttribute("customer", customer);
        	
	        // Calculate and store cash and available balance
	        cash = (double)customer.getCash() / 100;
	        stringCash = dfNumberCash.format(cash);
	        available = (double)customer.getAvailable() / 100;
	        stringAvailable = dfNumberCash.format(available);
	        request.setAttribute("stringCash",stringCash);
	        request.setAttribute("stringAvailable",stringAvailable);
	        
	        // get position list from database
	        List<Position> positions;
	        synchronized (positionDAO) {
	        	positions = positionDAO.getAllPositions(customer.getCustomer_id());
	        }
	        
	        
	        // store all position information along with other information;
	        ArrayList<PositionList> positionList = new ArrayList<PositionList>();
	        java.util.Iterator<Position> iter = positions.iterator();
	        Position tempPosition;
	        Fund tempFund;
	        long tempLatestPrice;
	        while (iter.hasNext()) {
	        	tempPosition = iter.next();
	        	synchronized (fundDAO) {
	        		tempFund = fundDAO.read(tempPosition.getFund_id());
	        	}
	        	synchronized (fundPriceHistoryDAO) {
	        		tempLatestPrice = fundPriceHistoryDAO.getLatestPrice(tempPosition.getFund_id());
	        	}
	        	// calculate latest price to be total value
	        	String tempValue = null;
	        	if (tempLatestPrice < 0) {
	        		tempValue = "-";
	        	} else {
	        		tempValue = dfNumberCash.format((double)(tempLatestPrice*tempPosition.getShares())/100000);
	        	}
	        	if (tempPosition.getShares() > 0) {
	        		positionList.add(new PositionList(dfNumberFund.format((double)tempPosition.getShares()/1000),tempFund,tempValue));
	        	}
	        }
	        
	        request.setAttribute("positionList", positionList);
	        
	     // get transaction history list from database
	        List<Transaction> transactions;
	        synchronized (transactionDAO) {
	        	transactions = transactionDAO.getPending(customer.getCustomer_id());
	        }
	        
	        // store all position information along with other information;
	        ArrayList<TransactionList> transactionList = new ArrayList<TransactionList>();
	        java.util.Iterator<Transaction> iter2 = transactions.iterator();
	        Transaction tempTransaction;
	        Fund tempFund2 = null;
	        while (iter2.hasNext()) {
	        	tempTransaction = iter2.next();
	        	synchronized (fundDAO) {
	        		tempFund2 = fundDAO.read(tempTransaction.getFund_id());
	        	}
	        	// store transaction and fund pairs in list
	        	transactionList.add(new TransactionList(tempTransaction,tempFund2));
	        }
	        
	        request.setAttribute("transactionList", transactionList);
	        
	        // get last transaction day
	        Transaction lastTransaction;
	        synchronized (transactionDAO) {
	        	lastTransaction = transactionDAO.getLastTransaction(customer.getCustomer_id());
	        }
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
