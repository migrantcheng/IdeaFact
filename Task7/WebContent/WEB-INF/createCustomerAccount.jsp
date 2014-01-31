	<jsp:include page="employeeHeader.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	
    <div class="container">
      <div class="row">
        <jsp:include page="employeeNav.jsp" />
        <div class="span9">
          <div class="page-header">
            <h1>Create Customer Account</h1>
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
  		      <label class="control-label">Username</label>
  		      <div class="controls">
  		        <input type="text" name="username" maxlength="16" value="${form.username}">
  		      </div>
  		    </div>
  		    <div class="control-group">
  		      <label class="control-label">First Name</label>
  		      <div class="controls">
  		        <input type="text" name="firstname" maxlength="30" value="${form.firstName}">
  		      </div>
  		    </div>
  		    <div class="control-group">
  		      <label class="control-label">Last Name</label>
  		      <div class="controls">
  		        <input type="text" name="lastname" maxlength="30" value="${form.lastName}">
  		      </div>
  		    </div>
  		    <div class="control-group">
  		      <label class="control-label">Address Line 1</label>
  		      <div class="controls">
  		        <input type="text" name="addrline1" maxlength="50" value="${form.addr_line1}">
  		      </div>
  		    </div>
  		    <div class="control-group">
  		      <label class="control-label">Address Line 2</label>
  		      <div class="controls">
  		        <input type="text" name="addrline2" maxlength="50" value="${form.addr_line2}">
  		      </div>
  		    </div>
  		    <div class="control-group">
  		      <label class="control-label">City</label>
  		      <div class="controls">
  		        <input type="text" name="city" maxlength="50" value="${form.city}">
  		      </div>
  		    </div>
  		    <div class="control-group">
  		      <label class="control-label">State</label>
  		      <div class="controls">
  		        <select class="form-control" name="state" value="${form.state}">
					<option value="AL">Alabama</option>
					<option value="AK">Alaska</option>
					<option value="AZ">Arizona</option>
					<option value="AR">Arkansas</option>
					<option value="CA">California</option>
					<option value="CO">Colorado</option>
					<option value="CT">Connecticut</option>
					<option value="DE">Delaware</option>
					<option value="DC">District Of Columbia</option>
					<option value="FL">Florida</option>
					<option value="GA">Georgia</option>
					<option value="HI">Hawaii</option>
					<option value="ID">Idaho</option>
					<option value="IL">Illinois</option>
					<option value="IN">Indiana</option>
					<option value="IA">Iowa</option>
					<option value="KS">Kansas</option>
					<option value="KY">Kentucky</option>
					<option value="LA">Louisiana</option>
					<option value="ME">Maine</option>
					<option value="MD">Maryland</option>
					<option value="MA">Massachusetts</option>
					<option value="MI">Michigan</option>
					<option value="MN">Minnesota</option>
					<option value="MS">Mississippi</option>
					<option value="MO">Missouri</option>
					<option value="MT">Montana</option>
					<option value="NE">Nebraska</option>
					<option value="NV">Nevada</option>
					<option value="NH">New Hampshire</option>
					<option value="NJ">New Jersey</option>
					<option value="NM">New Mexico</option>
					<option value="NY">New York</option>
					<option value="NC">North Carolina</option>
					<option value="ND">North Dakota</option>
					<option value="OH">Ohio</option>
					<option value="OK">Oklahoma</option>
					<option value="OR">Oregon</option>
					<option value="PA">Pennsylvania</option>
					<option value="RI">Rhode Island</option>
					<option value="SC">South Carolina</option>
					<option value="SD">South Dakota</option>
					<option value="TN">Tennessee</option>
					<option value="TX">Texas</option>
					<option value="UT">Utah</option>
					<option value="VT">Vermont</option>
					<option value="VA">Virginia</option>
					<option value="WA">Washington</option>
					<option value="WV">West Virginia</option>
					<option value="WI">Wisconsin</option>
					<option value="WY">Wyoming</option>
				</select>
  		      </div>
  		    </div>
  		    <div class="control-group">
  		      <label class="control-label">Zip</label>
  		      <div class="controls">
  		        <input type="text" name="zip" maxlength="5" value="${form.zip}">
  		      </div>
  		    </div>
		    <div class="control-group">
		      <label class="control-label">Password</label>
		      <div class="controls">
		        <input type="password" name="password" maxlength="16">
		      </div>
		    </div>
		    <div class="control-group">
		      <label class="control-label">Confirm Password</label>
		      <div class="controls">
		        <input type="password" name="confirmPassword" maxlength="16">
		      </div>
		    </div>
		    
		    <div class="control-group">
		      <div class="controls">
		        <button type="submit" class="btn btn-primary" name="button">Create</button>
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
