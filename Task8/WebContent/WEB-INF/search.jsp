<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <title>IdeaFact</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,400,700'>
        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=Patua+One'>
        <link rel="stylesheet" href="css/search/bootstrap.min.css">
        <link rel="stylesheet" href="css/style-s.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="ico/apple-touch-icon-57-precomposed.png">

    </head>

    <body>

        <div class="page-container container-fluid">

            
            <!-- Page title -->
            <div class="row-fluid">
                <div class="page-title span12">
                    <br/><h1>IdeaFact</h1>
                </div>
            </div>
			
			<!-- login -->
			<div class="login">
			<%if (session.getAttribute("user") == null) { %>
			  <a href="twitterSignIn.do">
				<button type="submit" class="btn1">
					<img src="image/tweets.png" class="tweet"> 
					Login with Twitter</button>
			  </a>
			<%} else {%>
				<button class="btn1">
					<img src="image/tweets.png" class="tweet"> 
					Hello, ${user.username}
				</button>
			<%} %>
			</div>
			
			
            <!-- Page description -->
            <div class="row-fluid">
                <div class="page-description span6">
                    <p>What's in your mind</p>
                </div>
            </div>

            <!-- Search form -->
            <div class="row-fluid">
                <div class="subscription-form span12">
                    <form class="form-inline" method="post">
                        <input type="text" name="key" placeholder="Enter keyword...">
                        <button type="submit" class="btn" name="button" value="signin">Search</button>
                    </form>
                    <div class="success-message"></div>
                    <div class="error-message"></div>
                </div>
            </div>
			
			
            <!-- Page description -->
            <div class="row-fluid">
                <div class="page-description span6" style="
	margin-top: 100px;">
                    <p>Top tens of</p>
                </div>
            </div>
			
            <!-- hot pic -->
            <div class="row-fluid">
				    <ul class="flickr-feed">
						<li><a href="category.do">
							<img src="image/beach.jpg" alt="Melbourne Meetup">
							</a>
							<br/><span>Beach</span>
						</li>
						<li><a href="category.do">
							<img src="image/nature.jpg" alt="Melbourne Meetup">
							</a>
							<br/><span>Nature</span>
						</li>
						<li><a href="http://www.flickr.com/photos/we-are-envato/12333892565/" target="_blank">
							<img src="image/sun-island.jpg" alt="Melbourne Meetup">
							</a>
							<br/><span>Island</span>
						</li>
						<li><a href="http://www.flickr.com/photos/we-are-envato/12333892565/" target="_blank">
							<img src="image/lavender.jpg" alt="Melbourne Meetup">
							</a>
							<br/><span>Flower</span>
						</li>
						<li><a href="http://www.flickr.com/photos/we-are-envato/12334281344/" target="_blank"><img src="image/s2.jpg" alt="Melbourne Meetup"></a>
						<br/><span>City</span>
					</li>
						<li><a href="http://www.flickr.com/photos/we-are-envato/12333834195/" target="_blank"><img src="image/s3.jpg" alt="Melbourne Meetup"></a>
						<br/><span>Sunset</span>
					</li>
					<li><a href="http://www.flickr.com/photos/we-are-envato/12333834195/" target="_blank"><img src="image/mountain.jpg" alt="Melbourne Meetup"></a>
					<br/><span>Mountain</span>
				</li>
					</ul>
				
            </div>

        </div>
        

        <!-- Javascript -->
        <script src="js/jquery-1.8.2.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.countdown.js"></script>
        <script src="js/jquery.backstretch.min.js"></script>
        <script src="js/jquery.tweet.js"></script>
        <script src="js/jflickrfeed.js"></script>
        <script src="js/backstretch.init.js"></script>
        <script src="js/scripts.js"></script>

    </body>

</html>

