<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setHeader("Expires", "0"); // Proxies.
%>
<%@ include file="header.jsp"%>
<div class="container">

	<div class="row">
		<div class="col-12">
			<h1 class="text-muted text-center">Welcome to Biayna BI</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-xl-3 col-lg-3 col-md-2 col-sm-1 col-1"></div>
		<div class="col-xl-6 col-lg-6 col-md-8 col-sm-10 col-10 p-4 rounded"
			style="background-color: #8EC9FF;">

			<form id="loginForm" action="login.html" method="post">

				<p class="lead text-muted ml-1">Log In</p>

				<c:if test="${not empty error}">
					<div class="alert alert-danger small" role="alert">${error}</div>
				</c:if>

				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text"><i class="fas fa-envelope"></i></span>
					</div>
					<input type="text" class="form-control" placeholder="Email"
						aria-label="Email" name="email" id="email" />
				</div>

				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text"><i class="fas fa-lock"></i></span>
					</div>
					<input type="password" class="form-control" placeholder="Password"
						aria-label="password" aria-describedby="basic-addon1"
						name="password" id="password" />
				</div>
				<button type="submit" class="btn btn-outline-primary btn-block">Log In</button>
			</form>
			<div class="lead small pt-2 pl-1">
				<a href="">Forgot your password?</a>
			</div>
		</div>
		<div class="col-xl-3 col-lg-3 col-md-2 col-sm-1 col-1 "></div>
	</div>
	<div class="row">
		<div class="col-12">
			<p class="text-center">
				<br /> Copyright &copy; - Biayna
				<%=java.time.LocalDateTime.now().getYear()%>
			</p>
		</div>
	</div>
</div>

<%@ include file="footer.jsp"%>