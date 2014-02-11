package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class IndexAction extends Action {

	

	public IndexAction() {
		
	}

	public String getName() { return "index.do"; }
    
    public String perform(HttpServletRequest request) {
    		
    	String flickrBaseUrl = "http://api.flickr.com/services/rest/";  
        String flickrMethod = "flickr.photos.search";  
        String flickrAPIKey = "9bd62f1fe0f26fcde97a3b7cfe051cc9";  
        String flickrPerPage = "200";  
        String flickrFormat = "json";  
        String flickrTags = "cancun beach";  
        String jsonData = getFlickrImage(flickrBaseUrl, flickrMethod, flickrAPIKey, flickrPerPage, flickrFormat, flickrTags); 
       
        HttpSession session     = request.getSession(true);
        session.setAttribute("json", jsonData);
        session.setAttribute("hello", "hello");
       	return ("index.jsp");
    }

    
    public static String getFlickrImage(String flickrBaseUrl, String flickrMethod, String flickrAPIKey, String flickrPerPage, String flickrFormat, String flickrTags){  
        String url = flickrBaseUrl + "?method=" + flickrMethod + "&api_key=" + flickrAPIKey + "&per_page=" + flickrPerPage + "&format=" + flickrFormat + "&tags=" + flickrTags + "&extras=original_format";  
        StringBuffer sb = new StringBuffer();  
        InputStream is = null;  
        InputStreamReader isr = null;  
        BufferedReader br = null;  
        try{  
            is = new URL(url).openConnection().getInputStream();  
            isr = new InputStreamReader(is, "UTF-8");  
            br = new BufferedReader(isr);  
            String line = null;  
            while(null != (line=br.readLine())){  
                sb.append(line);  
            }  
            System.out.println(sb);  
            return sb.toString();
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }finally{  
            if(null != br){  
                try {  
                    br.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
            if(null != isr){  
                try {  
                    isr.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
            if(null != is){  
                try {  
                    is.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        } 
        return "";
    }  
    
}
