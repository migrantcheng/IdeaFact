package dao;

import dao.interfaces.CustomerDAO;
import dao.interfaces.FundDAO;
import dao.interfaces.TransactionDAO;

public class Model {
	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	private TransactionDAO transactionDAO;
	
	public Model(){
		customerDAO = new CustomerDAOHBImpl();
		fundDAO = new FundDAOHBImpl();
		transactionDAO = new TransactionDAOHBImpl();
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

}
