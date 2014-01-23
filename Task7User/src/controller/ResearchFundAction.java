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
import databean.FundPriceHistory;
import databean.Position;
import databean.Transaction;
import formbean.SellFundFormBean;

public class ResearchFundAction extends Action {
	
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	private DecimalFormat dfNumberCash = new DecimalFormat("#,##0.00");
	
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
	        List<Fund> funds = fundDAO.getAllFunds();
	        
	        // store all position information along with other information;
	        ArrayList<FundList> fundList = new ArrayList<FundList>();
	        java.util.Iterator<Fund> iter = funds.iterator();
	        Fund tempFund;
	        long tempLatestPrice;
	        while (iter.hasNext()) {
	        	tempFund = iter.next();
	        	tempLatestPrice = fundPriceHistoryDAO.getLatestPrice(tempFund.getFund_id());
	        	System.out.println(tempFund.getFund_id());
	        	fundList.add(new FundList(tempFund,dfNumberCash.format((double)tempLatestPrice/100)));
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
	        Fund fund = fundDAO.read(id);
	        if (fund == null) {
	        	errors.add("Value of parameter is not correct.");
	        }
	        request.setAttribute("fund", fund);
	        
	        if (errors.size() != 0) {
		        return "researchFund.jsp";
	        }
	        
	        List<FundPriceHistory> fundPriceList = fundPriceHistoryDAO.read(id);
	        request.setAttribute("fundPriceList", fundPriceList);
	        
        	return "researchFundDetail.jsp";
        	
        }catch (Exception e) {
        	errors.add(e.getMessage());
			return "researchFund.jsp";
		}
	}

}
