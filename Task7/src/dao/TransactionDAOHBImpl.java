package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import dao.interfaces.TransactionDAO;
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

}
