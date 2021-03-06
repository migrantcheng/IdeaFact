package Flickr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;

import databean.FlickrPhoto;

public class FlickrUtil {
	private static final String BASE_URL = "http://api.flickr.com/services/rest/";
	private static final String API_KEY = "9bd62f1fe0f26fcde97a3b7cfe051cc9";  
	private static final String PER_PAGE = "200";  
	private static final String FORMAT = "json";  
	
	public FlickrUtil() {
		
	}
	
	public static ArrayList<FlickrPhoto> search(String key, int perPage, int page) {
		if (key == null || key.length() == 0) {
			return null;
		}
		try {
			OAuthRequest request = new OAuthRequest(Verb.GET, BASE_URL);
			request.addHeader("version", "HTTP/1.1");
			request.addHeader("host", "api.flickr.com");
			request.setConnectionKeepAlive(true);
			request.addHeader("user-agent", "IdeaFact Task 8");
//			request.addQuerystringParameter(key, value);
			request.addQuerystringParameter("method", "flickr.photos.search");
			request.addQuerystringParameter("api_key", API_KEY);
			request.addQuerystringParameter("per_page", Integer.toString(perPage));
			request.addQuerystringParameter("format", FORMAT);
			request.addQuerystringParameter("text", key);
			request.addQuerystringParameter("safe_search", "1");
			request.addQuerystringParameter("page", Integer.toString(page));
			// sort param can be set to different values
			// see: http://www.flickr.com/services/api/flickr.photos.search.htm
			// keyword: sort
			request.addQuerystringParameter("sort", "relevance"); 
			request.addQuerystringParameter("extras", "url_o"); 
			request.addQuerystringParameter("nojsoncallback", "1");
			
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
			if (json.get("stat").equals("fail")) {
				return new ArrayList<FlickrPhoto>();
			}
			JSONArray array = (JSONArray)((JSONObject)json.get("photos")).get("photo");
			System.out.println(array);
			ArrayList<FlickrPhoto> photos = new ArrayList<FlickrPhoto>();
			for(int i = 0; i < array.size(); i++){
				JSONObject photo = (JSONObject)array.get(i);
				photos.add(new FlickrPhoto(photo));
				System.out.println("http://www.flickr.com/photos/" + photo.get("owner") + "/" + photo.get("id") + "/");
			}
			return photos;
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return null;
	}
	
	public static FlickrPhoto getById(String id) {
		if (id == null || id.length() == 0) {
			return new FlickrPhoto();
		}
		try {
			OAuthRequest request = new OAuthRequest(Verb.GET, BASE_URL);
			request.addHeader("version", "HTTP/1.1");
			request.addHeader("host", "api.flickr.com");
			request.setConnectionKeepAlive(true);
			request.addHeader("user-agent", "IdeaFact Task 8");
//			request.addQuerystringParameter(key, value);
			request.addQuerystringParameter("method", "flickr.photos.getInfo");
			request.addQuerystringParameter("api_key", API_KEY);
			request.addQuerystringParameter("format", FORMAT);
			request.addQuerystringParameter("photo_id", id);
			// sort param can be set to different values
			// see: http://www.flickr.com/services/api/flickr.photos.search.htm
			// keyword: sort
			request.addQuerystringParameter("nojsoncallback", "1");
			
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
			if (json.get("stat").equals("fail")) {
				return new FlickrPhoto();
			}
			FlickrPhoto result = new FlickrPhoto();
			JSONObject photo = (JSONObject)json.get("photo");
			JSONObject title = (JSONObject)photo.get("title");
			JSONObject location = (JSONObject)photo.get("location");
			JSONObject urls = (JSONObject)photo.get("urls");
			JSONObject url = (JSONObject)((JSONArray)urls.get("url")).get(0);
			
			result.setId((String)photo.get("id"));
			try {
				result.setLat((double)(location.get("latitude")));
				result.setLon((double)(location.get("longitude")));
			} catch (Exception e) {
				/* ignore */
			}
			result.setTitle((String)title.get("_content"));
			result.setPageLink((String)url.get("_content"));
			result.setUrl("http://farm" + photo.get("farm") + ".staticflickr.com/" + photo.get("server") 
					+ "/" + photo.get("id") + "_" + photo.get("secret") + ".jpg");
			return result;
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<FlickrPhoto> searchByGeo(double lat, double lon, double radius, int perPage) {
		try {
			OAuthRequest request = new OAuthRequest(Verb.GET, BASE_URL);
			request.addHeader("version", "HTTP/1.1");
			request.addHeader("host", "api.flickr.com");
			request.setConnectionKeepAlive(true);
			request.addHeader("user-agent", "IdeaFact Task 8");
//			request.addQuerystringParameter(key, value);
			request.addQuerystringParameter("method", "flickr.photos.search");
			request.addQuerystringParameter("api_key", API_KEY);
			request.addQuerystringParameter("per_page", Integer.toString(perPage));
			request.addQuerystringParameter("format", FORMAT);
			request.addQuerystringParameter("lon", Double.toString(lon));
			request.addQuerystringParameter("lat", Double.toString(lat));
			request.addQuerystringParameter("radius", Double.toString(radius));
			request.addQuerystringParameter("safe_search", "1");
			// sort param can be set to different values
			// see: http://www.flickr.com/services/api/flickr.photos.search.htm
			// keyword: sort
			request.addQuerystringParameter("sort", "date-taken-desc"); 
			request.addQuerystringParameter("nojsoncallback", "1");
			
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
			if (json.get("stat").equals("fail")) {
				return new ArrayList<FlickrPhoto>();
			}
			JSONArray array = (JSONArray)((JSONObject)json.get("photos")).get("photo");
			System.out.println(array);
			ArrayList<FlickrPhoto> photos = new ArrayList<FlickrPhoto>();
			for(int i = 0; i < array.size(); i++){
				JSONObject photo = (JSONObject)array.get(i);
				photos.add(new FlickrPhoto(photo));
				System.out.println("http://www.flickr.com/photos/" + photo.get("owner") + "/" + photo.get("id") + "/");
			}
			return photos;
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return null;
	}
	
	public static void getPosition(FlickrPhoto photo) {
		try {
			OAuthRequest request = new OAuthRequest(Verb.GET, BASE_URL);
			request.addHeader("version", "HTTP/1.1");
			request.addHeader("host", "api.flickr.com");
			request.setConnectionKeepAlive(true);
			request.addHeader("user-agent", "IdeaFact Task 8");
//			request.addQuerystringParameter(key, value);
			request.addQuerystringParameter("method", "flickr.photos.geo.getLocation");
			request.addQuerystringParameter("api_key", API_KEY);
			request.addQuerystringParameter("format", FORMAT);
			request.addQuerystringParameter("photo_id", photo.getId());
			// sort param can be set to different values
			// see: http://www.flickr.com/services/api/flickr.photos.search.htm
			// keyword: sort
//			request.addQuerystringParameter("sort", "date-taken-desc"); 
			request.addQuerystringParameter("nojsoncallback", "1");
			
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
			if (json.get("stat").equals("fail")) {
				System.out.println("Warning: " + json.get("message"));
				return;
			}
			
			photo.setLat((double)((JSONObject)((JSONObject)json.get("photo")).get("location")).get("latitude"));
			photo.setLon((double)((JSONObject)((JSONObject)json.get("photo")).get("location")).get("longitude"));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
}
