<!doctype html>

<html lang="en">
<head>
	<meta charset="utf-8">
	
	<title>Biayna BI</title>
	<meta name="description" content="">
	<meta name="author" content="">
	
	<!--  <link rel="stylesheet" href="css/styles.css?v=1.0"> -->
	<style>
		#products {
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
			alert(event.dataTransfer.files[0].name);
			alert(event.dataTransfer.files[0].size + " bytes");
			/*  This is where to begin uploading the file with Ajax and upload progress bar to PHP script */
			/*   https://www.developphp.com/video/JavaScript/File-Upload-Progress-Bar-Meter-Tutorial-Ajax-PHP */
		}
	</script>
	<h1>Product List</h1>
	<div id="products">
		${listings}
	</div>
</body>
</html>