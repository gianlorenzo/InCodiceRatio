<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>


<html>
<head>
<meta charset="utf-8">
<title>ICR</title>

<!-- Bootstrap CSS -->

<link rel="stylesheet"
	href="<c:url value=" resources/css/bootstrap.responsive.css" />"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value=" resources/css/bootstrap.css" />" type="text/css">
<link rel="stylesheet"
	href="<c:url value=" resources/css/fontello-ie7.css" />"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value=" resources/css/fontello.css" />" type="text/css">
<link rel="stylesheet"
	href="<c:url value=" resources/css/prettyPhoto.css" />" type="text/css">
<link rel="stylesheet" href="<c:url value=" resources/css/style.css" />"
	type="text/css">


<!-- Favicon -->
<link rel="shortcut icon"
	href="<c:url value="resources/img/siteImages/favicon.ico"/>">




</head>

<body background="#ffffff">
	<!--******************** NAVBAR ********************-->
	<div class="navbar-wrapper">
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container">
					<!-- Responsive Navbar Part 1: Button for triggering responsive navbar (not covered in tutorial). Include responsive CSS to utilize. -->
					<a class="btn btn-navbar" data-toggle="collapse"
						data-target=".nav-collapse"> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
					</a>
					<h1 class="brand">
						<a href="index">In Codice Ratio</a>
					</h1>
					<nav class="pull-right nav-collapse collapse">
						<ul id="menu-main" class="nav">

							<a href="registrationAdmin"> Sign
									up new Admin</a> &nbsp;
							<a href="registration">Sign up new
									Account</a>


						</ul>

					</nav>

				</div>
				<!-- /.container -->
			</div>
			<!-- /.navbar-inner -->
		</div>
		<!-- /.navbar -->
	</div>

	<div align="center">
		<div style="position: inline; top: 40px;">

			<div>
				<form method="POST" name="f"
					action="<c:url value="/${pageContext.request.contextPath}/j_spring_security_check"/>">
					<h3>Username</h3>
					<input type='text' name='j_username' />

					<h3>Password</h3>
					<input type='password' name='j_password'>
					<div>
						<input name="submit" type="submit" value="Send">
					</div>
				</form>
			</div>
		</div>
	</div>




</body>
</html>