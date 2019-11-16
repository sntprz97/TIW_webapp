package logout;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("CerrarSesion.java");
		HttpSession sesion = request.getSession();
    	sesion.invalidate();
    	String viewURL = "index.jsp";
    	   	
		request.getRequestDispatcher(viewURL).forward(request, response);
		return;
	}
}
