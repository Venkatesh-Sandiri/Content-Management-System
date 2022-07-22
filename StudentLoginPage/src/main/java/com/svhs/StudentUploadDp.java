package com.svhs;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/StudentUploadDp")
@MultipartConfig
public class StudentUploadDp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		try 
		{
			//LocalDateTime dt = LocalDateTime.now();
			ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
			List<FileItem> file;
			file = sfu.parseRequest(request);
			//out.write("came here");
			for(FileItem i : file)
				i.write(new File("C:\\Users\\sandi\\eclipse-workspace\\StudentLoginPage\\src\\main\\webapp\\images\\" + i.getName()));
			//out.write("came here");
			String loc="";
			for(FileItem i : file)
			{
				loc = "C:/Users/sandi/eclipse-workspace/StudentLoginPage/src/main/webapp/images/" + i.getName();
			}
			//out.write(loc);
			String url = "jdbc:mysql://localhost:3306/Student";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,"root","191981");
			HttpSession session=request.getSession();
			String uname = session.getAttribute("uname").toString();
			out.print(uname);
			PreparedStatement pst = con.prepareStatement("insert into StudentDp values('"+uname+"','"+loc+"' )");
			int count = pst.executeUpdate();
			//out.write("second query executed");
			if(count>0)
			{
				response.sendRedirect("Logout");
			}
			else
			{
				out.print("unable to insert into database");
			}
		} 
		catch (Exception e) 
		{
				e.printStackTrace();
		}
		
		out.print("Files uploaded");
	}
		
}
