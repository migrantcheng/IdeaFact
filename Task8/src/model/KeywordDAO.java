package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databean.KeywordBean;

public class KeywordDAO extends GenericDAO<KeywordBean>{

	public KeywordDAO(String tableName,
			ConnectionPool connectionPool) throws DAOException {
		super(KeywordBean.class, tableName, connectionPool);
	}
	
	public KeywordBean[] getKeyword(String keyword) throws RollbackException {
		return match(MatchArg.equals("word", keyword));
	}
	
	public KeywordBean[] getAll() throws RollbackException {
		return match(MatchArg.notEquals("frequency", 0));
	}

}
