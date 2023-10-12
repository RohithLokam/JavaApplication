

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Insert")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter pwt=response.getWriter();
			String rol=Search.role;
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/Rohith","root","M1racle@123");
			String s=request.getParameter("empid");
			String s1=request.getParameter("fname");
			String s2=request.getParameter("lname");
			String s3=request.getParameter("phone");			
			String s4=request.getParameter("zip");
			String s5=request.getParameter("role");
			String s6=request.getParameter("branch");
			String s7=request.getParameter("depart");
			String s8=request.getParameter("dob");
//			String s9=s1.substring(0,1)+s2+s8.substring(0,2)+s8.substring(3,5)+s8.substring(8,10);
			String s9=s1.substring(0,1)+s2;
			DateTimeFormatter dtf=DateTimeFormatter.ofPattern("hhmm");
			LocalDateTime ldt=LocalDateTime.now();
			String dat=dtf.format(ldt);
//			String s10=Character.toUpperCase(s1.charAt(0))+s1.substring(1,4)+s2.charAt(s2.length()-1)+s2.charAt(s2.length()-2)+s2.charAt(s2.length()-3)+dat;
			String s10=s1.substring(0,1)+s2+dat;
			String s11=s1.substring(0,1)+s2+"@miraclesoft.com";
			PrintWriter pw=response.getWriter();
			if(rol.equalsIgnoreCase("trainer")) {
				if(s5.equalsIgnoreCase("hr")) {
					pwt.print("<h3>Your not allowed to Enter Hr Details</h3>");
					pwt.println(" ");
					RequestDispatcher rdt=request.getRequestDispatcher("insert.html");
					rdt.include(request, response);
				}
			}
			PreparedStatement ps=c.prepareStatement("insert into officer values(?,?,?,?,?,?,?,?,?,?,?,?)"); 
			ps.setString(1, s);
			ps.setString(2, s1);
			ps.setString(3, s2);
			ps.setString(4, s3);
			ps.setString(5, s4);
			ps.setString(6, s5);
			ps.setString(7, s6);
			ps.setString(8, s7);
			ps.setString(9, s8);
			ps.setString(10, s9);
			ps.setString(11, s10);
			ps.setString(12, s11);
			int i=ps.executeUpdate();

			if(i>0) {
				pw.write("<h1>Data Inserted Successfully..</h1>");
				RequestDispatcher rdt=request.getRequestDispatcher("Choice.html");
				rdt.include(request, response);
				pw.write(rol);
			}else {
				pw.write("<h1>Data Already Exist..</h1>");
				RequestDispatcher rdt=request.getRequestDispatcher("Choice.html");
				rdt.include(request, response);
			}
			c.close();
		}catch(Exception e) {
//			e.printStackTrace();
		}
	}
}
