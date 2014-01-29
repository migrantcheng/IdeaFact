package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import dao.interfaces.TransactionDAO;
import databean.Customer;
import databean.Position;
import databean.Transaction;

public class TransactionDAOHBImpl implements TransactionDAO {

	private Session session;
	
	public TransactionDAOHBImpl(){
		session = HibernateUtil.getSessionFactory().openSession();
	}

	@Override
	public void create(Transaction transaction) {
		session = HibernateUtil.getSessionFactory().openSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction();

			session.save(transaction);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> buyFund(Customer customer, Transaction transaction, long amount) {
		ArrayList<String> errors = new ArrayList<String>();
		session = HibernateUtil.getSessionFactory().openSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction();
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
			if (errors.size() == 0) {
				session.save(transaction);
			}
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}

		return errors;
	}
	
	public ArrayList<String> sellFund(Position position, Transaction transaction, long amount) {
		ArrayList<String> errors = new ArrayList<String>();
		session = HibernateUtil.getSessionFactory().openSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from Position where fund_id = :fund_id and customer_id = :customer_id");
			query.setParameter("fund_id", position.getFund_id());
			query.setParameter("customer_id", position.getCustomer_id());
			Position tempPosition = null;
	        List <Position> list = query.list();
	        java.util.Iterator<Position> iter = list.iterator();
	        if (iter.hasNext()) {
	        	tempPosition = iter.next();
	        }

	        if (tempPosition == null) {
	        	errors.add("Cannot find position.");
	        } else {
				if (tempPosition.getShares() < amount) {
					errors.add("Not enough shares.");
				} else {
					tempPosition.setShares(tempPosition.getShares() - amount);
					session.update(tempPosition);
				}
	        }
			if (errors.size() == 0) {
				session.save(transaction);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}

		return errors;
	}

	@Override
	public Transaction getLastTransaction(int customer_id) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Transaction where customer_id =:customer_id order by transaction_id desc");
		query.setParameter("customer_id", customer_id);
		Transaction lastTransaction = null;
        List <Transaction> list = query.list();
        java.util.Iterator<Transaction> iter = list.iterator();
        if (iter.hasNext()) {
        	lastTransaction = iter.next();
        }
        session.getTransaction().commit();
        
		return lastTransaction;
	}

	@Override
	public List<Transaction> getAll(int customer_id) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Transaction where customer_id = :customer_id order by transaction_id DESC");
		query.setParameter("customer_id", customer_id);
		List<Transaction> transactions = query.list();



        session.getTransaction().commit();
        
        return transactions;
	}

	@Override
	public List<Transaction> getPending(int customer_id) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Transaction where customer_id = :customer_id and execute_date is NULL order by transaction_id DESC");
		query.setParameter("customer_id", customer_id);
		List<Transaction> transactions = query.list();

        session.getTransaction().commit();
        
        return transactions;
	}
	

}
