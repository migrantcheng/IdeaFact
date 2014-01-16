package dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import databean.Customer;


public class TestCustomer {

    public static void main(String[] args) {
        Session session ;
        session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        createPerson(session);

        queryPerson(session);

    }

    private static void queryPerson(Session session) {
        Query query = session.createQuery("from Customer");                 
        List <Customer>list = query.list();
        java.util.Iterator<Customer> iter = list.iterator();
        while (iter.hasNext()) {

        	Customer person = iter.next();
            System.out.println("Person: \"" + person.getUsername() +"\", " + person.getCity() +"\", " +person.getCash());

        }

        session.getTransaction().commit();

    }

    public static void createPerson(Session session) {
        Customer person = new Customer();
        
        person.setUsername("Barack");
        person.setCity("DC");
        person.setCash(100);

        session.save(person);
    }
}
