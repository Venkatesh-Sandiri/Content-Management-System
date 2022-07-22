package com.svhs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/GetImage")
@MultipartConfig
public class GetImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession ses = request.getSession();
		String un = ses.getAttribute("uname").toString();
		String sql = "SELECT * FROM StudentDp WHERE pid ='"+un+"'";
		String url = "jdbc:mysql://localhost:3306/Student";
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "root", "191981");
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			if (result.next()) 
			{
				String loc = result.getString("location");
				request.setAttribute("loc", loc);
				PrintWriter out = response.getWriter();
				out.print(loc);
				RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
				rd.forward(request, response);
				//request.getRequestDispatcher("Home.jsp?im=ok").forward(request, response);
				//response.sendRedirect("Home.jsp?im=ok");
				//PrintWriter out = response.getWriter();
				//out.write("in get image");
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		//request.getRequestDispatcher("Home.jsp").forward(request, response);
	}

}
