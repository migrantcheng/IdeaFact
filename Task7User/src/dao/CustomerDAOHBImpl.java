package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.interfaces.CustomerDAO;
import databean.Customer;

public class CustomerDAOHBImpl implements CustomerDAO {

	private Session session;
	
	public CustomerDAOHBImpl(){
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	public Customer read(String username){
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Customer where username = :username");
		query.setParameter("username", username);
		Customer customer = null;

        List <Customer> list = query.list();
        java.util.Iterator<Customer> iter = list.iterator();
        if (iter.hasNext()) {
        	customer = iter.next();
        }

        session.getTransaction().commit();
        
        return customer;
	}

	@Override
	public void create(Customer customer) {
//		session = HibernateUtil.getSessionFactory().getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//
//			session.save(customer);
//			tx.commit();
//		} catch (HibernateException e) {
//			if (tx != null)
//				tx.rollback();
//			e.printStackTrace();
//		}
	}
	
	public ArrayList<String> spend(Customer customer, long amount) {
		ArrayList<String> errors = new ArrayList<String>();
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Customer where username = :username");
		query.setParameter("username", customer.getUsername());

        List <Customer> list = query.list();
        java.util.Iterator<Customer> iter = list.iterator();
        if (iter.hasNext()) {
        	customer = iter.next();
        }

        
		if (customer.getAvailable() < amount) {
			errors.add("Insufficient fund.");
		} else {
			customer.setAvailable(customer.getAvailable() - amount);
			session.update(customer);
		}
		
        session.getTransaction().commit();


		return errors;
	}

	@Override
	public void update(Customer customer) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
			 session.update(customer); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }
		
	}
}
