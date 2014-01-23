package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import dao.interfaces.TransactionDAO;
import databean.FundPriceHistory;
import databean.Position;
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
	public Transaction getLastTransaction(int customer_id) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Transaction where customer_id =:customer_id");
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
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Transaction where customer_id = :customer_id");
		query.setParameter("customer_id", customer_id);
		List<Transaction> transactions = query.list();



        session.getTransaction().commit();
        
        return transactions;
	}
	

}
