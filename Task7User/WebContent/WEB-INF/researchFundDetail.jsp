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
            <h1>Fund History Detail - ${fund.name}</h1>
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
		  
		<div class="span5 offset1">
          <table class="table table-striped">
            <thead>
              <tr>
                <th>Date</th>
                <th width="50%">Price</th>
              </tr>
            </thead>
            <tbody>
            
            <c:if test="${fn:length(fundPriceList) gt 0}">
            <c:forEach var="fundPrice" items="${fundPriceList}">
              <tr>
                <td>${fundPrice.date}</td>
                <td>${fundPrice.price}</td>
              </tr>
            </c:forEach>
            </c:if>
            
            </tbody>
          </table>
          <div align="center">
          	<form class="form-horizontal" method="POST" action="buy.do">
          	<input type="hidden" id="ticker" name="ticker" value="${fund.symbol}">
          	<input type="hidden" id="ticker" name="amount" value="1">
            <button type="submit" class="btn btn-success inline" value="query" name="button">Buy</button>
            </form>
            <a href="research.do"><button class="btn inline">Return</button></a>
          </div>
        </div>
		  
        </div><!--/span-->
      </div><!--/row-->

<jsp:include page="customerFooter.jsp" />