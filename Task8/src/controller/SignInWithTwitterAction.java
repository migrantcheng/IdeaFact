package controller;

import javax.servlet.http.HttpServletRequest;

import org.scribe.model.Token;
import org.scribe.model.Verifier;

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
		twitter.getAccessToken(requestToken, verifier);
		
		return "index.jsp";
	}

}
