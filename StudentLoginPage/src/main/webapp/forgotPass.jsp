<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="NewFile.css"/>
</head>
<body>
	
	<form action="ChangePass" method="post" class="changePass">
	<h3>Enter your Roll number <br>to change your password :</h3><br>
	<span class="brtop"></span>
	<input type = "text" pattern = "[A-Z]{1}[0-9]{2}[A-Z]{2}[0-9]{3}" placeholder =  "B17EC126" name = "rno">
	<input type = "submit" value="submit">
	</form>
</body>
</html>