<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setHeader("Expires", "0"); // Proxies.
%>
<%@ include file="header.jsp"%>
<div class="container">

	<div class="row">
		<div class="col-12">
			<h1 class="text-muted text-center">500 Internal Server Error</h1>
			<h3 class="text-muted text-center">Sorry, something went wrong. The server was unable to complete your request.</h3>
		</div>
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