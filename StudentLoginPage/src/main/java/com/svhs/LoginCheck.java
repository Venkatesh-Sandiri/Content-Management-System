package com.svhs;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String rno = request.getParameter("uname");
		String passw = request.getParameter("pass");
		String query = "select password from StudentLogin where rollNo='"+rno+"'";
		String url = "jdbc:mysql://localhost:3306/Student";
		String uname="root";
		String pass = "191981";
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,uname,pass);
			PreparedStatement st = con.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			//out.print("came inside filter");
			if(rs.next())
			{
				if(passw.equals(rs.getString("password")))
				{
					//out.print("came inside password equal");
					HttpSession ses = request.getSession();
					ses.setAttribute("uname", rno);
					response.sendRedirect("Home.jsp");
				}
				else
				{
					response.sendRedirect("index.jsp?log=fail");
				}
			}
			else
				response.sendRedirect("index.jsp?usr=fail");
		}
		catch(Exception e)
		{
			e.getMessage();
		}
	}

}
