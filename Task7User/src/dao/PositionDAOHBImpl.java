package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import dao.interfaces.PositionDAO;
import databean.Position;

public class PositionDAOHBImpl implements PositionDAO{
	
	private Session session;
	
	public PositionDAOHBImpl() {
		session = HibernateUtil.getSessionFactory().openSession();
	}
	
	public List<Position> getAllPositions(int customer_id){
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Position where customer_id = :customer_id");
		query.setParameter("customer_id", customer_id);
		List<Position> positions = query.list();



        session.getTransaction().commit();
        
        return positions;
	}
	
	public Position read(int fund_id, int customer_id) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Position where fund_id = :fund_id and customer_id = :customer_id");
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
	
	public ArrayList<String> spend(Position position, long amount) {
		ArrayList<String> errors = new ArrayList<String>();
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Position where fund_id = :fund_id and customer_id = :customer_id");
		query.setParameter("fund_id", position.getFund_id());
		query.setParameter("customer_id", position.getCustomer_id());
		Position tempPosition = null;
        List <Position> list = query.list();
        java.util.Iterator<Position> iter = list.iterator();
        if (iter.hasNext()) {
        	tempPosition = iter.next();
        }

        if (tempPosition == null) {
        	errors.add("Cannot find position.");
        } else {
			if (tempPosition.getShares() < amount) {
				errors.add("Not enough shares.");
			} else {
				tempPosition.setShares(tempPosition.getShares() - amount);
				session.update(tempPosition);
			}
        }
		
        session.getTransaction().commit();


		return errors;
	}
}
