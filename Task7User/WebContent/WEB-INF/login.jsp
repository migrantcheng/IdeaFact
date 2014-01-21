<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Sign in &middot; Carnegie Financial Services</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px; 
        background: url("img/goldcoins.jpg");
        background-repeat: no-repeat;
        background-size: 100%;
      }

      .title {
        color: rgba(255,255,255,1);

        -webkit-text-shadow: 0 1px 2px rgba(0,0,0,.5);
           -moz-text-shadow: 0 1px 2px rgba(0,0,0,.5);
                text-shadow: 0 1px 2px rgba(0,0,0,.5);
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

    <div class="container">
      <div class="row">
        <div class="span6 offset3" style="text-align:center">
         <h1 class="title"><br>Carnegie Financial Services<br>&nbsp;</h1>
        </div>
      </div>
      
      <c:if test="${fn:length(errors) gt 0}">
      <div class="row">
        <div class="span4 offset4">
          <div class="alert alert-error" style="box-shadow: 0 1px 2px rgba(0,0,0,0.5);background-color:rgba(242,222,222, 0.8);">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
			<c:forEach var="error" items="${errors}">
            <li>${error}</li>
            </c:forEach>
          </div>
        </div>
      </div>
      </c:if>
      
      <c:if test="${fn:length(messages) gt 0}">
      <div class="row">
        <div class="span4 offset4">
          <div class="alert alert-success" style="box-shadow: 0 1px 2px rgba(0,0,0,0.5);background-color:rgba(223,240,216, 0.8);">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
			<c:forEach var="message" items="${messages}">
            <li>${message}</li>
            </c:forEach>
          </div>
        </div>
      </div>
      </c:if>
      
      <form class="form-signin" method="POST">
        <h2 class="form-signin-heading">Sign in</h2>
        <input type="text" class="input-block-level" placeholder="User Name" name="username">
        <input type="password" class="input-block-level" placeholder="Password" name="password">
        <button class="btn btn-large btn-primary" type="submit" name="button" value="signin">Sign in</button>
      </form>

    </div> <!-- /container -->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>

  </body>
</html>
