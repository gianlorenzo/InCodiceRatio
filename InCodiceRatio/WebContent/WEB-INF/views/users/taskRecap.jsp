<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="it.uniroma3.icr.model.Result"%>
<%@ page import="it.uniroma3.icr.model.Task"%>


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
<link rel="stylesheet" href="<c:url value=" resources/css/style.css" />"
	type="text/css">

<!-- Favicon -->
<link rel="shortcut icon"
	href="<c:url value="resources/img/siteImages/favicon.ico"/>">

</head>

<body>
	<div style="position: absolute; top: 15px; left: 10px">

		<font size="6" color="white">Pagina dello studente</font>
	</div>

	<div style="position: absolute; top: 15px; right: 40px">
		<font size="6" color="white">Benvenuto:
			${pageContext.request.userPrincipal.name}</font>
	</div>


	<div align="center">
		<h1>
			<font color="white">These are your answers:</font>
		</h1>

		<div class="normal">
			<table>
				<tr>
					<th>Id Image:</th>
					<th>Answer</th>
				</tr>

				<c:forEach items="${taskResults.resultList}" var="result"
					varStatus="vs">

					<tr>
						<th><c:out value="${result.image.id}" /></th>
						<th><c:out value="${result.answer}" /></th>
					</tr>
				</c:forEach>
			</table>
		</div>




	</div>
</body>
</html>