

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
import javax.servlet.http.HttpSession;


@WebServlet("/hiii")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String user_name;
	static String uname;
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
				String  empid=rs.getString("empid");
				 String fn=rs.getString("firstname");
				 
				HttpSession hs=request.getSession();
				hs.setAttribute("emp",empid );
				hs.setAttribute("fname", fn);
				
				
				if(role.equalsIgnoreCase("hr") || role.equalsIgnoreCase("trainer")) {
					pw.print("<h2 style='text-align:right; background-color:blue; color:white'>welcome "+fn+"</h2>");
					RequestDispatcher rd=request.getRequestDispatcher("Choice.html");
					rd.include(request, response);
//					response.sendRedirect("Choice.html");  

				}else if(role.equalsIgnoreCase("trainee")){
					pw.print("<h2 style='text-align:right'>welcome "+fn+"</h2>");
					RequestDispatcher rd=request.getRequestDispatcher("choicet.html");
					rd.include(request, response);
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
