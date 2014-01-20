package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.interfaces.EmployeeDAO;
import databean.Customer;
import databean.Employee;

public class EmployeeDAOHBImpl implements EmployeeDAO {
	
	private Session session;
	
	public EmployeeDAOHBImpl(){
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	@Override
	public Employee read(String username) {
		session.beginTransaction();
		Query query = session.createQuery("from Employee where username = :username");
		query.setParameter("username", username);
		
		Employee employee = null;

        List <Employee> list = query.list();
        java.util.Iterator<Employee> iter = list.iterator();
        if (iter.hasNext()) {

        	employee = iter.next();
        }

        session.getTransaction().commit();
        
        return employee;
	}

	@Override
	public void updatePassword(String username, String newPassword) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Employee employee = 
	                    (Employee)session.get(Employee.class, username); 
	         employee.setPassword(newPassword);;
			 session.update(employee); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }
	}

	@Override
	public void createAccount(Employee employee) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			session.save(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}

	}

}
