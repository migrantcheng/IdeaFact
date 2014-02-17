<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@include file="mapheader.jsp" %>
<div class="container">
	<div class="row">
		
        <div class="span8" style="margin-top:20px;">

			<div class="row" style="text-align:center;">
                    <img src="${photo.url}" class="img-responsive" style="max-width:900px;">
                
            </div>
                <p class="lead" style="text-align:center;">${photo.title}
                </p>
                
			<hr>
			<c:forEach var="tweet" items="${tweetList}">
				<h4><i class="fa fa-clock-o"></i> ${tweet.username} Posted on ${tweet.createdAt}</h4>
                <p><i class="fa fa-clock-o"></i> ${tweet.text}</p>
                <hr>
			</c:forEach>
			
                <div>
                    <h4>Tweet a Comment:</h4>
                    <form method="POST">
                        <div class="form-group">
                            <textarea rows="3" name="tweetContent"></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary" name="button">Tweet</button>
                    </form>
                </div>


            </div>
        
        
		<!-- twitter stream -->
	<div class="span4">
                <div class="well">
                    <p>Map</p>
					<div style="width:100%;height:200px;">
				      <div id="map-canvas">
				      </div>
				    </div>
                    <!-- /input-group -->
                </div>
                
                <!-- /well -->
                <div class="well">
                    <h4>Side Widget Well</h4>
                    <p>Bootstrap's default well's work great for side widgets! What is a widget anyways...?</p>
                </div>
                <!-- /well -->
                <div class="well">
                    <div id="piechart" style="width: 350px; height: 350px;"></div>                </div>
            </div>
        
	</div>
	
<!-- MASONRY ITEMS END -->
</div>

<script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Tweet', 'Hours per Day'],
          ['Tweets',     ${tweetCount}],
          ['Retweets',      ${retweetCount}]
        ]);

        var options = {
          title: 'Tweet Activities'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
        chart.draw(data, options);
      }
    </script>

<%@include file="footer.jsp" %>
