package controller;

import javax.servlet.http.HttpServletRequest;

import Twitter.TwitterUtil;

public class TwitterSignInAction extends Action {

	@Override
	public String getName() {
		return "twitterSignIn.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		return new TwitterUtil().requestToken();
	}

}
