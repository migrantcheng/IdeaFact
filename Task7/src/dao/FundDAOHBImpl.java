package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.interfaces.FundDAO;
import databean.Customer;
import databean.Fund;

public class FundDAOHBImpl implements FundDAO {
	private Session session;
	
	public FundDAOHBImpl(){
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	@Override
	public void createFund(Fund fund) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			session.save(fund);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Fund> getFundList() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Fund");
        List <Fund> list = query.list();

        session.getTransaction().commit();
        
        return list;
	}

}
