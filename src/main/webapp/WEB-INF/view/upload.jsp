<!doctype html>

<html lang="en">
<head>
	<meta charset="utf-8">
	
	<title>Biayna BI</title>
	<meta name="description" content="">
	<meta name="author" content="">
	
	<!--  <link rel="stylesheet" href="css/styles.css?v=1.0"> -->
	<style>
		#drop_zone {
			background-color: #D6E1FC;
			border: #336DFF 1px dashed;
			width: 400px;
			height: 100px;
			padding: 8px;
			font-size: 18px;
		}
	</style>
</head>

<body>
	<script>
		function drag_drop(event) {
			event.preventDefault();
			document.getElementById("drop_zone").style.backgroundColor  = "#DCFCD6";
			alert(event.dataTransfer.files[0]);
			alert(event.dataTransfer.files[0].size + " bytes");
			/*  This is where to begin uploading the file with Ajax and upload progress bar to PHP script */
			/*   https://www.developphp.com/video/JavaScript/File-Upload-Progress-Bar-Meter-Tutorial-Ajax-PHP */
		}
	</script>
	<h1>File Upload</h1>
	<div id="drop_zone" ondrop="drag_drop(event)" ondragover="return false">
		<!-- <form action="/biayna-bi/uploadProcess.html" method="post"> -->
		<form method="POST" action="${pageContext.request.contextPath}/uploadProcess.html" enctype="multipart/form-data">
			<p><input id="uploadInput" type="file" name="userFile"></p>
			<p><input type="submit" style="float: right" value="Send file"></p>
		</form>
	</div>
</body>
</html>