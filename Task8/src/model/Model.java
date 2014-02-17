package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

public class Model {
	private TweetDAO tweetDao;
	private KeywordDAO keywordDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);
			
			tweetDao = new TweetDAO("tweet", pool);
			keywordDAO = new KeywordDAO("keyword", pool);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	public TweetDAO getTweetDao() {
		return tweetDao;
	}
	
	public KeywordDAO getKeywordDao() {
		return keywordDAO;
	}
}
