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


							<a title="#" href="#">Pagina di registrazione dello studente</a>

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

		<h2>
			<strong><font color="ffffff">Create a new Account</font></strong>
		</h2>
		<form:form method="post" action="addUser" modelAttribute="student"
			name="form">

			<div>
				<h2>Nome:</h2>
				<form:input type="text" path='name' placeholder="Name" />
			</div>

			<div>

				<h2>Cognome:</h2>
				<form:input type="text" path='surname' placeholder="Surname" />
			</div>
			<div>

				<h2>Scuola:</h2>
				<form:input type="text" path='school' placeholder="School" />
			</div>
			<div>

				<h2>Anno:</h2>


				<form:select path="schoolGroup">
					<form:options items="${schoolGroups}" />
				</form:select>
			</div>
			<div>

				<h2>Sezione:</h2>
				<form:input type="text" path='section' placeholder="Section" />
			</div>
			
			<div>
				
				<h2>Username:</h2>
				<font size="3" color="red">${usernameError}</font>
				<form:input type="text" path='username' placeholder="Username" />
			</div>
			<div>

				<h2>Password:</h2>
				<form:input type="Password" path='password' placeholder="Password" />
			</div>

			<font color="white"><springForm:errors path="username" /></font>
			<font color="white"><springForm:errors path="password" /></font>
			<input type="submit" value="Send" />





		

			</form:form>



	</div>
</body>
</html>