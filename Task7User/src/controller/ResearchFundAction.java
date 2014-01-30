package controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.Model;
import dao.interfaces.FundDAO;
import dao.interfaces.FundPriceHistoryDAO;
import databean.Fund;
import databean.FundPriceHistory;

public class ResearchFundAction extends Action {
	
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	private DecimalFormat dfNumberCash = new DecimalFormat("#,##0.00");
	private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
	public static class FundList {
    	public Fund fund;
    	public String latestPrice;
    	
    	public FundList(Fund fund, String latestPrice) {
    		this.fund = fund;
    		this.latestPrice = latestPrice;
    	}
    	
    	public Fund getFund() {
    		return fund;
    	}
    	
    	public String getLatestPrice() {
    		return latestPrice;
    	}
    }
	
	public class FundPriceList {
    	public String date;
    	public String price;
    	public double realPrice;
    	public long timestamp;
    	
    	public FundPriceList(FundPriceHistory fundPriceHistory) {
    		timestamp = fundPriceHistory.getPrice_date().getTime();
    		date = sdf.format(fundPriceHistory.getPrice_date());
    		price = dfNumberCash.format((double)fundPriceHistory.getPrice() / 100);
    		realPrice = (double)fundPriceHistory.getPrice() / 100;
    	}

    	public String getDate() {
    		return date;
    	}
    	
    	public long getTimestamp() {
    		return timestamp;
    	}
    	
    	public double getRealPrice() {
    		return realPrice;
    	}
    	
    	public String getPrice() {
    		return price;
    	}
    }
	
	public ResearchFundAction(Model model){
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}

	@Override
	public String getName() {
		return "research.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// Set up error list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
	        // get Fund list from database
        	List<Fund> funds;
        	synchronized (fundDAO) {
        		funds = fundDAO.getAllFunds();
        	}
	        
	        // store all position information along with other information;
	        ArrayList<FundList> fundList = new ArrayList<FundList>();
	        java.util.Iterator<Fund> iter = funds.iterator();
	        Fund tempFund;
	        long tempLatestPrice;
	        while (iter.hasNext()) {
	        	tempFund = iter.next();
	        	synchronized (fundPriceHistoryDAO) {
	        		tempLatestPrice = fundPriceHistoryDAO.getLatestPrice(tempFund.getFund_id());
	        	}
	        	String tempPrice = null;
	        	if (tempLatestPrice <= 0) {
	        		tempPrice = "-";
	        	} else {
	        		tempPrice = dfNumberCash.format((double)tempLatestPrice/100);
	        	}
	        	fundList.add(new FundList(tempFund,tempPrice));
	        }
	        
	        request.setAttribute("fundList", fundList);

	        if (request.getParameter("id") == null || request.getParameter("id").length() == 0) {
            	return "researchFund.jsp";
        	}
        	
        	// Check for parameter errors
        	int id = 0;
        	try {
        		id = Integer.parseInt(request.getParameter("id"));
        	} catch (Exception e) {
        		errors.add("Invalid parameters.");
        	}
        	
	        
	        if (errors.size() != 0) {
		        return "researchFund.jsp";
	        }
	        
	        // Check to make sure parameter is in the right range.
	        Fund fund;
	        synchronized (fundDAO) {
	        	fund = fundDAO.read(id);
	        }
	        if (fund == null) {
	        	errors.add("Value of parameter is not correct.");
	        }
	        request.setAttribute("fund", fund);
	        
	        if (errors.size() != 0) {
		        return "researchFund.jsp";
	        }
	        
	        List<FundPriceHistory> fundPriceHistory = fundPriceHistoryDAO.read(id);
	        ArrayList<FundPriceList> fundPriceList = new ArrayList<FundPriceList>();
	        Iterator<FundPriceHistory> iter2 = fundPriceHistory.iterator();
	        while (iter2.hasNext()) {
	        	fundPriceList.add(new FundPriceList(iter2.next()));
	        }
	        request.setAttribute("fundPriceList", fundPriceList);
	        
        	return "researchFundDetail.jsp";
        	
        }catch (Exception e) {
        	errors.add(e.getMessage());
			return "researchFund.jsp";
		}
	}

}
