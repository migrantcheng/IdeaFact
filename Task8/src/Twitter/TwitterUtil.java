package Twitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import databean.Tweet;

public class TwitterUtil {
	
	private final static String CONSUMER_KEY = "JB903vkzwl7bOqWFjtooA";
	private final static String COMSUMER_SECRET = "u1sQpBvqpbazSMdWAWmaD5sXO3a3wnNUYcl3FNIqE";
	private final static String ACCESS_TOKEN = "2329310366-CkB8831CFX4GzCSemQXQaDMhZXgIRSy5TILpPGA";
	private final static String ACCESS_TOKEN_SECRET = "0PeboKQzQ92z3Vz8XMIQTiYe07DcAgy0gRGGdnawrQYj4";
	
	private final static String USER_TIMELINE_URL = "https://api.twitter.com/1.1/statuses/user_timeline.json";
	private final static String SEARCH_URL = "https://api.twitter.com/1.1/search/tweets.json";
	private final static String REQUEST_TOKEN_URL = "https://api.twitter.com/oauth/request_token";
	private static final String PROTECTED_RESOURCE_URL = "https://api.twitter.com/1.1/account/verify_credentials.json";
	private final static String UPDATE_URL = "https://api.twitter.com/1.1/statuses/update.json";
	private final static String STATUSES_SHOW_ID_URL = "https://api.twitter.com/1.1/statuses/show.json";
	private final static String STATUSES_RETWEETS_ID_URL = "https://api.twitter.com/1.1/statuses/retweets/";
	
	
	public void search(String keyword){
		try {
			System.out
					.println("Starting Twitter public stream consumer thread.");

			// Enter your consumer key and secret below
			OAuthService service = new ServiceBuilder()
					.provider(TwitterApi.class)
					.apiKey(CONSUMER_KEY)
					.apiSecret(COMSUMER_SECRET)
					.build();

			// Set your access token
			Token accessToken = new Token(ACCESS_TOKEN, ACCESS_TOKEN_SECRET);

			// Let's generate the request
			System.out.println("Connecting to Twitter Public Stream");
			OAuthRequest request = new OAuthRequest(Verb.GET,
					SEARCH_URL);
//			request.
			request.addHeader("version", "HTTP/1.1");
			request.addHeader("host", "api.twitter.com");
			request.setConnectionKeepAlive(true);
			request.addHeader("user-agent", "IdeaFact Task 8");
//			request.addQuerystringParameter(key, value);
			request.addQuerystringParameter("q", keyword);
			request.addQuerystringParameter("count", "10");
			// Set keywords
																// you'd like to
																// track here
			service.signRequest(accessToken, request);
			Response response = request.send();

			// Create a reader to read Twitter's stream
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					response.getStream()));
			

			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				sb.append(line);
			}
			
			JSONObject json = (JSONObject)JSONValue.parse(sb.toString());
			JSONArray array=(JSONArray) json.get("statuses");
			for(int i = 0; i < array.size(); i++){
				JSONObject temp = (JSONObject)array.get(i);
				System.out.println(temp.get("text"));
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void countOfSearch(String keyword){
		try {
			System.out
					.println("Starting Twitter public stream consumer thread.");

			// Enter your consumer key and secret below
			OAuthService service = new ServiceBuilder()
					.provider(TwitterApi.class)
					.apiKey(CONSUMER_KEY)
					.apiSecret(COMSUMER_SECRET)
					.build();

			// Set your access token
			Token accessToken = new Token(ACCESS_TOKEN, ACCESS_TOKEN_SECRET);

			// Let's generate the request
			System.out.println("Connecting to Twitter Public Stream");
			OAuthRequest request = new OAuthRequest(Verb.GET,
					SEARCH_URL);
//			request.
			request.addHeader("version", "HTTP/1.1");
			request.addHeader("host", "api.twitter.com");
			request.setConnectionKeepAlive(true);
			request.addHeader("user-agent", "IdeaFact Task 8");
//			request.addQuerystringParameter(key, value);
			request.addQuerystringParameter("q", keyword);
			request.addQuerystringParameter("count", "100");
			// Set keywords
																// you'd like to
																// track here
			service.signRequest(accessToken, request);
			Response response = request.send();

			// Create a reader to read Twitter's stream
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					response.getStream()));
			

			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
//				System.out.println(line);
				sb.append(line);
			}
			
			JSONObject json = (JSONObject)JSONValue.parse(sb.toString());
			JSONArray array=(JSONArray) json.get("statuses");
			System.out.println("Count for "+ keyword + " is: " + array.size());
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void getUserTimeline(String screenName){
		try {
			System.out
					.println("Starting Twitter public stream consumer thread.");

			// Enter your consumer key and secret below
			OAuthService service = new ServiceBuilder()
					.provider(TwitterApi.class)
					.apiKey(CONSUMER_KEY)
					.apiSecret(COMSUMER_SECRET)
					.build();

			// Set your access token
			Token accessToken = new Token(ACCESS_TOKEN, ACCESS_TOKEN_SECRET);

			// Let's generate the request
			System.out.println("Connecting to Twitter Public Stream");
			OAuthRequest request = new OAuthRequest(Verb.GET,
					USER_TIMELINE_URL);
//			request.
			request.addHeader("version", "HTTP/1.1");
			request.addHeader("host", "api.twitter.com");
			request.setConnectionKeepAlive(true);
			request.addHeader("user-agent", "IdeaFact Task 8");
//			request.addQuerystringParameter(key, value);
			request.addQuerystringParameter("screen_name", screenName);
			request.addQuerystringParameter("count", "2");
			// Set keywords
																// you'd like to
																// track here
			service.signRequest(accessToken, request);
			Response response = request.send();

			

			// Create a reader to read Twitter's stream
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					response.getStream()));
			

			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				sb.append(line);
			}
			
			JSONArray array=(JSONArray)JSONValue.parse(sb.toString());
			for(int i = 0; i < array.size(); i++){
				JSONObject json = (JSONObject)array.get(i);
				System.out.println(json.get("text"));
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public String requestToken(){
		
		try {
			System.out
					.println("Starting Twitter request token");

			// Enter your consumer key and secret below
			OAuthService service = new ServiceBuilder()
					.provider(TwitterApi.SSL.class)
					.apiKey(CONSUMER_KEY)
					.apiSecret(COMSUMER_SECRET)
					.callback("http://127.0.0.1:8080/Task8/signInWithTwitter.do")
					.build();
			
			Scanner in = new Scanner(System.in);
			
			System.out.println("=== Twitter's OAuth Workflow ===");
		    System.out.println();

		    // Obtain the Request Token
		    System.out.println("Fetching the Request Token...");
		    Token requestToken = service.getRequestToken();
		    System.out.println("Got the Request Token!");
		    System.out.println(requestToken.getToken());
		    System.out.println(requestToken.getSecret());
		    System.out.println();

		    System.out.println("Now go and authorize Task Application here:");
		    System.out.println(service.getAuthorizationUrl(requestToken));
		    return service.getAuthorizationUrl(requestToken);
//		    
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
		return null;
	}
	
	public Token getAccessToken(Token requestToken, Verifier verifier){
		
		System.out
		.println("Starting Twitter request access token");

		// Enter your consumer key and secret below
		OAuthService service = new ServiceBuilder()
		.provider(TwitterApi.SSL.class)
		.apiKey(CONSUMER_KEY)
		.apiSecret(COMSUMER_SECRET)
		.callback("http://127.0.0.1:8080/Task8/signInWithTwitter.do")
		.build();


		System.out.println(service.getAuthorizationUrl(requestToken));
	    System.out.println("And paste the verifier here");
	    System.out.print(">>");
//	    verifier = new Verifier(in.nextLine());
	    System.out.println();

	    // Trade the Request Token and Verfier for the Access Token
	    System.out.println("Trading the Request Token for an Access Token...");
	    Token accessToken = service.getAccessToken(requestToken, verifier);
	    System.out.println("Got the Access Token!");
	    System.out.println("(if your curious it looks like this: " + accessToken + " )");
	    System.out.println();

//	    // Now let's go and ask for a protected resource!
//	    System.out.println("Now we're going to access a protected resource...");
//	    OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
//	    request.addBodyParameter("status", "this is sparta! *");
//	    service.signRequest(accessToken, request);
//	    Response response = request.send();
//	    System.out.println("Got it! Lets see what we found...");
//	    System.out.println();
//	    System.out.println(response.getBody());
//
//	    System.out.println();
//	    System.out.println("Thats it man! Go and build something awesome with Scribe! :)");
//	    
////	    128.237.216.188:8080/Task8/twitterSignIn.do
	    
	    return accessToken;
		
	}
	
	public static String update(Token accessToken, String status){
		
		String twitterId = "";
		try {
			System.out
					.println("Starting Twitter update thread.");

			// Enter your consumer key and secret below
			OAuthService service = new ServiceBuilder()
					.provider(TwitterApi.SSL.class)
					.apiKey(CONSUMER_KEY)
					.apiSecret(COMSUMER_SECRET)
					.build();

//			accessToken = new Token(ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
			
			// Let's generate the request
			System.out.println("Connecting to Twitter Public Stream");
			OAuthRequest request = new OAuthRequest(Verb.POST,
					UPDATE_URL);
//			request.
			request.addHeader("version", "HTTP/1.1");
			request.addHeader("host", "api.twitter.com");
			request.setConnectionKeepAlive(true);
			request.addHeader("user-agent", "IdeaFact Task 8");
//			request.addQuerystringParameter(key, value);
			request.addQuerystringParameter("status", status);
			service.signRequest(accessToken, request);
			Response response = request.send();

			

			// Create a reader to read Twitter's stream
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					response.getStream()));
			

			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
//				System.out.println(line);
				sb.append(line);
			}
			
			JSONObject json = (JSONObject)JSONValue.parse(sb.toString());
			twitterId = (String)json.get("id_str");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return twitterId;
	}
	
	public static Tweet getStatusById(String id){
		Tweet tweet = new Tweet();
		try {
			System.out
					.println("Starting Twitter public stream consumer thread.");

			// Enter your consumer key and secret below
			OAuthService service = new ServiceBuilder()
					.provider(TwitterApi.class)
					.apiKey(CONSUMER_KEY)
					.apiSecret(COMSUMER_SECRET)
					.build();

			// Set your access token
			Token accessToken = new Token(ACCESS_TOKEN, ACCESS_TOKEN_SECRET);

			// Let's generate the request
			System.out.println("Connecting to Twitter Public Stream");
			OAuthRequest request = new OAuthRequest(Verb.GET,
					STATUSES_SHOW_ID_URL);
//			request.
			request.addHeader("version", "HTTP/1.1");
			request.addHeader("host", "api.twitter.com");
			request.setConnectionKeepAlive(true);
			request.addHeader("user-agent", "IdeaFact Task 8");
//			request.addQuerystringParameter(key, value);
			request.addQuerystringParameter("id", id);
			service.signRequest(accessToken, request);
			Response response = request.send();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					response.getStream()));
			
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				sb.append(line);
			}
			JSONObject json = (JSONObject)JSONValue.parse(sb.toString());
			tweet.setTweetId((String)json.get("id_str"));
			tweet.setText((String)json.get("text"));
			JSONObject user = (JSONObject)json.get("user");
			tweet.setUsername((String) user.get("screen_name"));
			String createdAt = (String) json.get("created_at");
			String TWITTER="EEE MMM dd HH:mm:ss ZZZZZ yyyy";
			SimpleDateFormat sf = new SimpleDateFormat(TWITTER);
			Date date = sf.parse(createdAt);
			tweet.setCreatedAt(date.toString());
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tweet;
	}
	
	public static ArrayList<Tweet> getRetweetsById(String id){
		ArrayList<Tweet> list = new ArrayList<Tweet>();
		try {
			System.out
					.println("Starting Twitter public stream consumer thread.");

			// Enter your consumer key and secret below
			OAuthService service = new ServiceBuilder()
					.provider(TwitterApi.class)
					.apiKey(CONSUMER_KEY)
					.apiSecret(COMSUMER_SECRET)
					.build();

			// Set your access token
			Token accessToken = new Token(ACCESS_TOKEN, ACCESS_TOKEN_SECRET);

			// Let's generate the request
			System.out.println("Connecting to Twitter Public Stream");
			OAuthRequest request = new OAuthRequest(Verb.GET,
					STATUSES_RETWEETS_ID_URL+id+".json");
//			request.
			request.addHeader("version", "HTTP/1.1");
			request.addHeader("host", "api.twitter.com");
			request.setConnectionKeepAlive(true);
			request.addHeader("user-agent", "IdeaFact Task 8");
			
			service.signRequest(accessToken, request);
			Response response = request.send();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					response.getStream()));
			
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				sb.append(line);
			}
			JSONArray array=(JSONArray)JSONValue.parse(sb.toString());
			for (int i = 0; i < array.size(); i++) {
				JSONObject status = (JSONObject) array.get(i);
				
//				JSONObject status = (JSONObject)json.get("retweeted_status");

				Tweet tweet = new Tweet();
				tweet.setText((String) status.get("text"));
				JSONObject user = (JSONObject) status.get("user");
				tweet.setUsername((String) user.get("screen_name"));
				String createdAt = (String) status.get("created_at");
				String TWITTER = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
				SimpleDateFormat sf = new SimpleDateFormat(TWITTER);
				Date date = sf.parse(createdAt);
				tweet.setCreatedAt(date.toString());
				
				list.add(tweet);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	public static void main(String[] args){
		TwitterUtil twitter = new TwitterUtil();
		twitter.getUserTimeline("cmuuitest");
//		System.out.println(twitter.update(new Token("", ""), "test updating twitter"));
//		twitter.countOfSearch("Rabbit Beach");
//		twitter.countOfSearch("Grace Bay");
//		twitter.countOfSearch("White haven Beach");
//		twitter.countOfSearch("Baia do Sancho");
//		twitter.countOfSearch("Flamenco Beach");
//		twitter.countOfSearch("Lopes Mendes Beach");
//		twitter.countOfSearch("#");
//		twitter.countOfSearch("#");
		System.out.println();
//		twitter.search("Steelers");
//		twitter.requestToken();
	}

	
}
