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
<body>

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
					<!-- Responsive Navbar Part 2: Place all navbar contents you want collapsed withing .navbar-collapse.collapse. -->
					<nav class="pull-right nav-collapse collapse">
						<ul id="menu-main" class="nav">
							<a title="login" href="login">Administrator's
									registration page</a>

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
		<div>
			<h2>Create a new Administrator</h2>
		</div>
		<form:form method="post" action="confirmAdmin"
			modelAttribute="administrator" name="form">
			<div>
				<h3>Username:</h3>
				<font size="3" color="red">${usernameError}</font>

				<form:input type="text" path="username" placeholder="Username" />
			</div>

			<div>
				<h3>Password:</h3>
				<form:input type="password" path="password" placeholder="Password" />
			</div>
			<font color="white"><springForm:errors path="username" /></font>
			<font color="white"><springForm:errors path="password" /></font>
			<div>
				<input type="submit" value="Send" />
			</div>

		</form:form>

		<c:if test="${not empty administrator.username}">
			<div class="normal">
				<strong><c:out value="Username:" /></strong>
				${administrator.username}<br> <strong><c:out
						value="Password:" /></strong> ${administrator.password}
			</div>
			<form:form method="post" action="addAdmin"
				modelAttribute="administrator" name="form2">
				<input type="submit" value="Confirm" />


				<form:input type="hidden" path='username' placeholder="id" />


				<form:input type="hidden" path='password' placeholder="id" />
				<form:input type="hidden" path='role' placeholder="id" />
			</form:form>
		</c:if>

	</div>

</body>
</html>