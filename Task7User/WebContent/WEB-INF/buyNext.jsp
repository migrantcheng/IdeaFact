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
			
  		    <input type="hidden" id="ticker" name="ticker" value="${fund.symbol}">
		    <input type="hidden" id="name" name="fundName" value="${fund.name}">
		    <input type="hidden" id="price" name="fundPrice" value="${latestPrice}">
		    <input type="hidden" id="balance" name="balance" value="${customer.available}">
		      	
		    <div class="control-group">
		      <label class="control-label" for="amount">Amount of Money</label>
		      <div class="controls">
		        $<input type="text" id="amount" placeholder="minimum amount is 10.00" name="amount" maxlength="10">
		      </div>
		    </div>
		    <div class="control-group">
		      <div class="controls">
		        <button type="submit" class="btn btn-primary" name="button" value="next">Next</button>
		        <a href="javascript:history.go(-1);">
		        	<button type="button" class="btn" name="button">Cancel</button>
		        </a>
		      </div>
		    </div>
		  </form>
		  
        </div><!--/span-->
      </div><!--/row-->

<jsp:include page="customerFooter.jsp" />