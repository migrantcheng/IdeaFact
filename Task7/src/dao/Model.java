package dao;

import dao.interfaces.CustomerDAO;
import dao.interfaces.EmployeeDAO;
import dao.interfaces.FundDAO;
import dao.interfaces.TransactionDAO;

public class Model {
	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;
	private FundDAO fundDAO;
	private TransactionDAO transactionDAO;
	
	public Model(){
		customerDAO = new CustomerDAOHBImpl();
		employeeDAO = new EmployeeDAOHBImpl();
		fundDAO = new FundDAOHBImpl();
		transactionDAO = new TransactionDAOHBImpl();
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

}
