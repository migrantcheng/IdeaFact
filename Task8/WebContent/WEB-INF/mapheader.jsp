<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
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
<script type="text/javascript"
     src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBqw34tX8lso7zG-1Pgn1DRp7eKUogbaXI&sensor=false">
</script>

<script type="text/javascript">
      function initialize() {
        var mapOptions = {
          center: new google.maps.LatLng(${photo.lat}, ${photo.lon}),
          zoom: 8,
          disableDefaultUI: true
        };
        var map = new google.maps.Map(document.getElementById("map-canvas"),
            mapOptions);

        var marker = new google.maps.Marker({
          position: map.getCenter(),
          map: map,
          title: 'Click to zoom'
        });
      }
      google.maps.event.addDomListener(window, 'load', initialize);
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