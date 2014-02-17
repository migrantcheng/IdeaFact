package controller;

import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import Flickr.FlickrThread;
import Flickr.FlickrUtil;
import Flickr.PhotoList;

public class CategoryAction extends Action {
	
	private String[][] places = {{"Ka'anapali Beach", "Siesta Key Public Beach", "Gulf Islands National Seashore", "Fort De Soto Park", "Lanikai Beach", "Wailea Beach", "Assateague Beach", "La Jolla Cove", "Laguna Beach", "St Andrews State Park"}, 
								{"Ambergris Caye", "St John US Virgin Islands", "Bora Bora", "San Juan Island", "Santorini, Cyclades", "Isla Mujeres", "Moorea Society Islands", "Ko Tao", "Easter Island", "Nosy Be"},
								{"Iberostar Grand Hotel Paraiso", "Royalton Cayo Santa Maria", "The Beloved Hotel", "Excellence Playa Mujeres", "Le Blanc Spa Resort", "Paradisus Palma Real", "Kurumba Maldives", "Club Med Egypt", "Club Med Palmiye", "Cozumel Palace"},
								{"Mount Khuiten", "Kilimanjaro", "The Andes", "Mount Everest", "The Matterhorn", "Mount Elbrus", "Iztaccihuatl", "Denali", "Annapurna", "Damavand"},
								{"Peru Landscape", "New Zealand Landscape", "Chile Landscape", "Nepal Landscape", "Australia Landscape", "Netherlands Landscape", "Greece Landscape", "Utah Landscape", "Iceland Landscape", "Tanzania Landscape"},
								{"Aurora borealis", "Yellowstone National Park", "Plateau de Valensole", "Strokkur geyser", "The Wave Coyote Buttes", "Nideck waterfall", "Salar de Uyuni Salt Desert", "Plitvice Lakes National Park", "Mendenhall Glacier", "Antelope Canyon"},
								{"The Forbidden City", "The Great Pyramid", "Potala Palace", "Christ the Redeemer", "Parthenon", "Taj Mahal", "Alhambra", "Acropolis", "Sultan Ahmed Mosque", "Colosseum"}};
	private String[] widgets = {"<a class=\"twitter-timeline\" href=\"https://twitter.com/search?q=Beach\" data-widget-id=\"435509508417142784\">Tweets about \"Beach\"</a><script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+\"://platform.twitter.com/widgets.js\";fjs.parentNode.insertBefore(js,fjs);}}(document,\"script\",\"twitter-wjs\");</script>",
								"<a class=\"twitter-timeline\" href=\"https://twitter.com/search?q=Island\" data-widget-id=\"435508252994854912\">Tweets about \"Island\"</a><script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+\"://platform.twitter.com/widgets.js\";fjs.parentNode.insertBefore(js,fjs);}}(document,\"script\",\"twitter-wjs\");</script>",
								"<a class=\"twitter-timeline\" href=\"https://twitter.com/search?q=Resorts\" data-widget-id=\"435509177419436032\">Tweets about \"Resorts\"</a><script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+\"://platform.twitter.com/widgets.js\";fjs.parentNode.insertBefore(js,fjs);}}(document,\"script\",\"twitter-wjs\");</script>",
								"<a class=\"twitter-timeline\" href=\"https://twitter.com/search?q=Mountain+landscape\" data-widget-id=\"435510013407154176\">Tweets about \"Mountain landscape\"</a><script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+\"://platform.twitter.com/widgets.js\";fjs.parentNode.insertBefore(js,fjs);}}(document,\"script\",\"twitter-wjs\");</script>",
								"<a class=\"twitter-timeline\" href=\"https://twitter.com/search?q=Landscapes\" data-widget-id=\"435510468166156289\">Tweets about \"Landscapes\"</a><script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+\"://platform.twitter.com/widgets.js\";fjs.parentNode.insertBefore(js,fjs);}}(document,\"script\",\"twitter-wjs\");</script>",
								"<a class=\"twitter-timeline\" href=\"https://twitter.com/search?q=Fantastic+landscape\" data-widget-id=\"435510898103291904\">Tweets about \"Fantastic landscape\"</a><script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+\"://platform.twitter.com/widgets.js\";fjs.parentNode.insertBefore(js,fjs);}}(document,\"script\",\"twitter-wjs\");</script>",
								"<a class=\"twitter-timeline\" href=\"https://twitter.com/search?q=Historical+place\" data-widget-id=\"435512453766451200\">Tweets about \"Historical place\"</a><script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+\"://platform.twitter.com/widgets.js\";fjs.parentNode.insertBefore(js,fjs);}}(document,\"script\",\"twitter-wjs\");</script>"};
	private String[] types = {"Beaches", "Islands", "Resorts", "Mountains", "Landscapes", "Places", "Historical Places"};
	public String getName() { return "category.do"; }
    
    public String perform(HttpServletRequest request) {
    	int cat = 0;
    	try {
    		cat = Integer.parseInt(request.getParameter("cat"));
    		if (cat >= places.length || cat < 0) {
    			cat = 0;
    		}
    	} catch (Exception e) {
    		/* ignore */
    	}
		Random rand = new Random(System.currentTimeMillis()); 
		int value = 1;
    	int page = 1;
    	if (request.getParameter("page") != null && FlickrUtil.isInteger(request.getParameter("page"))) {
    		page = Integer.parseInt(request.getParameter("page"));
    	}
    	
    	
    	ArrayList<PhotoList> photoList = new ArrayList<PhotoList>();
    	Thread[] threads = new FlickrThread[places[cat].length];
    	for (int i = 0; i < places[cat].length; i++) {
    		value = rand.nextInt(10) + 1;
    		threads[i] = new FlickrThread(photoList, value, page, places[cat][i]);
    		threads[i].start();
    	}
    	
    	//test update twitter
//    	HttpSession session     = request.getSession(true);
//    	User user = (User) session.getAttribute("user");
//    	if(user != null){
//    		new TwitterUtil().update(new Token(user.getAccessToken(), user.getAccessTokenSecret()), "search for keywork: " + request.getParameter("key") + " from IdeaFact");
//    	}
    	//end test update twitter
    	try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        request.setAttribute("photoList", photoList);
        request.setAttribute("type", types[cat]);
        request.setAttribute("widget", widgets[cat]);
       	return "index.jsp";
    }
}
