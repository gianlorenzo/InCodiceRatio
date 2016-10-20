<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>


<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

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

<style type="text/css">
body {
	background-image: url('./resources/img/siteImages/homePage.png');
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
	background-size: 1420px 650px;
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
					<h1 class="brand">
						<a href="index">In Codice Ratio</a>
					</h1>
					<!-- Responsive Navbar Part 2: Place all navbar contents you want collapsed withing .navbar-collapse.collapse. -->
					<nav class="pull-right nav-collapse collapse">
						<ul id="menu-main" class="nav">


							<a title="login" href="login">Sign in</a>


						</ul>
					</nav>
				</div>
				<!-- /.container -->
			</div>
			<!-- /.navbar-inner -->
		</div>
		<!-- /.navbar -->
	</div>







</body>

</html>
