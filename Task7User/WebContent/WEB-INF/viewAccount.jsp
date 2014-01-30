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
            <dd>${customer.addr_line1}<c:if test="${fn:length(customer.addr_line2) gt 0}"><br></c:if>${customer.addr_line2}<br>${customer.city}<br>${customer.state} ${customer.zip}</dd>
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
		          <th style="text-align:right;">Amount of Position</th>
		          <th style="text-align:right;">Value</th>
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
		        <td style="text-align:right;">${position.shares}</td>
		        <td style="text-align:right;">$${position.latestPrice}</td>
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
                <th style="text-align:right;">Number of Shares</th>
                <th style="text-align:right;">Share Price</th>
                <th style="text-align:right;">Dollar Amount</th>
              </tr>
            </thead>
            <tbody>
            
            <c:forEach var="transaction" items="${transactionList}">
            <tr>
              <td>${transaction.stringDate}</td>
              <td>${transaction.operation}</td>
              <td>${transaction.fund.name}</td>
              <td style="text-align:right;">${transaction.stringShares}</td>
              <td style="text-align:right;">${transaction.stringUnitPrice}</td>
              <td style="text-align:right;">${transaction.stringAmount}</td>
            </tr>
            </c:forEach>
            
            <tbody>
          </table>
		  
        </div><!--/span-->
      </div><!--/row-->

<jsp:include page="customerFooter.jsp" />