package dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.interfaces.FundPriceHistoryDAO;
import databean.Customer;
import databean.FundPriceHistory;

public class FundPriceHistoryDAOHBImpl implements FundPriceHistoryDAO {
	
	private Session session;
	
	public FundPriceHistoryDAOHBImpl(){
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	@Override
	public void createFundPriceHistory(FundPriceHistory temp) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			session.save(temp);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		
	}

	@Override
	public FundPriceHistory readByIdDate(int fund_id, Date date) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from FundPriceHistory where fund_id = :fund_id AND price_date = :price_date" );
		query.setParameter("fund_id", fund_id);
		query.setParameter("price_date", date);
		FundPriceHistory fundPriceHistory = null;

        List <FundPriceHistory> list = query.list();
        java.util.Iterator<FundPriceHistory> iter = list.iterator();
        if (iter.hasNext()) {
        	fundPriceHistory = iter.next();
        }

        session.getTransaction().commit();
        
        return fundPriceHistory;
	}

}
