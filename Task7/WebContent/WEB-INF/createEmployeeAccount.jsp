<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Carnegie Financial Services</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }

      @media (max-width: 980px) {
        /* Enable use of floated navbar text */
        .navbar-text.pull-right {
          float: none;
          padding-left: 5px;
          padding-right: 5px;
        }
      }
    </style>
    <link href="css/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="ico/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="ico/apple-touch-icon-72-precomposed.png">
                    <link rel="apple-touch-icon-precomposed" href="ico/apple-touch-icon-57-precomposed.png">
                                   <link rel="shortcut icon" href="ico/favicon.png">
  </head>

  <body>

    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#">Carnegie Financial Services</a>
          <div class="nav-collapse collapse">
            <ul class="nav pull-right">
              <li><a href="#about">Change Password</a></li>
              <li><a href="#contact">Logout</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

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