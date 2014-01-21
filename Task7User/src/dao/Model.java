package dao;

import dao.interfaces.CustomerDAO;
import dao.interfaces.EmployeeDAO;
import dao.interfaces.FundDAO;

public class Model {
	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;
	private FundDAO fundDAO;
	
	public Model(){
		customerDAO = new CustomerDAOHBImpl();
		employeeDAO = new EmployeeDAOHBImpl();
		fundDAO = new FundDAOHBImpl();
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

}
