<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ICR</title>

<!--[if lte IE 8]><script src="resources/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="resources/css/main.css" />
<!--[if lte IE 8]><link rel="stylesheet" href="resources/css/ie8.css" /><![endif]-->
<!--[if lte IE 9]><link rel="stylesheet" href="resources/css/ie9.css" /><![endif]-->



<link rel="shortcut icon" href="resources/img/siteImages/favicon.ico" />
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
		<h2>Job Creati</h2>
	</div>

	<table>
		<tr>
			<th>ID</th>
			<th>Titolo</th>
			<th>Descrizione</th>
			<th>Difficolt�</th>
			<th>#Studenti</th>
			<th>#Immagini</th>
			<th>Dimensione Task</th>
			<th>Manoscritto</th>
			<th>Simbolo</th>
		</tr>
		<c:forEach var="job" items="${jobs}">
			<tr>
				<th><c:out value="${job.id}"></c:out></th>
				<th><c:out value="${job.title}"></c:out></th>
				<th><c:out value="${job.description}"></c:out></th>
				<th><c:out value="${job.difficulty}"></c:out></th>
				<th><c:out value="${job.students}"></c:out></th>
				<th><c:out value="${job.numberOfImages}"></c:out></th>
				<th><c:out value="${job.taskSize}"></c:out></th>
				<th><c:out value="${job.imageManuscript}"></c:out></th>
				<th><c:out value="${job.symbol.transcription}"></c:out></th>
			</tr>
		</c:forEach>
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