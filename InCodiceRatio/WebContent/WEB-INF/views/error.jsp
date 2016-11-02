<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

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

<style type="text/css">
body {
	background-color: black;
}
</style>


</head>
<body class="landing">

	<c:set var="exception"
		value="${requestScope['javax.servlet.error.exception']}" />

	<ul>
		<h2>
			${exception} <a href="logout">Logout</a>
		</h2>
	</ul>


	<c:forEach var="e" items="${exception.stackTrace}">

		<c:out value="${e}" />

	</c:forEach>



</body>
</html>