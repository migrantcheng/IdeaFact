package dao.interfaces;

import java.util.List;

import databean.Customer;

public interface CustomerDAO {

	public Customer read(String username);

	public void create(Customer customer);

	public void update(Customer customer);

	public List<Customer> getCustomerList();
	

}
