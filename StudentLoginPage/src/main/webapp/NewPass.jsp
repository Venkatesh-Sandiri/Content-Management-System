<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/NewFile.css"/>
<script type="text/javascript" src="CheckingNewRegister.js"></script>
</head>
<body>
	<form action="ChangePass" class="PassNew" onsubmit="return CheckPasswordMatch()" method="get">
	Enter your new password :<br><br>
	<input type="password" name="pass" id="pass" placeholder="change password" pattern="((?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*\W)\w.{6,18}\w)" required><br><br>
	<input type="password" name="confirm" id="confirm" placeholder="retype password" pattern="((?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*\W)\w.{6,18}\w)" required onkeyup="CheckPasswordMatch()"><br> <br>
	<input type="submit" >
	</form>
</body>
</html>