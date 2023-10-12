

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String rol=Search.role;
			String seq="";
			System.out.println("hii");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/Rohith","root","M1racle@123");
			String s=request.getParameter("id");
			PrintWriter pw=response.getWriter();
			if(rol.equalsIgnoreCase("trainer")) {
				seq=" and role!='hr'";
			}
			PreparedStatement ps=c.prepareStatement("delete from officer where empid=? " +seq); 
			ps.setString(1, s);
			int i=ps.executeUpdate();
			if(i>0) {
				RequestDispatcher rd=request.getRequestDispatcher("Choice.html");
				rd.include(request, response);
				pw.write("<h1>Data Deleted Successfully..</h1>");
			}else {
				pw.write("<h1>Data Not Deleted..</h1>");
			}
			c.close();
		}catch(Exception e) {
//			e.printStackTrace();
		}
	}

}
