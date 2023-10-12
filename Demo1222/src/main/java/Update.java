

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/updat")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String rol=Search.role;
			String se="";
			Class.forName("com.mysql.cj.jdbc.Driver");
			String s=request.getParameter("val");
			String s1=request.getParameter("emp");
			String s2=request.getParameter("sel");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/Rohith","root","M1racle@123");
			PrintWriter pw=response.getWriter();
			if(rol.equalsIgnoreCase("trainer")) {
				se=" and role!='hr'";
			}
			
			String sq="update officer set "+s2+"=? where empid=? "+se;
			PreparedStatement ps=c.prepareStatement(sq); 
			ps.setString(1, s);
			ps.setString(2, s1);
			int i=ps.executeUpdate();
			if(i>0) {
				pw.print("<h1>Data updated Successfully..</h1>");
			}else {
				pw.write("<h1>Data Not Updated</h1>");

			}
			
			c.close();
		}catch(Exception e) {
//			e.printStackTrace();
		}
	}

}
