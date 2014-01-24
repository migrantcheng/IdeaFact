package controller;

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
import databean.FundPriceHistory;
import databean.Position;
import databean.Transaction;

public class TransitionDayAction extends Action {
	
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;
	private PositionDAO positionDAO;
	
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
        		List<Fund> funds = fundDAO.getFundList();
        		request.setAttribute("funds", funds);
        		
        		return "transitionDay.jsp";
        		
        	}else{
        		//process transition day process
        		String dateStr = request.getParameter("currDate");
        		Date date = null;
        		
        		date = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(dateStr);
        		
        		List<Fund> fundList = fundDAO.getFundList();
        		Iterator<Fund> iter = fundList.iterator();
        		while(iter.hasNext()){
        			Fund fund = iter.next();
        			FundPriceHistory temp = new FundPriceHistory();
        			temp.setFund_id(fund.getFund_id());
        			temp.setPrice_date(date);
        			String priceStr = request.getParameter(""+fund.getFund_id());
        			Double price = Double.parseDouble(priceStr);
        			temp.setPrice((long)(price*100));
        			fundPriceHistoryDAO.createFundPriceHistory(temp);
        			
        		}
        		
        		List<Transaction> pendingList = transactionDAO.getPendingList();
        		Iterator<Transaction> pendingIter = pendingList.iterator();
        		while(pendingIter.hasNext()){
        			Transaction pendingItem = pendingIter.next();
        			if(pendingItem.getTransaction_type().equals(Transaction.BUY)){
        				FundPriceHistory today = fundPriceHistoryDAO.readByIdDate(pendingItem.getFund_id(), date);
        				Customer customer = customerDAO.readById(pendingItem.getCustomer_id());
        				double shares = (double)pendingItem.getAmount()/(double)today.getPrice();
        				
        				customer.setCash(customer.getCash() - pendingItem.getAmount());
        				
        				customerDAO.update(customer);
        				
        				Position position = positionDAO.read(pendingItem.getFund_id(), pendingItem.getCustomer_id());
        				if(position==null){
        					//create
        					position = new Position();
        					position.setCustomer_id(pendingItem.getCustomer_id());
        					position.setFund_id(pendingItem.getFund_id());
        					position.setShares((long)(shares*1000));
        					positionDAO.create(position);
        				}else{
        					long buyShares = position.getShares() + (long)(shares*1000);
        					position.setShares(buyShares);
        					positionDAO.update(position);
        				}
        				
        				pendingItem.setExecute_date(date);			
        				transactionDAO.update(pendingItem);
        				
        			}else if(pendingItem.getTransaction_type().equals(Transaction.SELL)){
        				FundPriceHistory today = fundPriceHistoryDAO.readByIdDate(pendingItem.getFund_id(), date);
        				Customer customer = customerDAO.readById(pendingItem.getCustomer_id());
        				customer.setCash(customer.getCash() + today.getPrice()*pendingItem.getShares()/1000);
        				customer.setAvailable(customer.getAvailable() + today.getPrice()*pendingItem.getShares()/1000);
        				
        				customerDAO.update(customer);
        				
//        				Position position = positionDAO.read(pendingItem.getFund_id(), pendingItem.getCustomer_id());
//        				long shares = position.getShares()-pendingItem.getShares();
//        				if(shares==0){
//        					//delete
//        					positionDAO.delete(position);
//        				}else{
//        					position.setShares(shares);
//        					positionDAO.update(position);
//        				}
        				
        				pendingItem.setExecute_date(date);			
        				transactionDAO.update(pendingItem);
        				
        			}else if(pendingItem.getTransaction_type().equals(Transaction.DEPOSIT)){
        				
        				Customer customer = customerDAO.readById(pendingItem.getCustomer_id());
        				customer.setAvailable(customer.getAvailable() + pendingItem.getAmount());
        				customerDAO.update(customer);
        				pendingItem.setExecute_date(date);			
        				transactionDAO.update(pendingItem);
        			}else if(pendingItem.getTransaction_type().equals(Transaction.WITHDRAWAL)){
        				
        				Customer customer = customerDAO.readById(pendingItem.getCustomer_id());
        				customer.setCash(customer.getCash() - pendingItem.getAmount());
        				customerDAO.update(customer);
        				pendingItem.setExecute_date(date);
        				transactionDAO.update(pendingItem);
        				
        			}
        		}
        		
        		customerDAO.testNewFunction("123");
        		
        		request.setAttribute("message","Transition Day: "+date+" successfully submitted.");
        		return "transitionDay.jsp";
        	}
        }catch (Exception e) {
        	errors.add(e.getMessage());
        	return "transitionDay.jsp";
		}
	}

}
