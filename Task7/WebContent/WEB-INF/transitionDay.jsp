	<jsp:include page="employeeHeader.jsp" />
	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    <div class="container">
      <div class="row">
        <jsp:include page="employeeNav.jsp" />
        <div class="span9">
          
		<div class="page-header">
            <h1>Transition Day</h1>
          </div>
          <p>Date: <input type="text" id="datepicker"></p>
          <tr>
            <tr>
              <tr>
                <tr>

          <table class="table table-striped">
    <thead>
    <tr>
              <th>Ticker</th>
              <th>Fund Name</th>
              <th>Closing Price</th>
              
    </tr>
    </thead>
    <tbody>
    		<c:forEach var="fund" items="${funds}">
    		<tr>
    		  <td>${fund.symbol}</td>
              <td>${fund.name}</td>
              <td>$<input type="text" name="textfield" id="price"></td> 
              </tr>
   			</c:forEach>
  
   </tbody>
 </table>
 <tr><a href="#"><button class="btn btn-info" name="button"> Submit </button></a></tr>


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