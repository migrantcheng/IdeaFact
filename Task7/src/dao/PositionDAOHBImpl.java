package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.interfaces.PositionDAO;
import databean.Customer;
import databean.Position;

public class PositionDAOHBImpl implements PositionDAO {
	
	private Session session;
	
	public PositionDAOHBImpl(){
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	@Override
	public Position read(int fund_id, int customer_id) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Position where fund_id = :fund_id AND customer_id=:customer_id");
		query.setParameter("fund_id", fund_id);
		query.setParameter("customer_id", customer_id);
		Position position = null;

        List <Position> list = query.list();
        java.util.Iterator<Position> iter = list.iterator();
        if (iter.hasNext()) {
        	position = iter.next();
        }

        session.getTransaction().commit();
        
        return position;
	}

	@Override
	public void delete(Position position) {
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         
	         Query query = session.createQuery("delete from Position where fund_id = :fund_id AND customer_id=:customer_id");
	         query.setParameter("fund_id", position.getFund_id());
	 		 query.setParameter("customer_id", position.getCustomer_id());
	 		 query.executeUpdate();
	         
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }
	}

	@Override
	public void update(Position position) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
			 session.update(position); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }
		
	}

	@Override
	public void create(Position position) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			session.save(position);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		
	}

	

}
