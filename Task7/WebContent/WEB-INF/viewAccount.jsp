<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="employeeHeader.jsp" />

    <div class="container">
      <div class="row">
        <div class="span3">
            <jsp:include page="employeeNav.jsp" />
        </div><!--/span-->
        <div class="span9">
          <div class="page-header">
            <h1>View Customer Account</h1>
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
		  
          <dl class="dl-horizontal">
            <dt>Name:</dt>
            <dd>${customer.firstname} ${customer.lastname}</dd>
          </dl>
          <dl class="dl-horizontal">
            <dt>Address:</dt>
            <dd>${customer.addr_line1}<br>${customer.addr_line2}<br>${customer.city}<br>${customer.state} ${customer.zip}</dd>
          </dl>
          <dl class="dl-horizontal">
            <dt>Last Trading Date:</dt>
            <dd>${lastTransactionDate}</dd>
          </dl>
          <dl class="dl-horizontal">
            <dt>Cash Balance</dt>
            <dd>$${stringCash}</dd>
          </dl>
          <dl class="dl-horizontal">
            <dt>Available Balance</dt>
            <dd>$${stringAvailable}</dd>
          </dl>
          
		  <hr>
		  
          <h4>Position Information</h4>
		    <table class="table table-striped">
		      <thead>
		        <tr>
		          <th>Ticker</th>
		          <th>Fund Name</th>
		          <th>Amount of Position</th>
		          <th>Value</th>
		          <th>Sell</th>
		        </tr>
		      </thead>
		      <tbody>
		      <c:if test="${fn:length(positionList) gt 0}">
		      <c:forEach var="position" items="${positionList}">
		      <form class="form-horizontal" method="POST" action="sell.do">
		      <input type="hidden" id="ticker" name="ticker" value="${position.fund.symbol}">
		      <input type="hidden" id="ticker" name="amount" value="1">
		      <tr>
		        <td>${position.fund.symbol}</td>
		        <td>${position.fund.name}</td>
		        <td>${position.shares}</td>
		        <td>$${position.latestPrice}</td>
		        <td><button type="submit" class="btn btn-primary" name="button" value="query">Sell Fund</button></td>
		      </tr>
		      </form>
		      </c:forEach>
		      </c:if>
		      <tbody>
			</table>
			
		  <hr>
		  
		  <h4>Pending Transactions</h4>
		  <table class="table table-striped">
            <thead>
              <tr>
                <th>Date</th>
                <th>Operation</th>
                <th>Fund Name</th>
                <th>Number of Shares</th>
                <th>Share Prices</th>
                <th>Dollar Amount</th>
              </tr>
            </thead>
            <tbody>
            
            <c:forEach var="transaction" items="${transactionList}">
            <tr>
              <td>${transaction.stringDate}</td>
              <td>${transaction.operation}</td>
              <td>${transaction.fund.name}</td>
              <td>${transaction.stringShares}</td>
              <td>${transaction.stringUnitPrice}</td>
              <td>${transaction.stringAmount}</td>
            </tr>
            </c:forEach>
            
            <tbody>
          </table>
		  
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
