package model;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.Tweet;


public class TweetDAO extends GenericDAO<Tweet>{

	public TweetDAO(String tableName,
			ConnectionPool connectionPool) throws DAOException {
		super(Tweet.class, tableName, connectionPool);
	}
	
	public void create(Tweet tweet) throws RollbackException{
		try {
			Transaction.begin();
			createAutoIncrement(tweet);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public Tweet[] getTweetsByPhotoId(String id) throws RollbackException {
		Tweet[] list = match(MatchArg.equals("photoId", id));
		return list;
	}

}
