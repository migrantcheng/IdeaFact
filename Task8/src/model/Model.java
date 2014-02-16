package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

public class Model {
	private TweetDAO tweetDao;

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);
			
			tweetDao = new TweetDAO("tweet", pool);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	public TweetDAO getTweetDao() {
		return tweetDao;
	}
	
}
