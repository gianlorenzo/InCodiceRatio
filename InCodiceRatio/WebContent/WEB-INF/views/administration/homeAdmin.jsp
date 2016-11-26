<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
								<li><a href="toSelectManuscript">Crea un nuovo Job</a></li>
								<li><a href="insertImageByManuscript">Inserisci le immagini nel
										Database</a></li>
								<li><a href="insertSymbolByManuscript">Inserisci i simboli nel
										Database</a></li>
								<li><a href="insertSampleByManuscript">Inserisci gli esempi nel
										Database</a></li>
								<li><a href="insertNegativeByManuscript">Inserisci i falsi positivi nel Database</a></li>
								<li><a href="listJobs">Job Creati</a></li>
								<li><a href="resultConsole">Console dei Risultati</a></li>
								<li><a href="toChangeStudentPassword">Modifica la password di uno studente</a></li>
								
								<li><a href="logout">Logout</a></li>
							</ul>
						</div></li>
				</ul>
			</nav>
		</header>
	</div>

	<!-- Banner -->
	<section id="banner">
		<div class="inner">

			<h2>Home Admin: ${pageContext.request.userPrincipal.name}</h2>
		</div>
	</section>

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