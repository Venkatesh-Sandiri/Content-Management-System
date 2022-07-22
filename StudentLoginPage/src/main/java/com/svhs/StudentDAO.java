package com.svhs;
import java.sql.DriverManager;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import com.mysql.cj.Session;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.util.*;

public class StudentDAO {
	Connection con;
	PreparedStatement st;
	ResultSet rs;
	public StudentDAO()
	{
		String url = "jdbc:mysql://localhost:3306/Student";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, "root", "191981");
		}
		catch (ClassNotFoundException | SQLException e )
		{
			e.printStackTrace();
		}
	}
	public boolean checkDetails(String rno,String mail,String phno) throws SQLException
	{
		String query1 = "select * from StudentLogin where rollNo=?";
		String query2 = "select * from StudentDetails where mail=?";
		String query3 = "select * from StudentDetails where phno=?";
		String url = "jdbc:mysql://localhost:3306/Student";
		String uname = "root";
		String pass = "191981";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,uname,pass);
			PreparedStatement st = con.prepareStatement(query1);
			st.setString(1, rno);
			ResultSet rs = st.executeQuery();
			if(rs.next())
				return false;
			
			
			st = con.prepareStatement(query2);
			st.setString(1, mail);
			rs = st.executeQuery();
			if(rs.next())
				return false;
			
			st = con.prepareStatement(query3);
			st.setString(1, phno);
			rs = st.executeQuery();
			if(rs.next())
				return false;
			else
				return true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public String createNewUser(String rno,String pas, String fname, String mname, String lname, String mail, String phno,String dept,String sec) throws SQLException
	{
		String query1 = "insert  into StudentLogin values('"+rno+"','"+pas+"')";
		String query2 = "insert into StudentDetails values('"+rno+"','"+fname+"','"+mname+"','"+lname+"','"+mail+"','"+phno+"','"+dept+
						"','"+sec+"')";
		String url = "jdbc:mysql://localhost:3306/Student";
		String uname = "root";
		String pass = "191981";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,uname,pass);
			Statement st = con.createStatement();
			int rs1=0,rs2=0;
			rs1 = st.executeUpdate(query1);
			if(rs1!=0)
			{
				rs2 = st.executeUpdate(query2);
				if(rs2!=0)
					return("success");
				else
					return("Error in creating details");
			}	
			else
				return ("Unable to create user at this time");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return("Unable to create user");
	}
	public String[] getDetails(String rno) throws SQLException
	{
		String arr[]=new String[7];
		String query1 ="SELECT * FROM StudentDetails where rollNo='"+rno+"'";
		String url = "jdbc:mysql://localhost:3306/Student";
		String uname = "root";
		String pass = "191981";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,uname,pass);
			PreparedStatement st = con.prepareStatement(query1);
			ResultSet rs = st.executeQuery();
			if(rs.next())
			{
				arr[0]=rs.getString("rollNo");
				arr[1] = rs.getString("fname");
				arr[2]=rs.getString("mname");
				arr[3]=rs.getString("lname");
				arr[4]=rs.getString("mail");
				arr[5]=rs.getString("phno");
				arr[6]=rs.getString("dept");
				arr[7]=rs.getString("sec");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		return(arr);
	}
	public boolean CheckDP(String rno)
	{
		String query1 =" SELECT * FROM StudentDp where pid='"+rno+"'";
		String url = "jdbc:mysql://localhost:3306/Student";
		String uname = "root";
		String pass = "191981";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,uname,pass);
			PreparedStatement st = con.prepareStatement(query1);
			ResultSet rs = st.executeQuery();
			if(rs.next())
			{
				if(rs.getString("location")!=null)
					return true;
			}
			else
			{
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		return false;
	}
	public String getImage(String rno) throws SQLException, IOException, ClassNotFoundException 
	{
		String sql = "SELECT * FROM StudentDp WHERE pid ='"+rno+"'";
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
				int ind = loc.lastIndexOf("/");
				loc = loc.substring(ind+1);
				return(loc);
			}
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
			throw ex;
		}
		return null;
	}
	public String[] getClass(String un) throws SQLException
	{
		String res[] = new String[2];
		
		String query="select department,section from StudentDetails where rollNo='"+un+"'";
		
		st = con.prepareStatement(query);
		rs = st.executeQuery();
		//System.out.println("came here get class");
		int tmp=0;
		if(rs.next())
		{
			res[0]=rs.getString("department");
			tmp=rs.getInt("section");
		}
		res[1]=Integer.toString(tmp);
		return res;
	}
	public ArrayList<String> getSubjects(String rno)
	{
		ArrayList<String> arr = new ArrayList<String>();
		
		return arr;
	}
}
