<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ICR</title>

<!--[if lte IE 8]><script src="resources/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="resources/css/registration.css" />
<!--[if lte IE 8]><link rel="stylesheet" href="resources/css/ie8.css" /><![endif]-->
<!--[if lte IE 9]><link rel="stylesheet" href="resources/css/ie9.css" /><![endif]-->


<!-- Favicon -->
<link rel="shortcut icon"
	href="<c:url value="resources/img/siteImages/favicon.ico"/>">



</head>
<body class="landing">

	<!-- Page Wrapper -->
	<div id="page-wrapper">

		<!-- Header -->
		<header id="header" class="alt">
			<h1>
				<a href="index.html">In Codice Ratio</a>
			</h1>
			<nav id="nav">
				<ul>
					<li class="special"><a href="#menu" class="menuToggle"><span>Menu</span></a>
						<div id="menu">
							<ul>
								<li><a href="login">Log In</a></li>
								<li><a href="index">Torna alla HomePage</a></li>
							</ul>
						</div></li>
				</ul>
			</nav>
		</header>
	</div>

	<div class="form">
      
    
      
      <div class="tab-content">
        <div id="signup">   
          <h1>Registrazione Admin</h1>
          
          <form:form method="post" action="addAdmin" modelAttribute="administrator" name="form">
          
          
          
          <div class="field-wrap">
            <label>
              Username<font size="3" color="red">${usernameError}</font>
            </label>
					<form:input type="text" path='username' placeholder="Username" />
          </div>
          
          <div class="field-wrap">
            <label>
              Password
            </label>
					<form:input type="password" path='password' placeholder="Password" />
          </div>
          
          <font color="white"><springForm:errors path="username" /></font> <font
					color="white"><springForm:errors path="password" /></font>
          
          <button type="submit" class="button button-block">Conferma</button> 
          </form:form>
          </div>
          
         

        
        <div id="login">   
         
        </div>
        
      </div><!-- tab-content -->
      
</div> <!-- /form -->
      
	<!-- Scripts -->
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/jquery.scrollex.min.js"></script>
	<script src="resources/js/jquery.scrolly.min.js"></script>
	<script src="resources/js/skel.min.js"></script>
	<script src="resources/js/util.js"></script>
	<!--[if lte IE 8]>-->
	<script src="resources/js/ie/respond.min.js"></script>
	<script src="resources/js/main.js"></script>
</body>
</html>