<%@ page language="java" contentType="text/html; charset=UTF-8"
	session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Biayna BI</title>
	<link rel="stylesheet"
		href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
		integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4"
		crossorigin="anonymous">
	<script defer
		src="https://use.fontawesome.com/releases/v5.0.10/js/all.js"
		integrity="sha384-slN8GvtUJGnv6ca26v8EzVaR9DC58QEwsIk9q1QXdCU8Yu8ck/tL/5szYlBbqmS+"
		crossorigin="anonymous"></script>
</head>

<body style="background-color: #EAF2F8;">	
	<header>
	
		<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-3">
			<div class="container">
				<a class="navbar-brand mr-lg-5" href="/biayna-bi/">Biayna BI</a>
				<c:if test="${authenticated}">
				<button class="navbar-toggler" data-toggle="collapse" data-target="#navbarNav"><span class="navbar-toggler-icon"></span></button>
            	<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="/biayna-bi/">Home</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Active Listings</a>
						</li>
					</ul>
					</div>
					<div class="dropdown show ml-auto">
								<a class="btn btn-secondary dropdown-toggle" href="#"
									role="button" id="dropdownMenuLink" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false"> 
									Hi <c:out value="${firstName}" default="firstName"/>
									<span class="small text-muted">(<c:out value="${roleName}" default="unknown"/>)</span>
								</a>
								<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
									<c:if test="${roleId >= 3}">
									 	<a href="/biayna-bi/authentication/registration.html"
										class="dropdown-item">Register</a>
										<div class="dropdown-divider"></div>
									</c:if>
									<a href="/biayna-bi/authentication/logout.html"
										class="dropdown-item text-danger">Sign Out</a>
								</div>
							</div>
					</div>
				
				</c:if>
		</nav>

	</header>