<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<div class="relative">
		<h2>Riepilogo del Job creato</h2>
	</div>

	<table>
		<tr>
			<th>Titolo</th>
			<th>Descrizione</th>
			<th>Difficoltà</th>
			<th>Numero di Studenti</th>
			<th>Numero di Immagini</th>
			<th>Dimensione del Task</th>
			<th>Manoscritto</th>
			<th>Pagina</th>
			<th>Simbolo</th>
		</tr>

		<tr>
			<th>${job.title}</th>
			<th>${job.description}</th>
			<th>${job.difficulty}</th>
			<th>${job.students}</th>
			<th>${job.numberOfImages}</th>
			<th>${job.taskSize}</th>
			<th>${job.imageManuscript}</th>
			<th>${job.imagePage}</th>
			<th>${job.symbol.transcription}</th>
		</tr>
	</table>

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