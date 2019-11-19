package servlet;

import java.io.IOException;

import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import cerrarSesion.CerrarSesion;
import checkout.Checkout;
import login.Login;
import perfil.BorrarCuenta;
import perfil.CambiarPerfil;
import perfil.Perfil;
import products.AddProduct;
import products.DeleteProduct;
import products.GetProducts;
import products.GetSellerProducts;
import products.ModifyProduct;
import signUp.SignUp;
import messageJMS.SendMessage;
import messageJMS.BroadcastMessage;
import messageJMS.ConsumeMessage;
import messageJMS.ReceiveMessage;

/**
 * Servlet implementation class ControlerServlet
 */
@WebServlet({"/ControllerServlet", "/signUp.html", "/login.html", "/cerrarSesion.html", "/profile.html", "/borrarCuenta.html", "/cambiarPerfil.html", "/checkout.html", "/getProducts", "/addProduct", "/deleteProduct", "/modifyProduct", "/getSellerProducts", "/misMensajes.html", "/enviarMensaje.html"})
@MultipartConfig
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource (name="TIWDS") //Using Inyection
	DataSource ds;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
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
		
		if (sReq.compareTo("/misMensajes.html")==0){
	    	ReceiveMessage rm = new ReceiveMessage();
	    	try {
	    		rm.doGet(request, response);
	    	} catch(NamingException e) {
	    		e.printStackTrace();
	    	}  		
	    }
		
		if (sReq.compareTo("/getProducts")==0){
			GetProducts m = new GetProducts();
	    	m.doGet(request, response);	
	    }
		
		if (sReq.compareTo("/getSellerProducts")==0){
	    	GetSellerProducts g = new GetSellerProducts();
	    	g.doGet(request, response);	
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
		
		if (sReq.compareTo("/deleteProduct")==0){
	    	DeleteProduct d = new DeleteProduct();
	    	d.doPost(request, response);	
	    }
		
		if (sReq.compareTo("/enviarMensaje.html")==0){
			HttpSession sesion = request.getSession();
			String status = sesion.getAttribute("Status").toString();
			if(status == "buyer") {
				SendMessage sm = new SendMessage();
				try {
		    		sm.doPost(request, response);
		    	} catch(NamingException e) {
		    		e.printStackTrace();
		    	} 
			} else {
				BroadcastMessage pm = new BroadcastMessage();
				try {
		    		pm.doPost(request, response);
		    	} catch(NamingException e) {
		    		e.printStackTrace();
		    	} 
			} 		
	    }
		
		if (sReq.compareTo("/checkout.html")==0){
	    	Checkout d = new Checkout();
	    	d.doPost(request, response);	
	    }
		
	}

}
