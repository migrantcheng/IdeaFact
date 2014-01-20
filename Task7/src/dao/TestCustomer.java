package dao;


import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import databean.Customer;
import databean.Employee;
import databean.Fund;
import databean.FundPriceHistory;


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
        
        person.setUsername("barack");
        person.setPassword("123");
        person.setCity("DC");
        person.setCash(100);
        
        Employee employee = new Employee();
        employee.setUsername("emp1");
        employee.setFirstname("Haoran");
        employee.setLastname("Cheng");
        employee.setPassword("123");
        
        Fund fund = new Fund();
        fund.setName("Adobe");
        fund.setSymbol("ADBE");
        
        FundPriceHistory price = new FundPriceHistory();
        price.setFund_id(1);
        price.setPrice(1233);
        Date date = new Date();
        price.setPrice_date(date);

        session.save(person);
//        session.save(employee);
        session.save(fund);
        session.save(price);
        
    }
}
