

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SelectAll
 */
@WebServlet("/SelectAll")
public class SelectAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType ("text/html");
		PrintWriter pw=response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/Rohith","root","M1racle@123");
			String sq="select * from officer ";
			System.out.println(sq);
			PreparedStatement ps=c.prepareStatement(sq);
			pw.print("<table width=50% border=1>");
			pw.print("<caption>Employee Details:</caption>");
			ResultSet rs=ps.executeQuery();
			pw.print("<br><br>");
			ResultSetMetaData rsmd=rs.getMetaData();
			int i=rsmd.getColumnCount();
			pw.print("<tr>");
			for(int j=1;j<=i;j++) {
				pw.print("<th>"+rsmd.getColumnName(j)+"</th>");
			}
			pw.print("</tr>");
			while(rs.next()) {
				pw.print("<tr>");
				for(int k=1;k<=i;k++) {
					pw.print("<td>"+rs.getString(k)+"</td>");
				}
				pw.print("</tr>");
			}
			pw.print("</table>");
			}catch(Exception e) {

		}finally {
			pw.close();
		}
	}

}
