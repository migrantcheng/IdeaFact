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
<!-- Le styles -->
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/prettyPhoto.css" rel="stylesheet">
<link href="css/icons.css" rel="stylesheet" media="screen">
<link href="css/custom.css" rel="stylesheet">
<link href="css/skindefault.css" rel="stylesheet">
<link href="css/bootstrap-responsive.css" rel="stylesheet">
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
	  <link rel="stylesheet" type="text/css" href="css/ie.css" />
    <![endif]-->
<!-- Jquery - The rest of the scripts at the bottom-->
<script src="js/jquery-1.9.0.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/twitter-bootstrap-hover-dropdown.js"></script>
<script src="js/common.js"></script>
<script src="js/jquery.prettyPhoto.js"></script>
<script src="js/jquery.isotope.min.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Keyword', 'Visits'],
          <c:if test="${fn:length(keys) gt 0}">
      		<c:forEach var="key" items="${keys}">
      		['${key.word}',	${key.frequency}],
            </c:forEach>
       	  </c:if>
        ]);

        var options = {
          title: 'Top Keywords',
        };

        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
</head>
<body>

<!-- Header
================================================== -->
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			</button>
			<a class="brand" href="index.do">IdeaFact.</a>
			<div class="nav-collapse collapse">
				<ul class="nav">
					<form class="navbar-search pull-left" action="list.do">
					  <input type="text" class="search-query" placeholder="Search" name="key">
					</form>
					<li><a href="index.do">Home</a></li>
					<%if (session.getAttribute("user") != null) { %>
					<li><a href="#">Hello, ${user.username}</a></li>
					<%} else { %>
					<li><a href="twitterSignIn.do">Sign in with twitter</a></li>
					<%}%>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
</div>
<div class="container">
	<!-- Filtering Menu
	================================================== -->
	<!-- <div class="row-fluid">
		<div class="span12 text-center">
			<div id="filter">
				<ul>
					<li><a href="" data-filter="*" class="selected"><i class="icon icon-reorder"></i> All Items</a></li>
					<li><a href="" data-filter=".cat1"><i class="icon icon-th-large"></i> Landscape</a></li>
					<li><a href="" data-filter=".cat2"><i class="icon icon-th-list"></i> Overview</a></li>
					<li><a href="" data-filter=".cat3"><i class="icon icon-th"></i> Green Nature</a></li>
				</ul>
			</div>
		</div>
	</div> -->
	<!-- END filtering menu -->
	<!-- MASONRY ITEMS START
	================================================== -->
	<div class="row">
		<div class="span9">
			<div id="content">	
				<!-- box 1 -->
				<c:forEach var="photo" items="${photos}">
				<div class="boxportfolio4">
					<div class="boxcontainer">
						<img src="${photo.urlm}" alt="">
						<div class="roll">
							<div class="wrapcaption">
								<a href="detail.do?id=${photo.id}"><i class="icon-link captionicons"></i></a>
								<a data-gal="prettyPhoto[gallery1]" href="${photo.url}" title="La Chaux De Fonds"><i class="icon-zoom-in captionicons"></i></a>
							</div>
						</div>
						<h1><a href="detail.do?id=${photo.id}">${photo.title}</a></h1>
					</div>
				</div>
				</c:forEach>
			</div>
		</div>
		
		<!-- twitter stream -->
		<div class="span3">
		<div id="chart_div" style="width: 100%; height: 300px;"></div>
		<br>
		<a class="twitter-timeline" href="https://twitter.com/search?q=%23travelling" data-widget-id="435515192969920512">Tweets about "#travelling"</a>
<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+"://platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>
		<a href="https://twitter.com/share" class="twitter-share-button" data-text="Check out this page:" data-size="large" data-hashtags="travelling">Share</a>
	</div>
	
	<div class="row">
	</div>
<!-- MASONRY ITEMS END -->
</div>
<%@include file="footer.jsp" %>
