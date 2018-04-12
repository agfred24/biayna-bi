<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Biayna BI</title>
</head>

<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setHeader("Expires", "0"); // Proxies.
	
		
	%>		
	
	<form id="loginForm" action="login.html" method="post">
		<p style="color: red">${error}</p>
		<table>
			<tr>
				<td align="right">Username:</td>
				<td><input type="text" name="userName" id="userName" /></td>
			</tr>
			<tr>
				<td align="right">Password:</td>
				<td><input type="password" name="password" id="password" /></td>
			</tr>
			<tr>
				<td></td>
				<td align="right"><input type="submit" value="login" /></td>
			</tr>
		</table>
	</form>
</body>
</html>

