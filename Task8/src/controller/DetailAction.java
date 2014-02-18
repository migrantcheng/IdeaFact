package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.TweetDAO;

import org.genericdao.RollbackException;
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
		ArrayList<Tweet> tweetList = new ArrayList<Tweet>();
		if(request.getParameter("button") != null){
			User user = (User)request.getSession().getAttribute("user");
			Token accessToken = new Token(user.getAccessToken(), user.getAccessTokenSecret());
			String content = request.getParameter("tweetContent");
			content = content + " - Sent from IdeaFact http://task8.yiye.im:8080/Task8/detail.do?id=" + request.getParameter("id");
			
			String tweetId = TwitterUtil.update(accessToken, content);
			System.out.println(tweetId);
//			
//			tweetList = new ArrayList<Tweet>();
//			tweetList.add(TwitterUtil.getStatusById(tweetId));
			
			try {
				Tweet temp = TwitterUtil.getStatusById(tweetId);
				temp.setPhotoId(request.getParameter("id"));
				tweetDao.create(temp);
			} catch (RollbackException e) {
				e.printStackTrace();
			}

		}
		
		int tweetCount = 0;
		int retweetCount = 0;
		
		try {
			Tweet[] list = tweetDao.getTweetsByPhotoId(request.getParameter("id"));
			tweetCount = list.length;
			for(int i = 0; i < list.length; i++){
				tweetList.add(0, list[i]);
				ArrayList<Tweet> retweetList = TwitterUtil.getRetweetsById(list[i].getTweetId());
				for(int j = 0; j < retweetList.size(); j++){
					tweetList.add(retweetList.get(j));
					retweetCount++;
				}
			}
		} catch (RollbackException e) {
			e.printStackTrace();
		}
		request.setAttribute("tweetCount", tweetCount);
		request.setAttribute("retweetCount", retweetCount);
		
		request.setAttribute("tweetList",tweetList);
		
		request.setAttribute("photo", photo);
		return "detail.jsp";
	}

}
