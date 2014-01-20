package dao;

public class Model {
	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;
	private FundDAO fundDAO;
	
	public Model(){
		customerDAO = new CustomerDAO();
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
