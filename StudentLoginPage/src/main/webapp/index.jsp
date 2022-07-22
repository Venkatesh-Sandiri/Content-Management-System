<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="NewFile.css"/>
<title>Insert title here</title>
</head>
<body  >
	<form action="LoginCheck" method="post" class="login">
	<span class="brtopmost"></span>
	<p id="p">Student Enter your details to login :</p> 
	<span class="brtop"></span>
	<h1 id='h1'>Sign In</h1>
	<span class="br"></span>
	<pre>
   Username:  <input type ="text" name = "uname" placeholder="username" pattern="[A-Z]{1}[0-9]{2}[A-Z]{2}[0-9]{3}[A-Z]{0,1}"><br>
   Password:  <input type= "password" id ="pass" name = "pass" placeholder="password" ><br>
   	   <a href="forgotPass.jsp">Forgot your password?</a>
	</pre>
	<span class="brtop"></span>
	<input type="submit" id="sub"><br>
	</form>
	
	
	
	<br> <br> 
	<form id = "NewUser" action="NewRegister.jsp" method="post">
		<br>New User<br>
		<input type="submit" value="Register" id="subm">
	</form>
	<div class ="message">
	<%
	String str=request.getParameter("l");
	if(str!=null)
	{
		if(str.equals("success"))
			out.print("<br><h1>New User Created</h1>");
		else
			out.print("User Alredy Exists Please Login");
	}
	String s=request.getParameter("log");
	if(s!=null)
	{
		if(s.equals("fail"))
			out.print("<br><h1>Incorrect Passwword</h1>");
	}
	String incpsw=request.getParameter("usr");
	if(incpsw!=null)
	{
		if(incpsw.equals("fail"))
			out.print("<br><h1>User Dosen't Exist</h1>");
	}
	String newPass = request.getParameter("newPass");
	if(newPass!=null)
	{
		if(newPass.equals("fail"))
			out.print("<br><h1>Unable to change password, Incorrect mobile number</h1>");
		else
			out.print("<h1>Password Changed</h1>");
	}
	%>
	</div>
</body>
</html>