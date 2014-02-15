package Flickr;

import databean.FlickrPhoto;


public class PhotoList {
	private FlickrPhoto photo;
	private int category;
	private String catName;
	
	public PhotoList(FlickrPhoto photo, int category, String catName) {
		this.photo = photo;
		this.category = category;
		this.catName = catName;
	}
	
	public FlickrPhoto getPhoto() {
		return photo;
	}
	
	public int getCategory() {
		return category;
	}
	
	public String getCatName() {
		return catName;
	}
}