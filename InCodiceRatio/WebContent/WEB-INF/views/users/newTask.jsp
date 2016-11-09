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

<!--[if lte IE 8]><script src="resources/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="resources/css/newTask.css" />
<!--[if lte IE 8]><link rel="stylesheet" href="resources/css/ie8.css" /><![endif]-->
<!--[if lte IE 9]><link rel="stylesheet" href="resources/css/ie9.css" /><![endif]-->

<!-- Favicon -->
<link rel="shortcut icon"
	href="<c:url value="resources/img/siteImages/favicon.ico"/>">


</head>

<body class="landing">
	<ul>
		<h2>${task.job.title}${task.job.symbol.transcription}</h2>
	</ul>






	<div align="center">
		<h3>Spunta le immagini che ti sembrano simili agli esempi sopra</h3>
	</div>
	<table class="pos">
		<c:forEach varStatus="vs" var="sample" items="${positiveSamples}">

			<td><img src="resources${sample.path}" alt="${sample.id}" /></td>
		</c:forEach>
	</table>

	<div align="center">
		<h3>Attenzione: non vanno bene immagini come queste</h3>
	</div>
	<table class="neg">
		<c:forEach varStatus="vs" var="sample" items="${negativeSamples}">

			<td><img src="resources${sample.path}" alt="${sample.id}" /></td>
		</c:forEach>
	</table>


	<form:form method="post" action="secondConsole"
		modelAttribute="taskResults" name="form">

		<table class="${task.job.difficulty}">
			<c:forEach varStatus="vs" var="result"
				items="${taskResults.resultList}">

				<c:if test="${vs.count % 10 == 1}">

					<tr>
				</c:if>
				<td>

					<div>
						<img src="resources${result.image.path}" alt="${result.image.id}"
							class="resized" />
					</div>
					<div align="center">
						<label><form:checkbox style="display:inline"
								path="resultList[${vs.index}].answer" value="Yes" /> </label>
					</div>
				</td>
				<c:if test="${vs.count % 10 == 0}">
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

		<div align="center">
			<input type="submit" value="Conferma e vai al prossimo Task">
		</div>
	</form:form>
	
	<div align="center">
		<form:form method="post" action="homeStudent">
			<input type="submit" value="Torna alla pagina dello studente">
		</form:form>
	
	</div>

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