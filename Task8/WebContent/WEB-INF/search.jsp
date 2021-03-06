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
                    <p>Where's your next destination?</p>
                </div>
            </div>

            <!-- Search form -->
            <div class="row-fluid">
                <div class="subscription-form span12">
                    <form class="form-inline" method="post" action="list.do">
                        <input type="text" name="key" placeholder="Enter keyword">
                        <button type="submit" class="btn" name="button" value="signin">Search</button>
                    </form><span style="color:white;text-shadow: 0 1px 1px rgba(0,0,0,.8);margin-left:10px;font-size:20px;">Top 5 key words</span>
                    <ul class="hot-keyword">
						
                   	  <c:if test="${fn:length(keys) gt 0}">
			      		<c:forEach var="key" items="${keys}">
						<li>
                    		<a href="list.do?key=${key.word}">#${key.word}</a>
                    	</li>
			            </c:forEach>
			       	  </c:if>
                    	
                    </ul>
                    <div class="success-message"></div>
                    <div class="error-message"></div>
                </div>
                
                <!-- <div class="span12" style="padding-left:10px;">
	                <script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+"://platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>
	                <a href="https://twitter.com/share" class="twitter-share-button" data-text="Check out this page:" data-size="large" data-hashtags="travelling">Share</a>
                </div>   -->
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
						<li><a href="category.do?cat=0">
							<img src="image/beach.jpg" alt="Melbourne Meetup">
							</a>
							<br/><span>Beaches</span>
						</li>
						<li><a href="category.do?cat=1">
							<img src="image/sun-island.jpg" alt="Melbourne Meetup">
							</a>
							<br/><span>Islands</span>
						</li>
						<li><a href="category.do?cat=2">
							<img src="image/resort.png" alt="Melbourne Meetup">
							</a>
							<br/><span>Resorts</span>
						</li>
						
						<li><a href="category.do?cat=3">
							<img src="image/mount.png" alt="Melbourne Meetup"></a>
						<br/><span>Mountains</span>
						</li>
						<li><a href="category.do?cat=4">
							<img src="image/greece.png" alt="Melbourne Meetup">
							</a>
							<br/><span>Landscapes</span>
						</li>
						<li><a href="category.do?cat=5">
							<img src="image/aurora.png" alt="Melbourne Meetup"></a>
						<br/><span>Fantastic Places</span>
						</li>
						<li><a href="category.do?cat=6">
							<img src="image/mosque.png" alt="Melbourne Meetup"></a>
						<br/><span>Historical Places</span>
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

