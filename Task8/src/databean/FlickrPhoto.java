package databean;

import org.json.simple.JSONObject;

import Flickr.FlickrUtil;

public class FlickrPhoto {
	private String url;
	private String urlm;
	private String pageLink;
	private String title;
	private double lon = 0;
	private double lat = 0;
	private String id;
	
	public FlickrPhoto(JSONObject photo) {
		url = (String)photo.get("url_o");
		if (url == null) {
			url = "http://farm" + photo.get("farm") + ".staticflickr.com/" + photo.get("server") 
			+ "/" + photo.get("id") + "_" + photo.get("secret") + ".jpg";
		}
		urlm = "http://farm" + photo.get("farm") + ".staticflickr.com/" + photo.get("server") 
				+ "/" + photo.get("id") + "_" + photo.get("secret") + "_q.jpg";
		pageLink = "http://www.flickr.com/photos/" + photo.get("owner") + "/" + photo.get("id") + "/";
		title = (String)photo.get("title");
		id = (String)photo.get("id");
//		FlickrUtil.getPosition(this);
	}

	public String getUrl() {
		return url;
	}
	
	public String getUrlm() {
		return urlm;
	}
	
	public String getPageLink() {
		return pageLink;
	}
	
	public String getTitle() {
		return title;
	}
	
	public double getLon() {
		return lon;
	}
	
	public double getLat() {
		return lat;
	}
	
	public String getId() {
		return id;
	}
	
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	public void setLon(double lon) {
		this.lon = lon;
	}
}
