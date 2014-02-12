package controller;

import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import Flickr.FlickrUtil;
import databean.FlickrPhoto;

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
        request.setAttribute("photos", photos);
       	return "index.jsp";
    }
}
