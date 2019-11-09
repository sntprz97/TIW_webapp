package servlet;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import login.Login;
import signUp.SignUp;

/**
 * Servlet implementation class ControlerServlet
 */
@WebServlet({"/ControlerServlet", "/signUp.html", "/login.html"})
public class ControlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource (name="TIWDS") //Using Inyection
	DataSource ds;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String sReq;
		sReq = request.getServletPath();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String sReq;
	    sReq = request.getServletPath();
	   
		if (sReq.compareTo("/signUp.html")==0){
	    	SignUp s = new SignUp();
	    	s.doPost(request, response);
	    }
		if (sReq.compareTo("/login.html")==0){
	    	Login l= new Login();
	    	l.doPost(request, response);
	    	
	    	
	    }
	}

}
