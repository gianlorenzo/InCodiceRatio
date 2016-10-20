<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ page import="it.uniroma3.icr.model.Symbol"%>
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
					<h2 class="brand">
						<a href="#">Pagina di creazione di un Job </a>
					</h2>
					<!-- Responsive Navbar Part 2: Place all navbar contents you want collapsed withing .navbar-collapse.collapse. -->
					<nav class="pull-right nav-collapse collapse">
						<ul id="menu-main" class="nav">



							<a title="logout" href="logout"> Logout</a>

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

		



		<form:form method="post" action="addJob" modelAttribute="job"
			name="form">
			<div style="position: inline; top: 35px;">

			<div>
				<h2>Titolo:</h2>
				<form:input type="text" path="title" placeholder="Title" />
			</div>

			<div>
				<h2>Descrizione:</h2>
				<form:textarea path="description" rows="5" cols="5" />
			</div>

			<div>
				<h2>#Studenti:</h2>
				<form:input type="text" path='students' placeholder="Students"
					onBlur="isnum(this)" />
			</div>

			<div>
				<h2>#Immagini:</h2>
				<form:input type="text" path='numberOfImages' placeholder="images"
					onBlur="isnum(this)" />
			</div>

			<div>
				<h2>Dimensione Task:</h2>
				<form:input type="text" path='taskSize' placeholder="TaskSize"
					onBlur="isnum(this)" />
			</div>


			<div>
				<h2>Manoscritto:</h2>
				<form:select path="imageManuscript">

					<form:options items="${manuscripts}" />

				</form:select>

				<div>
					<h2>Simbolo:</h2>
					<form:select path="symbol">
						<font color="7a0000"><form:options items="${symbols}"
								itemLabel="transcription" itemValue="id" /> </font>

					</form:select>
				</div>

				<form:hidden path="id" />

				<input type="submit" value="Send" />
			</div>
			</div>
		</form:form>
	</div>

	<script src="resources/js/file.js"></script>


</body>
</html>