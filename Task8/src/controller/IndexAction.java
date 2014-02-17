package controller;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import model.KeywordDAO;
import model.Model;
import databean.KeywordBean;

public class IndexAction extends Action {
	private KeywordDAO keywordDAO;
	
	public IndexAction(Model model) {
		keywordDAO = model.getKeywordDao();
	}
	
	public String getName() { return "index.do"; }
    
    public String perform(HttpServletRequest request) {
    	ArrayList<KeywordBean> keys = new ArrayList<KeywordBean>();
    	try {
    		KeywordBean[] keywords = keywordDAO.getAll();
    		Arrays.sort(keywords);
    		for (int i = 0; i < keywords.length && i < 5; i++) {
    			keys.add(keywords[i]);
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	request.setAttribute("keys", keys);
       	return "search.jsp";
    }
}
