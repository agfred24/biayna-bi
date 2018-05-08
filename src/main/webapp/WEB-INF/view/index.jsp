<!doctype html>

<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Biayna BI</title>
	<meta name="description" content="">
	<meta name="author" content="">
</head>

<body>

	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setHeader("Expires", "0"); // Proxies.
	%>
	<p>Hi ${firstName}</p> 
	<br>
	<p>Hi ${role}</p>
	<a href="logout.html">Sign Out</a>
	
	<h1>Index Page</h1>
	<a href="upload.html">Upload</a>
	<br />
	<a href="listings.html">Listings</a>

</body>
</html>