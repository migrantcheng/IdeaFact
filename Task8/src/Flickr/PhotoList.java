package Flickr;

import databean.FlickrPhoto;


public class PhotoList {
	private FlickrPhoto photo;
	private String catName;
	
	public PhotoList(FlickrPhoto photo, String catName) {
		this.photo = photo;
		this.catName = catName;
	}
	
	public FlickrPhoto getPhoto() {
		return photo;
	}
	
	public String getCatName() {
		return catName;
	}
}