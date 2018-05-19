<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setHeader("Expires", "0"); // Proxies.
%>
<%@ include file="header.jsp"%>
<div class="container">

	<div class="row">
		<div class="col-xl-3 col-lg-3 col-md-2 col-sm-1 col-1"></div>
		<div class="col-xl-6 col-lg-6 col-md-8 col-sm-10 col-10 p-4 rounded"
			style="background-color: #8EC9FF;">
			<div class="alert alert-success">
			  You have successfully logged out.
			</div>
			<div class="lead pl-1">
				<a href="login.html">Log in</a>
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