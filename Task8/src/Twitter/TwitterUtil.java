package Twitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
import org.scribe.oauth.OAuthService;

public class TwitterUtil {
	
	private final static String CONSUMER_KEY = "JB903vkzwl7bOqWFjtooA";
	private final static String COMSUMER_SECRET = "u1sQpBvqpbazSMdWAWmaD5sXO3a3wnNUYcl3FNIqE";
	private final static String ACCESS_TOKEN = "2329310366-9EWyzVn75vEyEFM2YE45AArhOpjv1vfluIQGoyS";
	private final static String ACCESS_TOKEN_SECRET = "5ZKDQMLhLWUQH77NAWeWiNIkzwvJJoGmDDRx8WJpVMBy9";
	
	private final static String USER_TIMELINE_URL = "https://api.twitter.com/1.1/statuses/user_timeline.json";
	private final static String SEARCH_URL = "https://api.twitter.com/1.1/search/tweets.json";
	
	public void search(){
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
			request.addQuerystringParameter("q", "steelers");
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
	
	public static void main(String[] args){
		TwitterUtil twitter = new TwitterUtil();
		twitter.getUserTimeline("cmuuitest");
		System.out.println();
		twitter.search();
	}

	
}
