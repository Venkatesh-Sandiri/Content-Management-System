package com.svhs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

@WebServlet("/TeacherDAO")
public class TeacherDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement st;
	ResultSet rs;
    public TeacherDAO() 
    {
        String url="jdbc:mysql://localhost:3306/Teacher";
        String uname="root";
        String pass="191981";
        try
        {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	con = DriverManager.getConnection(url,uname,pass);		
        }
        catch(Exception e)
        {
        	e.getMessage();
        }
    }
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	public ArrayList<String> getDetails(String un) throws SQLException
	{
		StudentDAO obj = new StudentDAO();
		String arr[] = new String[2];
		arr=obj.getClass(un);
		int sec=Integer.parseInt(arr[1]);
		String toGetTeacherDetails="select fname,mname,lname,subToTeach,mail,phno from Details where deptToTeach='"+arr[0]+"' and secToTeach="+sec;
		//System.out.println(arr[0]+"  "+sec);
		st = con.prepareStatement(toGetTeacherDetails);
		rs = st.executeQuery();
		ArrayList<String> lst = new ArrayList<String>();
		while(rs.next())
		{
			lst.add(rs.getString("lname")+" "+rs.getString("fname")+" "+rs.getString("mname"));
			lst.add(rs.getString("subToTeach"));
			lst.add(rs.getString("mail"));
			lst.add(rs.getString("phno"));
		}
		//System.out.println("end of getDetails func");
    	return lst;
	}
	public String HODDetails(String rno)
	{
			String loc="";
			String query="select pic from Details where department =? and hod='1'";
			//System.out.println(rno);
			String queryToGetDept="select department from StudentDetails where rollNo='"+rno+"'";
			String Studurl="jdbc:mysql://localhost:3306/Student";
	        String TeacherUrl="jdbc:mysql://localhost:3306/Teacher";
			try
			{
				con = DriverManager.getConnection(Studurl,"root","191981");
				st=con.prepareStatement(queryToGetDept);
				rs=st.executeQuery();
				if(rs.next())
				{
					//System.out.println("first query executed");
					String dep=rs.getString("department");
					query="select pic from Details where department ='"+dep+"' and hod='1'";
					//System.out.println("came into 2nd query");
					con = DriverManager.getConnection(TeacherUrl,"root","191981");
					st=con.prepareStatement(query);
					//System.out.println(rs.getString("department"));
					//st.setString(1,dep );
					//System.out.println(query);
					rs=st.executeQuery();
					if(rs.next())
						loc=rs.getString("pic");
					//System.out.println("got loc as "+loc);
					int ind = loc.lastIndexOf("/");
					loc = loc.substring(ind+1);
					//System.out.println("2nd query excuted");
					//System.out.println("loc="+loc);
					return(loc);
				}
			}
			catch(Exception e)
	        {
	        	e.getMessage();
	        }
			//System.out.println("returning");
			return loc;
	}
	public ArrayList<String> HodInfo(String rno)
	{
		ArrayList<String> arr=new ArrayList<String>();
		String query="select fname,mname,lname,phno,mail from Details where department =? and hod='1'";
		//System.out.println(rno);
		String queryToGetDept="select department from StudentDetails where rollNo='"+rno+"'";
		String studUrl="jdbc:mysql://localhost:3306/Student";
        String teacherUrl="jdbc:mysql://localhost:3306/Teacher";
       // System.out.println("came here");
		try
		{
			con=DriverManager.getConnection(studUrl,"root","191981");
			st=con.prepareStatement(queryToGetDept);
			rs=st.executeQuery();
			//System.out.println(rno);
			if(rs.next())
			{
				//System.out.println("first query executed");
				con=DriverManager.getConnection(teacherUrl,"root","191981");
				st=con.prepareStatement(query);
				st.setString(1, rs.getString("department"));
				//System.out.println(st);
				rs=st.executeQuery();
				if(rs.next())
				{
					//System.out.println("got 2nd query result");
					arr.add(rs.getString("lname")+"."+rs.getString("fname")+" "+rs.getString("mname"));
					arr.add(rs.getString("phno"));
					arr.add(rs.getString("mail"));
					//System.out.println("added lists to array");
				}
			}
			
		}
		catch(Exception e)
		{
			System.out.println("error is :"+e.getMessage());
		}
		//System.out.println("returning");
		return arr;
	}
}

