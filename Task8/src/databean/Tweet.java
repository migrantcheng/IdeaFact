package databean;

public class Tweet {
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
	
}
