

import java.io.IOException;
import java.io.PrintWriter;
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


@WebServlet("/hiii")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String user_name;
	static String uname;
	static String empid="";
	static String role="";

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter ();
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/Rohith","root","M1racle@123");
			uname=request.getParameter("uname");
			String password=request.getParameter("password");
			PreparedStatement ps=c.prepareStatement("select * from officer where username=? and password=?");
			ps.setString(1, uname);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				user_name=rs.getString("username");
				 role=rs.getString("role");
				 empid=rs.getString("empid");
				
				if(role.equalsIgnoreCase("hr") || role.equalsIgnoreCase("trainer")) {
					RequestDispatcher rd=request.getRequestDispatcher("Choice.html");
					rd.forward(request, response);
//					response.sendRedirect("Choice.html");  

				}else if(role.equalsIgnoreCase("trainee")){
					RequestDispatcher rd=request.getRequestDispatcher("choicet.html");
					rd.forward(request, response);
				}else {

					response.sendRedirect("index.html");  
				}
				}
			else {
				pw.print("Please Provide valid Credientials...");
				response.sendRedirect("index.html");  
			}	
		}catch(Exception e) {	

		}
	}
	
}
