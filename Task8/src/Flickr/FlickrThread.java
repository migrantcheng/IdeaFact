package Flickr;

import java.util.ArrayList;

import databean.FlickrPhoto;


public class FlickrThread extends Thread {
	private ArrayList<PhotoList> photoList;
	private int value = 1;
	private int page = 1;
	private String keyword;
	
	public FlickrThread(ArrayList<PhotoList> photoList, int value, int page, String keyword) {
		this.photoList = photoList;
		this.value = value;
		this.page = page;
		this.keyword = keyword;
	}
	
	public void run() {
    	for (FlickrPhoto photo : FlickrUtil.search(keyword, 1, value)) {
    		photoList.add(new PhotoList(photo, keyword));
    	}
	}
}
