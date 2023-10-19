

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Crud")
public class Crud extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String  s=request.getParameter("choicee");
	PrintWriter pw= response.getWriter();
	HttpSession hs=request.getSession();
	String fn=(String)hs.getAttribute("fname");
	 if(s.equalsIgnoreCase("insert")) {
			pw.print("<h2 style='text-align:right'>welcome "+fn+"</h2>");
		 RequestDispatcher rd=request.getRequestDispatcher("insert.html");
			rd.include(request, response);
	 }else if(s.equalsIgnoreCase("update")) {
			pw.print("<h2 style='text-align:right'>welcome "+fn+"</h2>");
		 RequestDispatcher rd=request.getRequestDispatcher("update.html");
			rd.include(request, response);
	 }else if(s.equalsIgnoreCase("select")) {
			pw.print("<h2 style='text-align:right'>welcome "+fn+"</h2>");
		 RequestDispatcher rd=request.getRequestDispatcher("select.html");
			rd.include(request, response);
	 }else if(s.equalsIgnoreCase("delete")) {
			pw.print("<h2 style='text-align:right'>welcome "+fn+"</h2>");
		 RequestDispatcher rd=request.getRequestDispatcher("delete.html");
			rd.include(request, response);
	 }	else if(s.equalsIgnoreCase("selectall")) {
		 RequestDispatcher rd=request.getRequestDispatcher("SelectAll");
			rd.forward(request, response);
	 }	else if(s.equalsIgnoreCase("SelfUpdate")) {
			pw.print("<h2 style='text-align:right'>welcome "+fn+"</h2>");
		 RequestDispatcher rd=request.getRequestDispatcher("tupdate.html");
			rd.include(request, response);
	 } else if(s.equalsIgnoreCase("exit")) {
			pw.print("<h2 style='text-align:right'>Bye "+fn+"</h2>");

		pw.print("<h2 style='color:blue;text-align:center;'>Thank you for visiting mss </h2>");
	 }	
		
	}

}
