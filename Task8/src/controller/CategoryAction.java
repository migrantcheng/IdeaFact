package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import Flickr.FlickrUtil;
import databean.FlickrPhoto;

public class CategoryAction extends Action {
	private String[] places = {"Ka'anapali Beach", "Siesta Key Public Beach", "Gulf Islands National Seashore", "Fort De Soto Park", "Lanikai Beach", "Wailea Beach", "Assateague Beach", "La Jolla Cove", "Laguna Beach", "Hanauma Bay Nature Preserve"};
	public String getName() { return "category.do"; }
    
    public String perform(HttpServletRequest request) {
    	int category = 0;
    	try {
    		category = Integer.parseInt((String)request.getAttribute("cat"));
    	} catch (Exception e) {
    		return "index.do";
    	}
    	if (category < 0 || category >= places.length) {
    		return "index.do";
    	}
    	
    	int page = 1;
    	try {
    		page = Integer.parseInt((String)request.getAttribute("page"));
    	} catch (Exception e) {
    		/* ignore */
    	}
    	FlickrUtil flickr = new FlickrUtil();
		int value = 1;
    	if (request.getParameter("page") != null && FlickrUtil.isInteger(request.getParameter("page"))) {
    		page = Integer.parseInt(request.getParameter("page"));
    	}
    	
    	ArrayList<FlickrPhoto> photos = new ArrayList<FlickrPhoto>();
    	photos.addAll(flickr.search(places[category], 30, page));
    	
    	//test update twitter
//    	HttpSession session     = request.getSession(true);
//    	User user = (User) session.getAttribute("user");
//    	if(user != null){
//    		new TwitterUtil().update(new Token(user.getAccessToken(), user.getAccessTokenSecret()), "search for keywork: " + request.getParameter("key") + " from IdeaFact");
//    	}
    	//end test update twitter
    	
        request.setAttribute("photos", photos);
       	return "category.jsp";
    }
}
