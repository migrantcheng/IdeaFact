<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="customerHeader.jsp" />

    <div class="container">
      <div class="row">
        <div class="span3">
            <jsp:include page="customerNav.jsp" />
        </div><!--/span-->
        <div class="span9">
          <div class="page-header">
            <h1>Change Password</h1>
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
		  
    		  <form class="form-horizontal" method="POST">
      		    <div class="control-group">
      		      <label class="control-label" for="oldPwd">Old Password</label>
      		      <div class="controls">
      		        <input type="password" id="oldPwd" placeholder="" name="oldPwd">
      		      </div>
      		    </div>
    		    <div class="control-group">
    		      <label class="control-label" for="newPwd">New Password</label>
    		      <div class="controls">
    		        <input type="password" id="newPwd" placeholder="6 - 16 characters long" name="newPwd">
    		      </div>
    		    </div>
    		    <div class="control-group">
    		      <label class="control-label" for="confirmPwd">Confirm Password</label>
    		      <div class="controls">
    		        <input type="password" id="confirmPwd" placeholder="" name="confirmPwd">
    		      </div>
    		    </div>
    		    <div class="control-group">
    		      <div class="controls">
    		    
    		        <button type="submit" class="btn btn-primary" name="button" value="submit">Change Password</button>
    		      </div>
    		    </div>
    		  </form>
		  
        </div><!--/span-->
      </div><!--/row-->

<jsp:include page="customerFooter.jsp" />