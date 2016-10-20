<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ICR</title>

<!-- Google Web fonts -->


<link href='http://fonts.googleapis.com/css?family=Quattrocento:400,700'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Patua+One'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css'>

<!-- Bootstrap CSS -->

<link type="text/css"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet">
<link type="text/css"
	href="${pageContext.request.contextPath}/resources/css/bootstrap-responsive.css"
	rel="stylesheet">
<link type="text/css"
	href="${pageContext.request.contextPath}/resources/css/fontello.css"
	rel="stylesheet">
<link type="text/css"
	href="${pageContext.request.contextPath}/resources/css/fontello-ie7.css"
	rel="stylesheet">
<link type="text/css"
	href="${pageContext.request.contextPath}/resources/css/prettyPhoto.css"
	rel="stylesheet">
<link type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet">

<link rel="shortcut icon" href="resources/img/siteImages/favicon.ico" />
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
						<a href="#">Welcome in your Administration
							Panel:${pageContext.request.userPrincipal.name} </a>
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
		<div style="position: inline; top: 100px;">
			<table>
				<tr>
					<th>ID </th>
					<th>Titolo</th>
					<th>Descrizione</th>
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
						<th><c:out value="${job.students}"></c:out></th>

						<th><c:out value="${job.numberOfImages}"></c:out></th>

						<th><c:out value="${job.taskSize}"></c:out></th>

						<th><c:out value="${job.imageManuscript}"></c:out></th>
						<th><c:out value="${job.symbol.transcription}"></c:out></th>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>




</body>
</html>