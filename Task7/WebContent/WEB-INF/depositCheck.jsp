	<jsp:include page="employeeHeader.jsp" />

    <div class="container">
      <div class="row">
        <jsp:include page="employeeNav.jsp" />
        <div class="span9">
          <div class="page-header">
            <h1>Deposit Check</h1>
          <form type="POST">
           <table width="298" border="0">
           <tr>
           <td width="543">Username:</td>
           </tr>
           <tr>
           <td><input type="text" name="username" id="username"></td>
           </tr>
           </table>


           <table width="298" border="0">
           <tr>
           <td width="327">Deposit Amount:</td>
           </tr>
           <tr>
           <td><input type="text" name="amount" id="amount" value="xxx.xx"></td>
           </tr>
           </table>
           <p>
           <input type="submit" name="button" id="submit" value="Submit">
           </p>
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
