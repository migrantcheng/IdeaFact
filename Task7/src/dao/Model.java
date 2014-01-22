package dao;

import dao.interfaces.CustomerDAO;
import dao.interfaces.EmployeeDAO;
import dao.interfaces.FundDAO;
import dao.interfaces.FundPriceHistoryDAO;
import dao.interfaces.TransactionDAO;
import dao.interfaces.PositionDAO;

public class Model {
	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;
	private FundDAO fundDAO;
	private TransactionDAO transactionDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private PositionDAO positionDAO;
	
	public Model(){
		customerDAO = new CustomerDAOHBImpl();
		employeeDAO = new EmployeeDAOHBImpl();
		fundDAO = new FundDAOHBImpl();
		transactionDAO = new TransactionDAOHBImpl();
		fundPriceHistoryDAO = new FundPriceHistoryDAOHBImpl();
		positionDAO = new PositionDAOHBImpl();
	}

	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	public EmployeeDAO getEmployeeDAO() {
		return employeeDAO;
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
