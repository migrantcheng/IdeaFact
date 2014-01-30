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
          
          <div class="panel panel-default">

  <!-- Table -->
  <table class="table table-striped">
    <thead>
    <tr>
              <th>User</th>
              <th>Name</th>
              <th>Last Transaction</th>
              <th style="text-align:right;">Cash</th>
              <th style="text-align:right;">Available</th>
              <th>Operation</th>
    </tr>
    </thead>
    <tbody>
    		<c:forEach var="customer" items="${customers}">
    		<tr>
    			<input type="hidden" name="customer_id" value="${customer.customer_id}">
    		  <td>${customer.username}</td>
              <td>${customer.name}</td>
              <td>${customer.lastTransactionDay}</td>
              <td><p class="text-right">$${customer.cash}</p></td>
              <td><p class="text-right">$${customer.available}</p></td>
              <td> 
              <a href="resetPwd.do?customer_username=${customer.username}"><button class="btn btn-success">Password</button></a>&nbsp;

              <a href="myaccount.do?customer_id=${customer.customer_id}"><button class="btn btn-info">Account</button></a>&nbsp;

              <a href="history.do?customer_id=${customer.customer_id}"><button class="btn btn-primary">History</button></a>&nbsp;

              <a href="depositCheck.do?customer_username=${customer.username}"><button class="btn btn-inverse">Deposit</button></a>

   			  
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
