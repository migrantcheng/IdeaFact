package controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.Model;
import dao.interfaces.FundDAO;
import dao.interfaces.TransactionDAO;
import databean.Customer;
import databean.Fund;
import databean.Transaction;

public class TransactionHistoryAction extends Action {
	
	private FundDAO fundDAO;
	private TransactionDAO transactionDAO;

	private DecimalFormat dfNumberCash = new DecimalFormat("#,##0.00");
	private DecimalFormat dfNumberFund = new DecimalFormat("#,##0.000");
	private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

	public class TransactionList {
    	public double unitPrice;
    	public Transaction transaction;
    	
    	public String operation;
    	public String stringShares;
    	public String stringDate;
    	public Fund fund;
    	public String fundName;
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
    		
    		if (fund != null) {
    			fundName = fund.getName();
    		} else {
    			fundName = "-";
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
    	
    	public String getFundName() {
    		return fundName;
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
	
	public TransactionHistoryAction(Model model){
		fundDAO = model.getFundDAO();
		transactionDAO = model.getTransactionDAO();
	}

	@Override
	public String getName() {
		return "history.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// Set up error list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        

        try {
            Customer customer = (Customer) request.getSession().getAttribute("customer");
        	
	        // get transaction history list from database
            List<Transaction> transactions;
            synchronized (transactionDAO) {
            	transactions = transactionDAO.getAll(customer.getCustomer_id());
	        }
	        
	        // store all position information along with other information;
	        ArrayList<TransactionList> transactionList = new ArrayList<TransactionList>();
	        java.util.Iterator<Transaction> iter = transactions.iterator();
	        Transaction tempTransaction;
	        Fund tempFund;
	        while (iter.hasNext()) {
	        	tempTransaction = iter.next();
	        	synchronized (fundDAO) {
	        		tempFund = fundDAO.read(tempTransaction.getFund_id());
	        	}
	        	// store transaction and fund pairs in list
	        	transactionList.add(new TransactionList(tempTransaction,tempFund));
	        }
	        
	        request.setAttribute("transactionList", transactionList);
	        
	        
	        return "transactionHistory.jsp";
        }catch (Exception e) {
        	errors.add(e.getMessage());
			return "transactionHistory.jsp";
		}
	}

}
