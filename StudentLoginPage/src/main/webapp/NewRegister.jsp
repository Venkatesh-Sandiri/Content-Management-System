<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="CheckingNewRegister.js"></script>
</head>
<body>
	Please enter the following data to register for this website : <br>
	<form action="NewRegister" method="post" onsubmit="return CheckPasswordMatch()" >
	<input type = "text" placeholder = "first name" required name="fname" pattern="[A-Za-z]{3,}"><br> <br>
	<input type = "text" placeholder = "middle name" name="mname" pattern="[A-Za-z]{3,}"><br> <br>
	<input type = "text" placeholder = "last name" required name="lname" pattern="[A-Za-z]{3,}"><br> <br>
	<h4>Please enter shortcut for department</h4>
	<input type = "text" placeholder = "Department name" required name="dept" pattern="[A-Z]{2,3}"><br> <br>
	<input type = "text" placeholder = "section" required name="sec" pattern="[1-3]{1}"><br> <br>
	
	<input type = "email" placeholder = "email" required name="mail"><br> <br>
	<input type="tel" placeholder = "phone" name="phno" 
       pattern="[0-9]{10}"
       required><br> <br>
	<input type = "text" placeholder = "address" name="add"><br> <br> <br>
	Enter the roll number given to you by college : <br>
	<input type = "text" placeholder = "roll number" required name="rno" pattern="[A-Z]{1}[0-9]{2}[A-Z]{2}[0-9]{3}[A-Z]{0,1}"><br> <br>
	Create new password : <br>
	<input type = "password" placeholder = "password" name ="pass" required id="pass" pattern="((?=.*\d)(?=.*[A-Z])(?=.*[a-z]).{6,18}\w)"><br> <br>
	Confirm password : <br>
	<input type = "password" placeholder = "confirm password" name="confirm" id="confirm" required pattern="((?=.*\d)(?=.*[A-Z])(?=.*[a-z]).{6,18}\w)" onkeyup="CheckPasswordMatch()"> <br> <br>
	<input type="submit">
	</form>
</body>
</html>