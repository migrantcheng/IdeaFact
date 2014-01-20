	<jsp:include page="employeeHeader.jsp" />

    <div class="container">
      <div class="row">
        <jsp:include page="employeeNav.jsp" />
        <div class="span9">
          <div class="page-header">
            <h1>Create Employee Account</h1>
          </div>
          <form>
    <table>
      <tr>
        <td>Username: </td>
        <td><input type="text" name="username" value="${form.userName}"/></td>
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
        <td>First Name: </td>
        <td><input type="text" name="firstName" value="${form.firstName}"/></td>
      </tr>
      <tr>
        <td>Last Name: </td>
        <td><input type="text" name="lastName" value="${form.lastName}"/></td>
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
