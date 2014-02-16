<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@include file="header.jsp" %>
<div class="container">
	<div class="row">
		
        <div class="span8">

                <br>
                <br>
                    <img src="http://placehold.it/900x300" class="img-responsive">
                
                <p class="lead">Photo Title
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
                    <h4>Google Map API</h4>
                    
                    <!-- /input-group -->
                </div>
                
                <!-- /well -->
                <div class="well">
                    <h4>Side Widget Well</h4>
                    <p>Bootstrap's default well's work great for side widgets! What is a widget anyways...?</p>
                </div>
                <!-- /well -->
                <div class="well">
                    <h4>Trends</h4>
                    <p>Bootstrap's default well's work great for side widgets! What is a widget anyways...?</p>
                </div>
            </div>
        
	</div>
	
<!-- MASONRY ITEMS END -->
</div>
<%@include file="footer.jsp" %>
