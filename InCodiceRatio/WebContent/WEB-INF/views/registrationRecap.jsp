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
<link rel="stylesheet" href="resources/css/main.css" />


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
			
			<nav id="nav">
				<ul>
					<li class="special"><a href="#menu" class="menuToggle"><span>Menu</span></a>
						<div id="menu">
							<ul>
								<li><a href="login">Log In</a></li>
								<li><a href="index">Torna alla pagina principale</a></li>
							</ul>
						</div></li>
				</ul>
			</nav>
		</header>
	</div>
	
	<div class="relative">
		<h2>Riepilogo dei dati inseriti</h2>
	</div>
	
	<table>
		<tr>
			<th>Nome</th>
			<th>Cognome</th>
			<th>Scuola</th>
			<th>Anno</th>
			<th>Sezione</th>
			<th>Username</th>
		</tr>

		<tr>
			<th>${student.name}</th>
			<th>${student.surname}</th>
			<th>${student.school}</th>
			<th>${student.schoolGroup}</th>
			<th>${student.section}</th>
			<th>${student.username}</th>
		</tr>
	</table>
	
	
	<!-- Scripts -->
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/jquery.scrollex.min.js"></script>
	<script src="resources/js/jquery.scrolly.min.js"></script>
	<script src="resources/js/skel.min.js"></script>
	<script src="resources/js/util.js"></script>
	<!--[if lte IE 8]><script src="resources/js/ie/respond.min.js"></script><![endif]-->
	<script src="resources/js/main.js"></script>

</body>
</html>