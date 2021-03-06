<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


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
<link rel="shortcut icon" href="resources/img/siteImages/favicon.ico" />

</head>
<body>
	<!-- Page Wrapper -->
	<div id="page-wrapper">

		<!-- Header -->
		<header id="header" class="alt">

			<nav id="nav">
				<ul>
					<li class="special"><a href="#menu" class="menuToggle"><span>Menu</span></a>
						<div id="menu">
							<ul>
								<li><a href="resultConsole">Torna alla console dei
										risultati</a></li>
								<li><a href="homeAdmin">Torna al pannello di
										amministrazione</a></li>
								<li><a href="logout">Logout</a></li>
							</ul>
						</div></li>
				</ul>
			</nav>
		</header>
	</div>
	<div class="relative">
		<h2>Produttivitą degli studenti</h2>
	</div>
	
			<table>
				<tr>
					<th>ID</th>
					<th>Nome</th>
					<th>Cognome</th>
					<th>Numero di Task Completati</th>
				</tr>
				<c:forEach var="ps" items="${produttivita}">
					<tr>
						<th><c:out value="${ps.id}"></c:out></th>
						<th><c:out value="${ps.name}"></c:out></th>
						<th><c:out value="${ps.surname}"></c:out></th>
						<th><c:out value="${ps.numeroTask}"></c:out></th>



					</tr>
				</c:forEach>
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