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
    <style type="text/css">
      body {
        padding-top: 80px;
        padding-bottom: 40px; 
        background: url("${photo.url}");
        background-repeat: no-repeat;
        background-size: 100%;
      }

      .title {
        color: rgba(255,255,255,1);

        -webkit-text-shadow: 0 2px 2px rgba(0,0,0,.5);
           -moz-text-shadow: 0 2px 2px rgba(0,0,0,.5);
                text-shadow: 0 2px 2px rgba(0,0,0,.5);
      }
      button {
        -webkit-box-shadow: 0 2px 2px rgba(0,0,0,.5);
           -moz-box-shadow: 0 2px 2px rgba(0,0,0,.5);
                box-shadow: 0 2px 2px rgba(0,0,0,.5);
      }
      input {
        -webkit-box-shadow: 0 2px 2px rgba(0,0,0,.5);
           -moz-box-shadow: 0 2px 2px rgba(0,0,0,.5);
                box-shadow: 0 2px 2px rgba(0,0,0,.5);
      }
      .form-signin {
        max-width: 300px;
        padding: 10px 29px 29px;
        margin: 0 auto 20px;
        background-color: rgba(255,255,255,0.7);
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,0.5);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,0.5);
                box-shadow: 0 1px 2px rgba(0,0,0,0.5);
      }
      .form-signin .form-signin-heading {
        color: rgba(0,0,0,0.75);
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

    </style>
    <link href="css/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="ico/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="ico/apple-touch-icon-72-precomposed.png">
                    <link rel="apple-touch-icon-precomposed" href="ico/apple-touch-icon-57-precomposed.png">
                                   <link rel="shortcut icon" href="ico/favicon.png">
  </head>

  <body>

    <div class="container" style="margin-top:70px;">
      <div class="row">
        <div class="span6 offset3" style="text-align:center">
         <h1 class="title"><br>What's in your mind?<br>&nbsp;</h1>
        </div>
      </div>
      
      <div class="row">
        <div class="span6 offset3" style="text-align:center">
	      <form class="form-search" method="POST">
	        <input type="text" class="input-xlarge" name="key">
	        <button class="btn btn-primary" type="submit" name="button" value="signin">Search</button>
	      </form>
        </div>
      </div>

    </div> <!-- /container -->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/jquery-1.8.2.js"></script>
    <script src="js/bootstrap.min.js"></script>

  </body>
</html>
