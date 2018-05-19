
	<%-- <p>Hi ${firstName}</p> 
	<br>
	<p>Hi ${role}</p>
	<a href="logout.html">Sign Out</a>
	
	<h1>Index Page</h1>
	<a href="upload.html">Upload</a>
	<br />
	<a href="listings.html">Listings</a>
 --%>

<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setHeader("Expires", "0"); // Proxies.
%>
<%@ include file="header.jsp"%>


	
	
	<div class="row">
		<div class="col-12">
			<p class="text-center">
				<br /> Copyright &copy; - Biayna
				<%=java.time.LocalDateTime.now().getYear()%>
			</p>
		</div>
	</div>

<%@ include file="footer.jsp"%>