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
	private String[] places = {"Ka'anapali Beach", "Siesta Key Public Beach", "Gulf Islands National Seashore", "Fort De Soto Park", "Lanikai Beach", "Wailea Beach", "Assateague Beach", "La Jolla Cove", "Laguna Beach", "Hanauma Bay Nature Preserve"};
	public String getName() { return "index.do"; }
    
    public String perform(HttpServletRequest request) {
    	FlickrUtil flickr = new FlickrUtil();
		Random rand = new Random(System.currentTimeMillis()); 
		int value = 1;
    	int page = 1;
    	if (request.getParameter("page") != null && FlickrUtil.isInteger(request.getParameter("page"))) {
    		page = Integer.parseInt(request.getParameter("page"));
    	}
    	
    	
    	ArrayList<FlickrPhoto> photos = new ArrayList<FlickrPhoto>();
    	for (int i = 0; i < places.length; i++) {
    		value = rand.nextInt(10) + 1;
    		photos.addAll(flickr.search(places[i], 1, value));
    	}
    	
    	//test update twitter
//    	HttpSession session     = request.getSession(true);
//    	User user = (User) session.getAttribute("user");
//    	if(user != null){
//    		new TwitterUtil().update(new Token(user.getAccessToken(), user.getAccessTokenSecret()), "search for keywork: " + request.getParameter("key") + " from IdeaFact");
//    	}
    	//end test update twitter
    	
        request.setAttribute("photos", photos);
       	return "index.jsp";
    }
}
