package dao;

import org.hibernate.Session;

import dao.interfaces.PositionDAO;

public class PositionDAOHBImpl implements PositionDAO{
	
	private Session session;
	
	public PositionDAOHBImpl() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}
}
