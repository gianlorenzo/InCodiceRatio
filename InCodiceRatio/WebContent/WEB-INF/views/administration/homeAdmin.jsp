<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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

<style type="text/css">
body {
	background-color: #000000;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
}
</style>


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
						<a href="#">Benvenuto nella tua pagina:
							${pageContext.request.userPrincipal.name} </a>
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
			<h1>
				<a title="newAdmin" href="insertJob"> Crea un nuovo Job</a>
			</h1>
			<div>
				<h1>
					<a title="insertImage" href="insertImage">Inserisci le immagini
						nel Database</a>
				</h1>
			</div>
			<div>
				<h1>
					<a title="insertSymbol" href="insertSymbol">Inserisci i simboli
						nel Database</a>
				</h1>
			</div>
			<div>
				<h1>
					<a title="insertSample" href="insertSample">Inserisci gli
						esempi nel Database</a>
				</h1>
			</div>
			<div>
				<h1>
					<a title="Jobs" href="listJobs">Job creati</a>
				</h1>
			</div>

			<div>
				<h1>
					<a title="Jobs" href="resultConsole">Console dei Risultati</a>
				</h1>
			</div>
			

		</div>
	</div>


</body>
</html>