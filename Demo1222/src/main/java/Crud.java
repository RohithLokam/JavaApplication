

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Crud")
public class Crud extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String  s=request.getParameter("choicee");
	PrintWriter pw= response.getWriter();
	 if(s.equalsIgnoreCase("insert")) {
		 RequestDispatcher rd=request.getRequestDispatcher("insert.html");
			rd.forward(request, response);
	 }else if(s.equalsIgnoreCase("update")) {
		 RequestDispatcher rd=request.getRequestDispatcher("update.html");
			rd.forward(request, response);
	 }else if(s.equalsIgnoreCase("select")) {
		 RequestDispatcher rd=request.getRequestDispatcher("select.html");
			rd.forward(request, response);
	 }else if(s.equalsIgnoreCase("delete")) {
		 RequestDispatcher rd=request.getRequestDispatcher("delete.html");
			rd.forward(request, response);
	 }	else if(s.equalsIgnoreCase("selectall")) {
		 RequestDispatcher rd=request.getRequestDispatcher("SelectAll");
			rd.forward(request, response);
	 }	else if(s.equalsIgnoreCase("SelfUpdate")) {
		 RequestDispatcher rd=request.getRequestDispatcher("tupdate.html");
			rd.forward(request, response);
	 } else if(s.equalsIgnoreCase("exit")) {
		pw.print("<h2 style='color:blue;text-align:center;'>Thank you for visiting mss </h2>");
	 }	
		
	}

}
