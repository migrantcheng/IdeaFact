package dao.interfaces;

import databean.Customer;

public interface CustomerDAO {

	public Customer read(String username);

	public void create(Customer customer);

	public void update(Customer customer);
	

}
