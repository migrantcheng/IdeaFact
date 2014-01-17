package dao;

public class Model {
	private CustomerDAO customerDAO;
	
	public Model(){
		customerDAO = new CustomerDAO();
	}

	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}
	

}
