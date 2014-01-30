<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Carnegie Financial Services</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }

      @media (max-width: 980px) {
        /* Enable use of floated navbar text */
        .navbar-text.pull-right {
          float: none;
          padding-left: 5px;
          padding-right: 5px;
        }
      }
    </style>
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="ico/apple-touch-icon-57-precomposed.png">
    <link rel="shortcut icon" href="ico/favicon.png">
    
   <script src="js/Charts.js"></script>             

  </head>

  <body>

    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" style="color:white; href="#">Carnegie Financial Services</a>
          <div class="nav-collapse collapse">
            <ul class="nav pull-right">
              <li><a href="myaccount.do">Hello, ${customer.username}</a></li>
              <li><a href="myaccount.do">My Account</a></li>
              <li><a href="changePwd.do">Change Password</a></li>
              <li><a href="logout.do">Logout</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container">
      <div class="row">
        <div class="span3">
            <jsp:include page="customerNav.jsp" />
        </div><!--/span-->
        <div class="span9">
          <div class="page-header">
            <h1>Fund History Detail - ${fund.name}</h1>
          </div>

          <c:if test="${fn:length(errors) gt 0}">
            <div class="row">
              <div class="span9">
                <div class="alert alert-error">
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
              <div class="span9">
                <div class="alert alert-success">
                  <button type="button" class="close" data-dismiss="alert">&times;</button>
            	  <c:forEach var="message" items="${messages}">
                  <li>${message}</li>
                  </c:forEach>
                </div>
              </div>
            </div>
          </c:if>
		  
          <canvas id="myChart" width="800" height="400"></canvas>
		  <script>
		  var data = {
					labels : [<c:forEach var="fundPrice" items="${fundPriceList}">"${fundPrice.date}",</c:forEach>],
					datasets : [
						{
							fillColor : "rgba(220,220,220,0.5)",
							strokeColor : "rgba(220,220,220,1)",
							pointColor : "rgba(220,220,220,1)",
							pointStrokeColor : "#fff",
							data : [<c:forEach var="fundPrice" items="${fundPriceList}">${fundPrice.realPrice},</c:forEach>]
						}
					]
				}
		  
		  var myLine = new Chart(document.getElementById("myChart").getContext("2d")).Line(data);
		  </script>
		 
		<div class="span5 offset1">
          <table class="table table-striped">
            <thead>
              <tr>
                <th>Date</th>
                <th style="text-align:right;" width="50%">Price</th>
              </tr>
            </thead>
            <tbody>
            
            <c:if test="${fn:length(fundPriceList) gt 0}">
            <c:forEach var="fundPrice" items="${fundPriceList}">
              <tr>
                <td>${fundPrice.date}</td>
                <td style="text-align:right;">${fundPrice.price}</td>
              </tr>
            </c:forEach>
            </c:if>
            
            </tbody>
          </table>
          <div align="center">
          	<a href="buy.do?ticker=${fund.symbol}&amount=10&button=query">
            	<button type="submit" class="btn btn-success" value="query" name="button">Buy</button>
            </a>
            <a href="research.do"><button class="btn inline">Return</button></a>
          </div>
        </div>
		  
        </div><!--/span-->
      </div><!--/row-->

<jsp:include page="customerFooter.jsp" />