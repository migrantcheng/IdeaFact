package databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class Tweet {
	private int id;
	private String tweetId;
	private String photoId;
	private String username;
	private String text;
	private String createdAt;
	private String isRetweet;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String isRetweet() {
		return isRetweet;
	}
	public void setIsRetweet(String isRetweet) {
		this.isRetweet = isRetweet;
	}
	public String getTweetId() {
		return tweetId;
	}
	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}
	public String getPhotoId() {
		return photoId;
	}
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
