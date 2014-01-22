package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import dao.interfaces.FundPriceHistoryDAO;
import databean.Customer;
import databean.Fund;
import databean.FundPriceHistory;

public class FundPriceHistoryDAOHBImpl implements FundPriceHistoryDAO{
	private Session session;
	
	public FundPriceHistoryDAOHBImpl() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	public long getLatestPrice(int id) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from FundPriceHistory where fund_id = :id");
		query.setParameter("id", id);
		long price = 0;

        List <FundPriceHistory> list = query.list();
        java.util.Iterator<FundPriceHistory> iter = list.iterator();
        if (iter.hasNext()) {
        	price = iter.next().getPrice();
        } else {
        	price = -1;
        }

        session.getTransaction().commit();
        
        return price;
	}
	
	public List<FundPriceHistory> read(int fundId){
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from FundPriceHistory where fund_id = :fundId");
		query.setParameter("fundId", fundId);
        List <FundPriceHistory> list = query.list();
        session.getTransaction().commit();
        return list;
	}
}
