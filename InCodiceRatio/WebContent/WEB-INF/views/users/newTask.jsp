<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="it.uniroma3.icr.model.Job"%>
<%@ page import="it.uniroma3.icr.model.Task"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>




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
<link rel="stylesheet"
	href="<c:url value=" resources/css/tableTask.css" />" type="text/css">

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
						<a href="#">${task.job.title} ${task.job.symbol.transcription}</a>
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

		<div style="position: inline; top: 60px;">
			<table>
				<c:forEach varStatus="vs" var="sample" items="${samples}">

					<td><img src="resources${sample.path}" alt="${sample.id}" />


					</td>
				</c:forEach>
			</table>
		</div>

		<div style="position: inline; top: 50px;">
			<h3>Spunta le immagini che ti sembrano simili agli esempi sopra  
			</h3>
		</div>




		<form:form method="post" action="secondConsole"
			modelAttribute="taskResults" name="form">

			<div style="position: inline; top: 60px;">

				<table>
					<c:forEach varStatus="vs" var="result"
						items="${taskResults.resultList}">

						<c:if test="${vs.count % 5 == 1}">

							<tr>
						</c:if>
						<td>

							<div>
								<img style="display: inline" src="resources${result.image.path}"
									alt="${result.image.id}" />
							</div>
							<div align="center">
								<form:checkbox style="display:inline"
									path="resultList[${vs.index}].answer" value="Yes" />
							</div>
						</td>
						<c:if test="${vs.count % 5 == 0}">
							</tr>


						</c:if>
						<form:hidden path="resultList[${vs.index}].id" />
						<form:hidden path="resultList[${vs.index}].image.id" />
						<form:hidden path="resultList[${vs.index}].task.id" />
						<form:hidden path="resultList[${vs.index}].task.student.id" />
						<form:hidden path="resultList[${vs.index}].task.batch" />
						<form:hidden path="resultList[${vs.index}].task.job.id" />
						<form:hidden path="resultList[${vs.index}].task.startDate" />



					</c:forEach>
				</table>
			</div>

			<div style="position: inline; top: 70px;">
				<input type="submit" value="Invio">
			</div>
		</form:form>




	</div>










</body>
</html>