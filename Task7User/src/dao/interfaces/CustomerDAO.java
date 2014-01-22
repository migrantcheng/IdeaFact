package dao.interfaces;

import java.util.ArrayList;

import databean.Customer;

public interface CustomerDAO {

	public Customer read(String username);

	public void create(Customer customer);

	public void update(Customer customer);
	
	public ArrayList<String> spend(Customer customer, long amount);
	
}
