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
            <h1>Buy Fund - Confirmation</h1>
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
  		      <label class="control-label" for="ticker">Ticker</label>
  		      <div class="controls">
  		        <input type="text" id="ticker" placeholder="ticker" name="ticker" value="${form.ticker}" disabled>
  		      </div>
  		    </div>
		    <div class="control-group">
		      <label class="control-label" for="fundName">Fund Name</label>
		      <div class="controls">
		      	<input type="text" id="name" placeholder="Input ticker to check" name="fundName" value="${form.fundName}" disabled>
		      </div>
		    </div>
		    <div class="control-group">
		      <label class="control-label" for="fundName">Latest Price</label>
		      <div class="controls">
		      	<input type="text" id="price" placeholder="Input ticker to check" name="fundPrice" value="${form.fundPrice}" disabled>
		      </div>
		    </div>
		    <div class="control-group">
		      <label class="control-label" for="fundName">Available Balance</label>
		      <div class="controls">
		      	<input type="text" id="balance" name="balance" value="${user.available}" disabled>
		      </div>
		    </div>
		    <div class="control-group">
		      <label class="control-label" for="amount">Amount of Money</label>
		      <div class="controls">
		        <input type="text" id="amount" placeholder="example: 29.50" name="amount" value="${form.amount}" disabled>
		      </div>
		    </div>
		    <div class="control-group">
		      <div class="controls">
		        <button type="submit" class="btn btn-primary" name="button" value="buy">Buy</button>
		        <a href="javascript:history.back()">
		        	<button type="button" class="btn" name="button" value="buy">Return</button>
		        </a>
		      </div>
		    </div>
		  </form>
		  
        </div><!--/span-->
      </div><!--/row-->

<jsp:include page="customerFooter.jsp" />