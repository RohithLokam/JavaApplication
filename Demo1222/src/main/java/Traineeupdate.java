

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
import javax.servlet.http.HttpSession;

@WebServlet("/Traineeupdate")
public class Traineeupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
//			String s1=Search.empid;
			
			HttpSession hs1=request.getSession();
			String s1=(String)hs1.getAttribute("emp");
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			String s=request.getParameter("val");
			String s2=request.getParameter("sel");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/Rohith","root","M1racle@123");
			PrintWriter pw=response.getWriter();
			
			String sq="update officer set "+s2+"=? where empid=? ";
			PreparedStatement ps=c.prepareStatement(sq); 
			ps.setString(1, s);
			ps.setString(2, s1);
			int i=ps.executeUpdate();
			if(i>0) {
				pw.print("<h1>Data updated Successfully..</h1>");
				String sqe="select * from officer where empid=?";
				System.out.println(sqe);
				PreparedStatement ps1=c.prepareStatement(sqe);
				ps1.setString(1,s1 );
				pw.print("<table width=50% border=1>");
				pw.print("<caption>Updated Employee Details:</caption>");
				ResultSet rs=ps1.executeQuery();
				pw.print("<br><br>");
				ResultSetMetaData rsmd=rs.getMetaData();
				int iw=rsmd.getColumnCount();
				pw.print("<tr>");
				for(int j=1;j<=iw;j++) {
					pw.print("<th>"+rsmd.getColumnName(j)+"</th>");
				}
				pw.print("</tr>");
				while(rs.next()) {
					pw.print("<tr>");
					for(int k=1;k<=iw;k++) {
						pw.print("<td>"+rs.getString(k)+"</td>");
					}
					pw.print("</tr>");
				}
				pw.print("</table>");
			}else {
				pw.write("<h1>Data Not Updated</h1>");

			}
			
			c.close();
		}catch(Exception e) {
//			e.printStackTrace();
		}
	}

}
