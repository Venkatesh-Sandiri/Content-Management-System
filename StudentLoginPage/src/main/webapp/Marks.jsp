<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@ page import="com.svhs.StudentDAO" %>
</head>
<body style="text-align:center">
	<h5><b>Result:</b></h5>
	
	<% 
	ArrayList<String> lst= new ArrayList<String>();
	StudentDAO obj= new StudentDAO();
	String un=session.getAttribute("uname").toString();
	lst=obj.getSubjects(un);
	if(lst.isEmpty())
	{
		%>
		<h4>No data to show</h4>
		<% 
	}
	else
	{
		for(int i=0;i<9;i++)
		{
		%>
			
		<% 
		}
		%>
	<table>
	<tr>
	<th>Name	</th>	
	<th>A-1		</th>
	<th>A-2		</th>	
	<th>A-3		</th>
	<th>A-4		</th>	
	<th>A-5		</th>	
	<th>A-6		</th>	
	<th>A-7		</th>	
	<th>A-8		</th>
	<th>MSE-1	</th>	
	<th>MSE-2	</th>
	</tr>
	</table>
	<%	
	}
	%>
</body>
</html>