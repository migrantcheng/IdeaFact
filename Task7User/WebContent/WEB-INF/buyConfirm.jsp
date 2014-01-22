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
            <h1>Buy Fund</h1>
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
  		    <dl class="dl-horizontal">
			  <dt style="font-weight:normal">Ticker</dt>
			  <dd>${fund.symbol}</dd>
			</dl>
  		    <dl class="dl-horizontal">
			  <dt style="font-weight:normal">Fund Name</dt>
			  <dd>${fund.name}</dd>
			</dl>
  		    <dl class="dl-horizontal">
			  <dt style="font-weight:normal">Latest Price</dt>
			  <dd>$${latestPrice}</dd>
			</dl>
  		    <dl class="dl-horizontal">
			  <dt style="font-weight:normal">Available Balance</dt>
			  <dd>$${stringAvailable}</dd>
			</dl>
  		    <dl class="dl-horizontal">
			  <dt style="font-weight:normal">Amount</dt>
			  <dd>$${stringAmount}</dd>
			</dl>
			
  		    <input type="hidden" id="ticker" name="ticker" value="${fund.symbol}">
		    <input type="hidden" id="name" name="fundName" value="${fund.name}">
		    <input type="hidden" id="price" name="fundPrice" value="${latestPrice}">
		    <input type="hidden" id="balance" name="balance" value="${customer.available}">
		    <input type="hidden" id="amount" name="amount" value="${form.amount / 100}">
		      	
		    <div class="control-group">
		      <div class="controls">
		        <button type="submit" class="btn btn-primary" name="button" value="buy">Buy</button>
		        <a href="javascript:history.back()">
		        	<button type="button" class="btn" name="button">Return</button>
		        </a>
		      </div>
		    </div>
		  </form>
		  
        </div><!--/span-->
      </div><!--/row-->

<jsp:include page="customerFooter.jsp" />