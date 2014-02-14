package controller;

import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.scribe.model.Token;

import Flickr.FlickrUtil;
import Twitter.TwitterUtil;
import databean.FlickrPhoto;
import databean.User;

public class IndexAction extends Action {

	public String getName() { return "index.do"; }
    
    public String perform(HttpServletRequest request) {
    	FlickrUtil flickr = new FlickrUtil();
    	if (request.getParameter("key") == null || request.getParameter("key").length() == 0) {
    		Random rand = new Random(System.currentTimeMillis()); 
    		int value = rand.nextInt(300) + 1;
    		System.out.println(value);
        	ArrayList<FlickrPhoto> photos = flickr.search("landscape", 2, value);
        	FlickrPhoto photo = null;
        	if (photos != null && photos.size() > 0) {
        		photo = photos.get(0);
        	}
            request.setAttribute("photo", photo);
    		return "search.jsp";
    	}
    	int page = 1;
    	if (request.getParameter("page") != null && FlickrUtil.isInteger(request.getParameter("page"))) {
    		page = Integer.parseInt(request.getParameter("page"));
    	}
    	ArrayList<FlickrPhoto> photos = flickr.search(request.getParameter("key"), 27, page);
    	
    	//test update twitter
    	HttpSession session     = request.getSession(true);
    	User user = (User) session.getAttribute("user");
    	if(user != null){
    		new TwitterUtil().update(new Token(user.getAccessToken(), user.getAccessTokenSecret()), "search for keywork: " + request.getParameter("key") + " from IdeaFact");
    	}
    	//end test update twitter
    	
        request.setAttribute("photos", photos);
       	return "index.jsp";
    }
}
