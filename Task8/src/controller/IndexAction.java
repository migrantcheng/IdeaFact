package controller;

import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import Flickr.FlickrThread;
import Flickr.FlickrUtil;
import Flickr.PhotoList;

public class IndexAction extends Action {
	
	private String[] places = {"Ka'anapali Beach", "Siesta Key Public Beach", "Gulf Islands National Seashore", "Fort De Soto Park", "Lanikai Beach", "Wailea Beach", "Assateague Beach", "La Jolla Cove", "Laguna Beach", "Hanauma Bay Nature Preserve"};
	public String getName() { return "index.do"; }
    
    public String perform(HttpServletRequest request) {
		Random rand = new Random(System.currentTimeMillis()); 
		int value = 1;
    	int page = 1;
    	if (request.getParameter("page") != null && FlickrUtil.isInteger(request.getParameter("page"))) {
    		page = Integer.parseInt(request.getParameter("page"));
    	}
    	
    	
    	ArrayList<PhotoList> photoList = new ArrayList<PhotoList>();
    	Thread[] threads = new FlickrThread[places.length];
    	for (int i = 0; i < places.length; i++) {
    		value = rand.nextInt(10) + 1;
    		threads[i] = new FlickrThread(photoList, value, page, places[i]);
    		threads[i].start();
    	}
    	
    	//test update twitter
//    	HttpSession session     = request.getSession(true);
//    	User user = (User) session.getAttribute("user");
//    	if(user != null){
//    		new TwitterUtil().update(new Token(user.getAccessToken(), user.getAccessTokenSecret()), "search for keywork: " + request.getParameter("key") + " from IdeaFact");
//    	}
    	//end test update twitter
    	try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        request.setAttribute("photoList", photoList);
       	return "index.jsp";
    }
}
