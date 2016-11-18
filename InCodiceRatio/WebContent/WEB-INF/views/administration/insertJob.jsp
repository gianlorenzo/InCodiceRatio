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

<!--[if lte IE 8]><script src="resources/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="resources/css/registration.css" />
<!--[if lte IE 8]><link rel="stylesheet" href="resources/css/ie8.css" /><![endif]-->
<!--[if lte IE 9]><link rel="stylesheet" href="resources/css/ie9.css" /><![endif]-->

<!-- Favicon -->
<link rel="shortcut icon"
	href="<c:url value="resources/img/siteImages/favicon.ico"/>">

</head>
<body class="landing">
	<!-- Header -->
	<header id="header" class="alt">

		<nav id="nav">
			<ul>
				<li class="special"><a href="#menu" class="menuToggle"><span>Menu</span></a>
					<div id="menu">
						<ul>
							<li><a href="homeAdmin">Torna alla pagina di
									amministrazione</a></li>
							<li><a href="logout">Logout</a></li>
						</ul>
					</div></li>
			</ul>
		</nav>
	</header>

	<div class="form">



		<div class="tab-content">
			<div id="signup">
				<h1>Crea un nuovo Job</h1>

				<form:form method="post" action="addJob" modelAttribute="job"
					name="form">

					<div class="field-wrap">
						<label> Titolo </label>
						<form:input type="text" path="title" placeholder="Titolo" />
					</div>

					<div class="field-wrap">

						<div class="field-wrap">
							<label> Difficoltà </label>
							<form:input type="text" path="difficulty"
								placeholder="Difficoltà" />
						</div>

						<div class="field-wrap">
							<label> #Studenti </label>
							<form:input type="text" path='students'
								placeholder="Numero di Studenti" onBlur="isnum(this)" />
						</div>

						<div class="field-wrap">
							<label> #Immagini </label>
							<form:input type="text" path='numberOfImages'
								placeholder="Numero di Immagini" onBlur="isnum(this)" />
						</div>

						<div class="field-wrap">
							<label> Dimensione Task </label>
							<form:input type="text" path='taskSize'
								placeholder="Dimensione Task" onBlur="isnum(this)" />
						</div>

						<div class="field-wrap">
							<label> Manoscritto </label>
							<form:select path="imageManuscript">

								<form:options items="${manuscripts}" />

							</form:select>
						</div>
						
						<div class="field-wrap">
							<label> Simbolo </label>
							<form:select path="symbol">
								<font color="7a0000"><form:options items="${symbols}"
										itemLabel="transcription" itemValue="id" /> </font>
							</form:select>
						</div>



						<button type="submit" class="button button-block">Conferma</button>
					</div>
				</form:form>

			</div>

			<div id="login"></div>

		</div>
		<!-- tab-content -->

	</div>
	<!-- /form -->



	<!-- Scripts -->
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/jquery.scrollex.min.js"></script>
	<script src="resources/js/jquery.scrolly.min.js"></script>
	<script src="resources/js/skel.min.js"></script>
	<script src="resources/js/util.js"></script>
	<!--[if lte IE 8]>-->
	<script src="resources/js/ie/respond.min.js"></script>
	<script src="resources/js/main.js"></script>
	<script src="resources/js/file.js"></script>


</body>
</html>