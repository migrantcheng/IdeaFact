package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import dao.interfaces.CustomerDAO;
import databean.Customer;

public class CustomerDAOHBImpl implements CustomerDAO {
private Session session;
	
	public CustomerDAOHBImpl(){
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	public Customer read(String username){
		session.beginTransaction();
		Query query = session.createQuery("from Customer where username = :username");
		query.setParameter("username", username);
		Customer customer = null;

        List <Customer> list = query.list();
        java.util.Iterator<Customer> iter = list.iterator();
        if (iter.hasNext()) {

        	customer = iter.next();
            System.out.println("Person: \"" + customer.getUsername() +"\", " + customer.getCity() +"\", " +customer.getCash());

        }

        session.getTransaction().commit();
        
        return customer;
	}


}
