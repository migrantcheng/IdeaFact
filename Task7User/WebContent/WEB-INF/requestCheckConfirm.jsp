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
            <h1>Request Check</h1>
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
			  <dt style="font-weight:normal">Available Cash</dt>
			  <dd>${customer.available}</dd>
			</dl>
		      	
  		    <dl class="dl-horizontal">
			  <dt style="font-weight:normal">Amount of Check</dt>
			  <dd>${stringAmount}</dd>
			</dl>
		    
		    <input type="hidden" id="ticker" name="amount" value="${form.amount / 100}">
		    <div class="control-group">
		      <div class="controls">
		        <button type="submit" class="btn btn-primary" name="button" value="confirm">Request Check</button>
		        <a href="javascript:history.back()">
		        	<button type="button" class="btn" name="button">Return</button>
		        </a>
		      </div>
		    </div>
		  </form>
		  
        </div><!--/span-->
      </div><!--/row-->

<jsp:include page="customerFooter.jsp" />