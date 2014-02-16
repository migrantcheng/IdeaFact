package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.scribe.model.Token;
import org.scribe.model.Verifier;

import databean.User;
import Twitter.TwitterUtil;

public class SignInWithTwitterAction extends Action {

	@Override
	public String getName() {
		return "signInWithTwitter.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		System.out.println("Got the Request Token!");
		System.out.println("oauth_token is ");
		System.out.println(request.getParameter("oauth_token"));
		System.out.println("oauth_verifier is ");
		System.out.println(request.getParameter("oauth_verifier"));
		
		Token requestToken = new Token(request.getParameter("oauth_token"),request.getParameter("oauth_verifier"));
		Verifier verifier = new Verifier(request.getParameter("oauth_verifier"));
		
		TwitterUtil twitter = new TwitterUtil();
		Token accessToken = twitter.getAccessToken(requestToken, verifier);
		String[] response = accessToken.getRawResponse().split("=");
		String userName = response[response.length - 1];
		User user = new User();
		user.setUsername(userName);
		user.setAccessToken(accessToken.getToken());
		user.setAccessTokenSecret(accessToken.getSecret());
		
		HttpSession session = request.getSession();
        session.setAttribute("user",user);
		
		return "index.do";
	}

}
