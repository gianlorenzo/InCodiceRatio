<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
						<a href="#">Job's Recap </a>
					</h2>
					<!-- Responsive Navbar Part 2: Place all navbar contents you want collapsed withing .navbar-collapse.collapse. -->
					<nav class="pull-right nav-collapse collapse">
						<ul id="menu-main" class="nav">
						


							<a title="homeAdmin" href="homeAdmin"> Go Back to Administrator Page</a> &nbsp;
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

		<h2>
			<c:out value="Confirms the following information:" />
		</h2>
		
		<div>
		<h3><c:out value="Title:" />${job.title}</h3>
		</div>		
		
		
		<div>
		<h3><c:out value="Description:" /></h3>${job.description}
		</div>
		
		<div>		
			<h3><c:out value="#Students:" /></h3>${job.students}
		</div>
		
		<div>	
			<h3><c:out value="#Images:" /></h3>${job.numberOfImages} 
		</div>
		
		<div>	
			<h3><c:out value="%Type1:" /></h3>${job.percentageType1} 
		</div>
		
		<div>	
			<h3><c:out value="%Type2:" /></h3>${job.percentageType2} 
		</div>
		
		<div>	
			<h3><c:out value="%Type3:" /></h3>${job.percentageType3} 
		</div>
		
		<div>	
			<h3><c:out value="TaskSize:" /></h3>${job.taskSize} 
		</div>
		
		<div>	
			<h3><c:out value="ImageManuscript:" /></h3>${job.imageManuscript} 
		</div>
		
		<div>			
			<h3><c:out value="Symbol:" /></h3>${job.symbol.transcription}
		</div>
		



	</div>
	
	

</body>
</html>