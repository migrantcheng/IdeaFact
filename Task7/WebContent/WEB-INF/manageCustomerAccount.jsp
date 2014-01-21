	<jsp:include page="employeeHeader.jsp" />
	
  	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    <div class="container">
      <div class="row">
        <jsp:include page="employeeNav.jsp" />
        <div class="span9">
          <div class="page-header">
            <h1>Manage Customer Account</h1>
            </div>
          
          <div class="panel panel-default">

  <!-- Table -->
  <table class="table table-striped">
    <thead>
    <tr>
              <th>User</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Cash</th>
              <th>Operation</th>
    </tr>
    </thead>
    <tbody>
    		<c:forEach var="customer" items="${customers}">
    		<tr>
    		  <td>${customer.username}</td>
              <td>${customer.firstname}</td>
              <td>${customer.lastname}</td> 
              <td>${customer.cash/100}</td>
              <td><a href="#"><button class="btn btn-info">Reset Passwrod</button></a>&nbsp;<a href="#"><button class="btn btn-primary">View Customer Account</button></a>&nbsp;<a href="#"><button class="btn btn-inverse">View Customer History</button></a></td>
   			</tr>
   			</c:forEach>

   </tbody>
 </table>
</div>
          



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
