<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*"%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.svhs.StudentDAO" %>
<%@ page import="com.svhs.TeacherDAO" %>
<%@page import="com.svhs.StudentUploadDp" %>
<%@page import="com.svhs.GetImage" %>
<%@page import="java.util.ArrayList"%>      <%--Importing all the dependent classes--%>
<%@page import="java.util.Iterator"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="NewFile.css"/>
<style type="text/css">

body {
      padding: 1%;
      font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
   }
   * {
      box-sizing: border-box;
   }
   .left, .right, .center {
      float: left;
      padding: 10px;
      height: 500px;
      text-align: center;
   }
   .left {
      width: 20%;
   }
   .right {
      width: 20%;
   }
   .center {
      width: 50%;
   }
   .container:after {
      clear: both;
   }
	table
	{
		border-collapse: collapse;
		width : 100%;
		border : 1px solid black;
	}
	td,th
	{
		text-align: left;
		padding: 10px;
		border: 1px solid black;
	}
	th
	{
		background-color: lightblue;
		
	}
	tr : nth_child(even)
	{
		background-color: rgb(180,180,180);
	}
}
</style>
</head>
<body>
	<%
		//Below line is to prevent opening of page again after logging out of it
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	
		//Below line works on http older than version Http 1.1
			response.setHeader("Pragma", "no-cache");
		
		//Below line is to prevent opening of page again when we use proxy and access our site
			response.setHeader("Expires", "0");
		
			if(session.getAttribute("uname") == null)
			{
				response.sendRedirect("index.jsp");
			}
	%>
	<div class ="logout">
	<form action="Logout" method="post">
	<input type="submit" value="Logout">
	<%
		String un="";
		if(session.getAttribute("uname")!=null)
		un = session.getAttribute("uname").toString();
		StudentDAO obj=new StudentDAO();
		String details[]=new String[8];
		if(un!=null)
		{
			details = obj.getDetails(un);
			for(int i=0;i<4;i++)
			{
				if(details[i]!="" || details[i]!=null || !details[i].isEmpty())
					out.print(details[i]+" ");
			}
		}
		%>
	</form>
	</div>
	

<img style="margin-left: 500px;" alt="college_pic" src="http://localhost:8080/StudentLoginPage/images/college_pic.jpg" width="500" height="100">
<h3 style="text-align: center;">Kakatiya institue of technology and science</h3>
<div class="container">
	<div class="left">
	<h4 style="color: lightblue">Institute operations</h4><br>
	<a href="Home.jsp">My Account</a><br>
	<a href="Notice.jsp">Notice board</a><br>
	<a href="Attendance.jsp">Attendance Mgmt.</a><br>
	<a href="MyClass.jsp">My Class</a><br>
	<a href="Marks.jsp">Exams Mgmt.</a><br>
	</div>
	
	
	<div class="center">
		<h2 style="color: blue;text-align:left">Head of the department :</h2><br>
		<%
		TeacherDAO tobj= new TeacherDAO();
		String loc = "http://localhost:8080/StudentLoginPage/teacherImages/";
		loc += tobj.HODDetails(un);
		pageContext.setAttribute("loc", loc);
		%>
		<img src ="${ loc }" width="130" height="130" alt="HOD_pic" style="float:left">
		
		<% 
		ArrayList<String> lst= new ArrayList<String>();
		lst=tobj.HodInfo(un);
		if(!lst.isEmpty()) 
		{	
		%>
		<div style="text-align: left;line-height: 0.3;padding-left:150px">
			<h3 style="display: inline-block"> Name :</h3>   <h5 style="display: inline-block"><%out.print(lst.get(0)+' '); %> </h5><br>
			<h3 style="display: inline-block">mobile no :</h3> <h5 style="display: inline-block"><%out.print(lst.get(1)+' '); %></h5><br>
			<h3 style="display: inline-block">email :</h3> <h5 style="display: inline-block"><%out.print(lst.get(2)+' '); lst.clear();%></h5><br><br>
		</div>
		<%
		}
		%>
		<% 
		//lst= new ArrayList<String>();
		lst=tobj.getDetails(un);
		%>
		<br><h3 style="text-align: left">Faculty details :</h3><br>
		<table>
		<tr>
			<th>Name    </th>
			<th>Subject   </th>
			<th>mail    </th>
			<th>Phone Number    </th>
		</tr>
		<% 
		for(int i=0;i<lst.size();i=i+4)
		{
		%>
			<tr>
				<td><b><% out.print(lst.get(i)+" ");  %>  </b></td>
				<td> <% out.print(lst.get(i+1)+" ");  %> </td>
				<td><% out.print(lst.get(i+2)+" ");  %></td>
				<td><% out.print(lst.get(i+3)+" ");  %></td>
				
			</tr>
		<%
		}
		%>
		</table>
	</div>
	
	
	<div class="right">
	<%
		if(obj.CheckDP(un) ) 
		{
			loc = "http://localhost:8080/StudentLoginPage/images/";
			try
			{
				 loc += obj.getImage(un);
				pageContext.setAttribute("loc", loc);
				%>
				<img src ="${ loc }" width="150" height="150" alt="student_pic">
				<br>
				<% 
				//out.println(loc);
			}
			catch (Exception e)
			{
				out.println("Image Display Error=" + e.getMessage());
			}
		}
		else
		{
			%>
			<form method="post" action="StudentUploadDp" enctype="multipart/form-data" >
				Upload Your Photo:<br>
				<input type="file" name="photo" size="50"/>
				<br>
            	<input type="submit" value="Save">
			</form>
			<br>
			<br>
			<%
		}
		details = obj.getDetails(un);
		
	%>
	<div style="line-height:0.3;padding:1px">
	<h3 style="display: inline-block">Roll No :</h3>   <h5 style="display: inline-block"><%out.print(details[0]); %> </h5><br>
	<h3 style="display: inline-block">Full Name :</h3>   <h5 style="display: inline-block"><%out.print(details[3]+' '+details[1]+" "+
	details[2]); %> </h5><br>
	<h3 style="display: inline-block">Email :</h3>   <h5 style="display: inline-block"><%out.print(details[4]); %> </h5><br>
	</div>
	
	</div>
</div>

</body>
</html>