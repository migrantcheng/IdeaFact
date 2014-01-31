package controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import dao.Model;
import dao.interfaces.CustomerDAO;
import dao.interfaces.FundDAO;
import dao.interfaces.FundPriceHistoryDAO;
import dao.interfaces.PositionDAO;
import dao.interfaces.TransactionDAO;
import databean.Customer;
import databean.Fund;
import databean.FundData;
import databean.FundPriceHistory;
import databean.Position;
import databean.Transaction;

public class TransitionDayAction extends Action {
	
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;
	private PositionDAO positionDAO;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	private DecimalFormat dfNumberCash = new DecimalFormat("#,##0.00");
	
	public TransitionDayAction(Model model){
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		transactionDAO = model.getTransactionDAO();
		customerDAO = model.getCustomerDAO();
		positionDAO = model.getPositionDAO();
	}

	@Override
	public String getName() {
		return "transitionDay.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// Set up error list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
        	String button = request.getParameter("button");
        	if(button==null){
        		//refresh page
        		List<Fund> funds;
        		synchronized (fundDAO) {
        			funds = fundDAO.getFundList();
        		}
        		List<FundData> dataList = new ArrayList<FundData>();
        		if(funds!=null){
        			Iterator<Fund> fundIter = funds.iterator();
        			while(fundIter.hasNext()){
        				Fund tmp = fundIter.next();
        				FundData data = new FundData();
        				data.setFund_id(tmp.getFund_id());
        				data.setName(tmp.getName());
        				data.setSymbol(tmp.getSymbol());
        				long latestPrice;
        				synchronized(fundPriceHistoryDAO){
        					latestPrice = fundPriceHistoryDAO.getLatestPrice(tmp.getFund_id());
        				}
        				if(latestPrice==-1){
        					data.setPrice("-");
        				}else{
        					data.setPrice("$" + dfNumberCash.format(((double)latestPrice)/100));
        				}
        				dataList.add(data);
        			}
        		}
        		request.setAttribute("funds", dataList);
        		Date date;
        		synchronized (transactionDAO) {
        			date = transactionDAO.getLastTransitionDay();
        		}
        		if(date!=null){
        		date.setDate(date.getDate()+1);
        		String dateStr = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).format(date);
        		
        		request.setAttribute("startDate", dateStr);
        		}
        		return "transitionDay.jsp";
        		
        	}else{
        		//process transition day process
        		String dateStr = request.getParameter("currDate");
        		Date date = null;
        		
        		date = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(dateStr);
        		Date lastDate;
        		synchronized (transactionDAO) {
        			lastDate = transactionDAO.getLastTransitionDay();
        		}
        		if(lastDate!=null){
        			if(lastDate.after(date) || lastDate.getDate()==date.getDate()){
        				throw new Exception("Date should be later than last transition Day: "+sdf.format(lastDate));
        			}
        		}
        		List<Fund> fundList;
        		synchronized (fundDAO) {
        			fundList = fundDAO.getFundList();
        		}
        		Iterator<Fund> iter = fundList.iterator();
        		while(iter.hasNext()){
        			Fund fund = iter.next();
        			String priceStr = request.getParameter(""+fund.getFund_id());
        			if(priceStr==null){
        				throw new Exception("Fund list not updated. Please refresh and try again.");
        			}
        			Double price = Double.parseDouble(priceStr);
        			if(!(price>=0.01 && price<=9999.99)){
        				
        				throw new Exception("Fund price should be 0.01 ~ 9999.99.");
        			}
        		}
        		
        		iter = fundList.iterator();
        		while(iter.hasNext()){
        			Fund fund = iter.next();
        			FundPriceHistory temp = new FundPriceHistory();
        			temp.setFund_id(fund.getFund_id());
        			temp.setPrice_date(date);
        			String priceStr = request.getParameter(""+fund.getFund_id());
        			Double price = Double.parseDouble(priceStr);
        			temp.setPrice((long)(price*100));
        			synchronized (fundPriceHistoryDAO) {
        				fundPriceHistoryDAO.createFundPriceHistory(temp);
        			}
        		}
        		List<Transaction> pendingList;
        		synchronized (transactionDAO) {
        			pendingList = transactionDAO.getPendingList();
        		}
        		Iterator<Transaction> pendingIter = pendingList.iterator();
        		while(pendingIter.hasNext()){
        			Transaction pendingItem = pendingIter.next();
        			if(pendingItem.getTransaction_type().equals(Transaction.BUY)){
        				FundPriceHistory today;
        				synchronized (fundPriceHistoryDAO) {
        					today = fundPriceHistoryDAO.readByIdDate(pendingItem.getFund_id(), date);
        				}
        				Customer customer;
        				synchronized (customerDAO) {
        					customer = customerDAO.readById(pendingItem.getCustomer_id());
        				}
        				double shares = (double)pendingItem.getAmount()/(double)today.getPrice();
        				
        				customer.setCash(customer.getCash() - pendingItem.getAmount());
        				
        				synchronized (customerDAO) {
        					customerDAO.update(customer);
        				}
        				
        				Position position;
        				synchronized (positionDAO) {
        					position = positionDAO.read(pendingItem.getFund_id(), pendingItem.getCustomer_id());
        				}
        				if(position==null){
        					//create
        					position = new Position();
        					position.setCustomer_id(pendingItem.getCustomer_id());
        					position.setFund_id(pendingItem.getFund_id());
        					position.setShares((long)(shares*1000));
        					synchronized (positionDAO) {
        						positionDAO.create(position);
        					}
        				}else{
        					long buyShares = position.getShares() + (long)(shares*1000);
        					position.setShares(buyShares);
        					synchronized (positionDAO) {
        						positionDAO.update(position);
        					}
        				}
        				
        				pendingItem.setShares((int)(shares*1000));
        				pendingItem.setExecute_date(date);		
        				pendingItem.setShareprice(today.getPrice());
        				synchronized (transactionDAO) {
        					transactionDAO.update(pendingItem);
        				}
        				
        			}else if(pendingItem.getTransaction_type().equals(Transaction.SELL)){
        				FundPriceHistory today;
        				synchronized (fundPriceHistoryDAO) {
        					today = fundPriceHistoryDAO.readByIdDate(pendingItem.getFund_id(), date);
        				}
        				Customer customer;
        				synchronized (customerDAO) {
        					customer = customerDAO.readById(pendingItem.getCustomer_id());
        				}
        				customer.setCash(customer.getCash() + today.getPrice()*pendingItem.getShares()/1000);
        				customer.setAvailable(customer.getAvailable() + today.getPrice()*pendingItem.getShares()/1000);
        				
        				synchronized (customerDAO) {
        					customerDAO.update(customer);
        				}
        				
//        				Position position = positionDAO.read(pendingItem.getFund_id(), pendingItem.getCustomer_id());
//        				long shares = position.getShares()-pendingItem.getShares();
//        				if(shares==0){
//        					//delete
//        					synchronized (positionDAO) {
//        						positionDAO.delete(position);
//        					}
//        				}else{
//        					position.setShares(shares);
//        					synchronized (positionDAO) {
//        						positionDAO.update(position);
//        					}
//        				}
        				
        				pendingItem.setAmount(today.getPrice()*pendingItem.getShares()/1000);
        				pendingItem.setExecute_date(date);
        				pendingItem.setShareprice(today.getPrice());
        				synchronized (transactionDAO) {
        					transactionDAO.update(pendingItem);
        				}
        				
        			}else if(pendingItem.getTransaction_type().equals(Transaction.DEPOSIT)){
        				
        				Customer customer;
        				synchronized (customerDAO) {
	        				customer = customerDAO.readById(pendingItem.getCustomer_id());
	        				customer.setAvailable(customer.getAvailable() + pendingItem.getAmount());
	        				customerDAO.update(customer);
        				}
        				pendingItem.setExecute_date(date);
        				synchronized (transactionDAO) {
        					transactionDAO.update(pendingItem);
        				}
        			}else if(pendingItem.getTransaction_type().equals(Transaction.WITHDRAW)){
        				
        				Customer customer;
        				synchronized (customerDAO) {
	        				customer = customerDAO.readById(pendingItem.getCustomer_id());
	        				customer.setCash(customer.getCash() - pendingItem.getAmount());
	        				customerDAO.update(customer);
        				}
        				pendingItem.setExecute_date(date);
        				synchronized (transactionDAO) {
        					transactionDAO.update(pendingItem);
        				}
        				
        			}
        		}
        		
        		
//        		request.setAttribute("messages","Transition Day: "+date+" successfully submitted.");
        		
        		List<String> messages = new ArrayList<String>();
    			messages.add("Transition Day: "+sdf.format(date)+" successfully submitted.");
    			request.setAttribute("messages",messages);
        		return "transitionDaySuccess.jsp";
        	}
        }catch(ParseException e){
        	errors.add("Date format wrong. Example format: 12/01/2013");
        	return "transitionDayFail.jsp";
        }catch (NumberFormatException e){
        	errors.add("Close price should be two decimal number.");
        	return "transitionDayFail.jsp";
        }catch (Exception e) {
        	errors.add(e.getMessage());
        	return "transitionDayFail.jsp";
		}
	}
	
	

}
