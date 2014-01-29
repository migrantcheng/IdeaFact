package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import dao.interfaces.FundDAO;
import databean.Fund;
import databean.Position;

public class FundDAOHBImpl implements FundDAO {
	private Session session;
	
	public FundDAOHBImpl(){
		session = HibernateUtil.getSessionFactory().openSession();
	}

	public Fund read(String ticker){
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Fund where symbol = :ticker");
		query.setParameter("ticker", ticker);
		Fund fund = null;
        List <Fund> list = query.list();
        java.util.Iterator<Fund> iter = list.iterator();
        if (iter.hasNext()) {
        	fund = iter.next();
        }

        session.getTransaction().commit();
        
        return fund;
	}
	
	public Fund read(int fund_id) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Fund where fund_id = :fund_id");
		query.setParameter("fund_id", fund_id);
		Fund fund = null;
        List <Fund> list = query.list();
        java.util.Iterator<Fund> iter = list.iterator();
        if (iter.hasNext()) {
        	fund = iter.next();
        }

        session.getTransaction().commit();
        
        return fund;
	}
	
	public List<Fund> getAllFunds() {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Fund");
		List<Fund> funds = query.list();
        session.getTransaction().commit();
        return funds;
	}
}
