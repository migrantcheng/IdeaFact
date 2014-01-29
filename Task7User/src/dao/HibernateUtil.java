package dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static String getMD5(String str){
    	
		try {
			MessageDigest m;
			m = MessageDigest.getInstance("MD5");
	    	m.reset();
	    	m.update(str.getBytes());
	    	byte[] digest = m.digest();
	    	BigInteger bigInt = new BigInteger(1,digest);
	    	String hashtext = bigInt.toString(16);
	    	return hashtext;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}