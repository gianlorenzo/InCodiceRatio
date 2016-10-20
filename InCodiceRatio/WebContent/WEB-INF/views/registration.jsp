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


							<a title="#" href="#">Student's registration page</a>

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
		<form:form method="post" action="confirmUser" modelAttribute="student"
			name="form">

			<div>
				<h2>Name:</h2>
				<form:input type="text" path='name' placeholder="Name" />
			</div>

			<div>

				<h2>Surname:</h2>
				<form:input type="text" path='surname' placeholder="Surname" />
			</div>
			<div>

				<h2>School:</h2>
				<form:input type="text" path='school' placeholder="School" />
			</div>
			<div>

				<h2>SchoolGroup:</h2>


				<form:select path="schoolGroup">
					<form:options items="${schoolGroups}" />
				</form:select>
			</div>
			<div>

				<h2>Section:</h2>
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




		<c:if test="${not empty student.username}">
			<h2>
				<c:out value="Confirm the following information:" />
			</h2>
			<br>
			<strong><c:out value="Name:" /></strong>
				${student.name}<br>
			<strong><c:out value="Surname:" /></strong>
				${student.surname}<br>
			<strong><c:out value="School:" /></strong>
				${student.school}<br>
			<strong><c:out value="SchoolGroup:" /></strong> ${student.schoolGroup}<br>
			<strong><c:out value="Section:" /></strong> ${student.section}<br>
			<strong><c:out value="Username:" /></strong> ${student.username}<br>
			<strong><c:out value="Password:" /></strong> ${student.password}<br>

			<form:form method="post" action="addUser" modelAttribute="student"
				name="form2">
				<input type="submit" value="Confirm" />
				<br>
				<br>



				<form:input type="hidden" path='name' placeholder="id" />
				<br />
				<form:input type="hidden" path='surname' placeholder="id" />
				<br />

				<form:input type="hidden" path='school' placeholder="id" />
				<br />
				<form:input type="hidden" path='schoolGroup' placeholder="id" />
				<br />

				<form:input type="hidden" path='section' placeholder="id" />
				<br />

				<form:input type="hidden" path='username' placeholder="id" />
				<br />

				<form:input type="hidden" path='password' placeholder="id" />
				<form:input type="hidden" path='role' placeholder="id" />


			</form:form>
		</c:if>



	</div>
</body>
</html>