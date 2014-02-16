package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.TweetDAO;

import org.scribe.model.Token;

import Flickr.FlickrUtil;
import Twitter.TwitterUtil;
import databean.FlickrPhoto;
import databean.Tweet;
import databean.User;

public class DetailAction extends Action {
	
	private final static String TWITTER="EEE MMM dd HH:mm:ss ZZZZZ yyyy";
	private static SimpleDateFormat sf = new SimpleDateFormat(TWITTER);
	private TweetDAO tweetDao;

	public DetailAction(Model model) {
		tweetDao = model.getTweetDao();
	}

	@Override
	public String getName() {
		return "detail.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		if (request.getParameter("id") == null) {
			return "index.do";
		}
		FlickrPhoto photo = FlickrUtil.getById(request.getParameter("id"));
		if(request.getParameter("button") != null){
			User user = (User)request.getSession().getAttribute("user");
			Token accessToken = new Token(user.getAccessToken(), user.getAccessTokenSecret());
			String content = request.getParameter("tweetContent");
			
			String tweetId = TwitterUtil.update(accessToken, content);
			System.out.println(tweetId);
			
			ArrayList<Tweet> tweetList = new ArrayList<Tweet>();
			tweetList.add(TwitterUtil.getStatusById(tweetId));
			
			ArrayList<Tweet> retweetList = TwitterUtil.getRetweetsById("434901229375082496");
			for(Tweet tweet: retweetList){
				tweetList.add(tweet);
			}
			request.setAttribute("tweetList",tweetList);
			return "detail.jsp";
		}
		request.setAttribute("photo", photo);
		return "detail.jsp";
	}

}
