package com.svhs;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.svhs.StudentDAO;

@WebServlet("/NewRegister")
public class NewRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		StudentDAO obj = new StudentDAO();
		String rno = request.getParameter("rno");
		String mail = request.getParameter("mail");
		String phno = request.getParameter("phno");
		String dept = request.getParameter("dept");
		String sec = request.getParameter("sec");
		
		try
		{
		if(obj.checkDetails(rno,mail,phno))
		{
			String fname = request.getParameter("fname");
			String mname = request.getParameter("mname");
			String lname = request.getParameter("lname");
			String pass = request.getParameter("pass");
			String str = obj.createNewUser(rno,pass,fname,mname,lname,mail,phno,dept,sec);
			if(str.equals("success"))
			{
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp?l=success");
				rd.forward(request, response);
			}
		}
		else
		{
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp?l=failure");
			rd.forward(request, response);
		}
		}
		catch(Exception e)
		{
			PrintWriter out = response.getWriter();
			out.print(e.getMessage());
		}
		
	}

}
