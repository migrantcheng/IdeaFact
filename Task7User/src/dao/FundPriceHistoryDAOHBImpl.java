package dao;

import org.hibernate.Session;

import dao.interfaces.FundPriceHistoryDAO;

public class FundPriceHistoryDAOHBImpl implements FundPriceHistoryDAO{
	private Session session;
	
	public FundPriceHistoryDAOHBImpl(){
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}

}
