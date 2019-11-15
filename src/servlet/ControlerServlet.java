package servlet;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import cerrarSesion.CerrarSesion;
import login.Login;
import perfil.BorrarCuenta;
import perfil.CambiarPerfil;
import perfil.Perfil;
import products.AddProduct;
import products.DeleteProduct;
import products.GetProducts;
import products.ModifyProduct;
import signUp.SignUp;

/**
 * Servlet implementation class ControlerServlet
 */
@WebServlet({"/ControlerServlet", "/signUp.html", "/login.html", "/cerrarSesion.html", "/profile.html", "/borrarCuenta.html", "/cambiarPerfil.html", "/checkout.html", "/getProducts", "/addProduct", "/deleteProduct", "/modifyProduct"})
@MultipartConfig
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
		
		if (sReq.compareTo("/profile.html")==0){
	    	Perfil p = new Perfil();
	    	p.doGet(request, response);
	    }
		
		if (sReq.compareTo("/cerrarSesion.html")==0){
	    	CerrarSesion c = new CerrarSesion();
	    	c.doGet(request, response);
		}
		
		if (sReq.compareTo("/borrarCuenta.html")==0){
	    	BorrarCuenta m = new BorrarCuenta();
	    	m.doGet(request, response);	
	    }

		if (sReq.compareTo("/getProducts")==0){
			GetProducts m = new GetProducts();
	    	m.doGet(request, response);	
	    }
		
		if (sReq.compareTo("/deleteProduct")==0){
	    	DeleteProduct d = new DeleteProduct();
	    	d.doGet(request, response);	
	    }
		
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
	    	Login l = new Login();
	    	l.doPost(request, response);	
	    }
		
		if (sReq.compareTo("/cambiarPerfil.html")==0){
	    	CambiarPerfil m = new CambiarPerfil();
	    	m.doPost(request, response);
	    }
		
		if (sReq.compareTo("/addProduct")==0){
	    	AddProduct a = new AddProduct();
	    	a.doPost(request, response);
	    }
		
		if (sReq.compareTo("/modifyProduct")==0){
	    	ModifyProduct m = new ModifyProduct();
	    	m.doPost(request, response);
	    }
		
	}

}
