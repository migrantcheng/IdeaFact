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
	
	public FlickrPhoto() {
		url = null;
		urlm = null;
		pageLink = null;
		title = null;
		id = null;
	}
	public FlickrPhoto(JSONObject photo) {
		url = (String)photo.get("url_o");
		if (url == null) {
			url = "http://farm" + photo.get("farm") + ".staticflickr.com/" + photo.get("server") 
			+ "/" + photo.get("id") + "_" + photo.get("secret") + ".jpg";
		}
		urlm = "http://farm" + photo.get("farm") + ".staticflickr.com/" + photo.get("server") 
				+ "/" + photo.get("id") + "_" + photo.get("secret") + "_n.jpg";
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
	public void setUrl(String url) {
		this.url = url;
	}
	public void setUrlm(String urlm) {
		this.urlm = urlm;
	}
	public void setPageLink(String pageLink) {
		this.pageLink = pageLink;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setId(String id) {
		this.id = id;
	}
}
