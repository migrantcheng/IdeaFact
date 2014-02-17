package controller;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import model.KeywordDAO;
import model.Model;
import Flickr.FlickrUtil;
import databean.FlickrPhoto;
import databean.KeywordBean;

public class ListAction extends Action {
	private KeywordDAO keywordDAO;
	public String getName() { return "list.do"; }
	
	public ListAction(Model model) {
		keywordDAO = model.getKeywordDao();
	}
    
    public String perform(HttpServletRequest request) {
    	String keyword = request.getParameter("key");
    	if (keyword == null || keyword.length() == 0) {
    		return "index.do";
    	}
    	ArrayList<KeywordBean> keys = new ArrayList<KeywordBean>();
    	try {
    		KeywordBean[] keywords = keywordDAO.getKeyword(keyword.toLowerCase());
    		if (keywords.length == 0) {
    			keywordDAO.create(new KeywordBean(keyword.toLowerCase()));
    		} else {
    			keywords[0].setFrequency(keywords[0].getFrequency() + 1);
    			keywordDAO.update(keywords[0]);
    		}
    		keywords = keywordDAO.getAll();
    		Arrays.sort(keywords);
    		for (int i = 0; i < keywords.length && i < 5; i++) {
    			keys.add(keywords[i]);
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	int page = 1;
    	try {
    		page = Integer.parseInt((String)request.getAttribute("page"));
    	} catch (Exception e) {
    		/* ignore */
    	}
    	if (request.getParameter("page") != null && FlickrUtil.isInteger(request.getParameter("page"))) {
    		page = Integer.parseInt(request.getParameter("page"));
    	}
    	
    	ArrayList<FlickrPhoto> photos = new ArrayList<FlickrPhoto>();
    	photos.addAll(FlickrUtil.search(keyword, 30, page));
    	
    	//test update twitter
//    	HttpSession session     = request.getSession(true);
//    	User user = (User) session.getAttribute("user");
//    	if(user != null){
//    		new TwitterUtil().update(new Token(user.getAccessToken(), user.getAccessTokenSecret()), "search for keywork: " + request.getParameter("key") + " from IdeaFact");
//    	}
    	//end test update twitter

        request.setAttribute("keys", keys);
        request.setAttribute("photos", photos);
        request.setAttribute("category", keyword);
       	return "category.jsp";
    }
}
