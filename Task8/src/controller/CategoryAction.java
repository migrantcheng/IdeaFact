package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import Flickr.FlickrUtil;
import databean.FlickrPhoto;

public class CategoryAction extends Action {
	public String getName() { return "category.do"; }
    
    public String perform(HttpServletRequest request) {
    	String keyword = request.getParameter("key");
    	if (keyword == null || keyword.length() == 0) {
    		return "search.jsp";
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
    	
        request.setAttribute("photos", photos);
        request.setAttribute("category", keyword);
       	return "category.jsp";
    }
}
