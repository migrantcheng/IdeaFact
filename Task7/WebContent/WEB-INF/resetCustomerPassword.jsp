<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="employeeHeader.jsp" />

    <div class="container">
      <div class="row">
            <jsp:include page="employeeNav.jsp" />
        <div class="span9">
          <div class="page-header">
            <h1>Reset Password</h1>
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
      		    <dl class="dl-horizontal" style="font-weight:normal">
            		<dt style="font-weight:normal;">Username</dt>
            		<dd>${customer_username}</dd>
            		<input type="hidden" name="username" value="${customer_username}">
          		</dl>
    		    <div class="control-group">
    		      <label class="control-label" for="newPwd">New Password</label>
    		      <div class="controls">
    		        <input type="password" id="newPwd" name="newPwd">
    		      </div>
    		    </div>
    		    <div class="control-group">
    		      <label class="control-label" for="confirmPwd">Confirm Password</label>
    		      <div class="controls">
    		        <input type="password" id="confirmPwd"  name="confirmPwd">
    		      </div>
    		    </div>
    		    <div class="control-group">
    		      <div class="controls">
    		    
    		        <button type="submit" class="btn btn-primary" name="button" value="submit">Reset Password</button>
    		      </div>
    		    </div>
    		  </form>
		  
        </div><!--/span-->
      </div><!--/row-->

	<hr>
	<footer>
        <p>&copy; Team IdeaFact 2014</p>
      </footer>
      
      </div><!--/.fluid-container-->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/bootstrap-transition.js"></script>
    <script src="js/bootstrap-alert.js"></script>
    <script src="js/bootstrap-modal.js"></script>
    <script src="js/bootstrap-dropdown.js"></script>
    <script src="js/bootstrap-scrollspy.js"></script>
    <script src="js/bootstrap-tab.js"></script>
    <script src="js/bootstrap-tooltip.js"></script>
    <script src="js/bootstrap-popover.js"></script>
    <script src="js/bootstrap-button.js"></script>
    <script src="js/bootstrap-collapse.js"></script>
    <script src="js/bootstrap-carousel.js"></script>
    <script src="js/bootstrap-typeahead.js"></script>

  </body>
</html>
      