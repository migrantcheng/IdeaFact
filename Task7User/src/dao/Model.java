package dao;

import dao.interfaces.CustomerDAO;
import dao.interfaces.FundDAO;
import dao.interfaces.FundPriceHistoryDAO;
import dao.interfaces.PositionDAO;
import dao.interfaces.TransactionDAO;

public class Model {
	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	private TransactionDAO transactionDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private PositionDAO positionDAO;
	
	public Model(){
		customerDAO = new CustomerDAOHBImpl();
		fundDAO = new FundDAOHBImpl();
		transactionDAO = new TransactionDAOHBImpl();
		fundPriceHistoryDAO = new FundPriceHistoryDAOHBImpl();
		positionDAO = new PositionDAOHBImpl();
	}

	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	public FundDAO getFundDAO() {
		return fundDAO;
	}

	public TransactionDAO getTransactionDAO() {
		return transactionDAO;
	}
	
	public FundPriceHistoryDAO getFundPriceHistoryDAO() {
		return fundPriceHistoryDAO;
	}
	
	public PositionDAO getPositionDAO() {
		return positionDAO;
	}

}
