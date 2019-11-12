package cerrarSesion;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CerrarSesion {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
    	sesion.invalidate();
    		
    	String viewURL = "profile.jsp";
    	   	
		request.getRequestDispatcher(viewURL).forward(request, response);
		return;
	}
}
