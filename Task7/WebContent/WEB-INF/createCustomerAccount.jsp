	<jsp:include page="employeeHeader.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	
    <div class="container">
      <div class="row">
        <jsp:include page="employeeNav.jsp" />
        <div class="span9">
          <div class="page-header">
            <h1>Create Customer Account</h1>
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
                   <form method="POST">
              <table>
                <tr>
                  <td>Username: </td>
                  <td><input type="text" name="username" value="${form.userName}"/></td>
                </tr>
                <tr>
                  <td>First Name: </td>
                  <td><input type="text" name="firstname" value="${form.firstName}"/></td>
                </tr>
                <tr>
                  <td>Last Name: </td>
                  <td><input type="text" name="lastname" value="${form.lastName}"/></td>
                </tr>
                <tr>
                  <td>Address: </td>
                  <td><input type="text" name="addrline1" value="${form.addrLine1}"/></td>
                </tr>
                <tr>
                  <td>Address2: </td>
                  <td><input type="text" name="addrline2" value="${form.addrLine2}"/></td>
                </tr>
                <tr>
                  <td>City: </td>
                  <td><input type="text" name="city" value="${form.city}"/></td>
                </tr>
                <tr>
                  <td>State: </td>
                  <td><input type="text" name="state" value="${form.state}"/></td>
                </tr>
                <tr>
                  <td>Zip: </td>
                  <td><input type="text" name="zip" value="${form.zip}"/></td>
                </tr>
                <tr>
                  <td>Password: </td>
                  <td><input type="password" name="password" value=""/></td>
                </tr>
                <tr>
                  <td>Confirm Password: </td>
                  <td><input type="password" name="confirmPassword" value=""/></td>
                </tr>
                <tr>
                  <td colspan="2" align="center">
                    <input class="btn btn-primary" type="submit" name="button" value="Create"/>
                  </td>
                </tr>
              </table>
            </form>
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
