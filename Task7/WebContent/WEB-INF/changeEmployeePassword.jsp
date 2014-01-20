	<jsp:include page="employeeHeader.jsp" />
	
    <div class="container">
      <div class="row">
        <jsp:include page="employeeNav.jsp" />
        <div class="span9">
          <div class="page-header">
            <h1>Change Password</h1>
          </div>
		  
		  <form class="form-horizontal">
  		    <div class="control-group">
  		      <label class="control-label" for="oldPwd">Old Password</label>
  		      <div class="controls">
  		        <input type="password" name="oldPwd" placeholder="OldPassword">
  		      </div>
  		    </div>
		    <div class="control-group">
		      <label class="control-label" for="newPwd">NewPassword</label>
		      <div class="controls">
		        <input type="password" name="newPwd" placeholder="NewPassword">
		      </div>
		    </div>
		    <div class="control-group">
		      <label class="control-label" for="confirmPwd">Confirm Password</label>
		      <div class="controls">
		        <input type="password" name="confirmPwd" placeholder="Confirm Password">
		      </div>
		    </div>
		    <div class="control-group">
		      <div class="controls">
		    
		        <button type="submit" class="btn" name="button">Change Password</button>
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
