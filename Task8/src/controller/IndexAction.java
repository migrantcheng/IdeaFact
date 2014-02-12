package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import Flickr.FlickrUtil;
import databean.FlickrPhoto;

public class IndexAction extends Action {

	public String getName() { return "index.do"; }
    
    public String perform(HttpServletRequest request) {
    	if (request.getParameter("key") == null || request.getParameter("key").length() == 0) {
    		return "search.jsp";
    	}
    	FlickrUtil flickr = new FlickrUtil();
    	ArrayList<FlickrPhoto> photos = flickr.search(request.getParameter("key"), 10);
        request.setAttribute("photos", photos);
       	return "index.jsp";
    }
}
