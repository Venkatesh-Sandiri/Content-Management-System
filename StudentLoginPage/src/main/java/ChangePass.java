

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

@WebServlet("/ChangePass")
public class ChangePass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String rno = request.getParameter("rno");
		String query1 =" SELECT * FROM StudentDetails where rollNo= '"+rno+"' ";
		String url = "jdbc:mysql://localhost:3306/Student";
		try 
		{
			PrintWriter out = response.getWriter();
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,"root","191981");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query1);
			out.print("we are in try loop now");
			if(rs.next())
			{
					HttpSession ses = request.getSession();
					ses.setAttribute("usname", rno);
					response.sendRedirect("NewPass.jsp");
			}
				else
					response.sendRedirect("index.jsp?newPass=fail");
		}
		catch (Exception e) 
		{
			e.getMessage();
		}
	}
	protected void doGet(HttpServletRequest req,HttpServletResponse res)
	{
		String pass = req.getParameter("pass");
		HttpSession ses = req.getSession();
		String rno = ses.getAttribute("usname").toString();
		String query = " update StudentLogin set password = '"+pass+"' where rollNo = '"+rno+"'; ";
		String url="jdbc:mysql://localhost:3306/Student";
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,"root","191981");
			Statement st = con.createStatement();
			int n = st.executeUpdate(query);
			if(n!=0)
				res.sendRedirect("index.jsp?newPass=pass");
			else
				res.sendRedirect("index.jsp?newPass=fail");
		}
		catch(Exception e)
		{
			e.getMessage();
		}
	}
}
