package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import dao.interfaces.TransactionDAO;
import databean.Customer;
import databean.Transaction;

public class TransactionDAOHBImpl implements TransactionDAO {

	private Session session;
	
	public TransactionDAOHBImpl(){
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	@Override
	public void create(Transaction transaction) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction();

			session.save(transaction);
			tx.commit();;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public List<Transaction> getDepositList() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Transaction where type = :type and execute_date is NULL");
		query.setParameter("type", Transaction.DEPOSIT);

        List <Transaction> list = query.list();

        session.getTransaction().commit();
        
        return list;
	}

	@Override
	public List<Transaction> getWithdrawList() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Transaction where type = :type and execute_date is NULL");
		query.setParameter("type", Transaction.DEPOSIT);

        List <Transaction> list = query.list();

        session.getTransaction().commit();
        
        return list;
	}

	@Override
	public List<Transaction> getBuyList() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Transaction where type = :type and execute_date is NULL");
		query.setParameter("type", Transaction.DEPOSIT);

        List <Transaction> list = query.list();

        session.getTransaction().commit();
        
        return list;
	}

	@Override
	public List<Transaction> getSellList() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Transaction where type = :type and execute_date is NULL");
		query.setParameter("type", Transaction.DEPOSIT);

        List <Transaction> list = query.list();

        session.getTransaction().commit();
        
        return list;
	}

	@Override
	public List<Transaction> getPendingList() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Transaction where execute_date is NULL");
		
        List <Transaction> list = query.list();

        session.getTransaction().commit();
        
        return list;
	}

	@Override
	public void update(Transaction pendingItem) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		org.hibernate.Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
			 session.update(pendingItem); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }
	}


}
