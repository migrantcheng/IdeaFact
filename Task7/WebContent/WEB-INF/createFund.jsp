	<jsp:include page="employeeHeader.jsp" />

    <div class="container">
      <div class="row">
        <jsp:include page="employeeNav.jsp" />
        <div class="span9">
          <div class="page-header">
            <h1>Create Fund</h1>
          </div>
           <form>
      <table>
        <tr>
          <td>Fund Name: </td>
          <td><input type="text" name="fundName" value="${form.fundName}"/></td>
         </tr>
        <tr>
          <td>Ticker: </td>
          <td><input type="text" name="ticker" value="${form.ticker}"/></td>
        </tr>
        <tr>
        <td colspan="2" align="center">
          <input class="btn btn-primary" type="submit" name="button" value="Submit"/>
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
