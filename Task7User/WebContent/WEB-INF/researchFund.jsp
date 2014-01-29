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
            <h1>Research Fund</h1>
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
		  
    	  <table class="table table-striped">
            <thead>
              <tr>
                <th>Ticker</th>
                <th>Fund Name</th>
                <th style="text-align:right;">Latest Price</th>
                <th>Detail</th>
                <th>Operation</th>
              </tr>
            </thead>
            <tbody>
            <c:if test="${fn:length(fundList) gt 0}">
            <c:forEach var="fund" items="${fundList}">
              <tr>
                <td>${fund.fund.symbol}</td>
                <td>${fund.fund.name}</td>
                <td style="text-align:right;">${fund.latestPrice}</td>
                <td><a href="research.do?id=${fund.fund.fund_id}"><button class="btn btn-info">View Details</button></a></td>
                <form class="form-horizontal" method="POST" action="buy.do">
	          	<input type="hidden" id="ticker" name="ticker" value="${fund.fund.symbol}">
	          	<input type="hidden" id="ticker" name="amount" value="10">
                <td><button type="submit" class="btn btn-success" value="query" name="button">Buy</button></td>
                </form>
              </tr>
            </c:forEach>
            </c:if>
            </tbody>
          </table>
		  
        </div><!--/span-->
      </div><!--/row-->

<jsp:include page="customerFooter.jsp" />