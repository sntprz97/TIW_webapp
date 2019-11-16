package products;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class DeleteProduct {

	@Resource (name="TIWDS") //Using Inyection
	DataSource ds;
	boolean empty=true;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String servername = "localhost";
			HttpSession sesion = request.getSession();
			String id = sesion.getAttribute("Usuario").toString();
			String idProducto = request.getParameter("idProducto");
			Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?autoReconnect=true&useSSL=false", "root", "root");
			
			if (con==null){
				
				System.out.println("--->UNABLE TO CONNECT TO SERVER:" + servername);
				
			}else{
				
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM VENDEDORES WHERE username='"+id+"'");
				
				while(rs.next()) {
					if(empty != false) {
						st.executeUpdate("DELETE FROM PRODUCTOS WHERE idProducto='"+idProducto+"'");
						st.close();
						con.close();
						
						GetSellerProducts gsp = new GetSellerProducts();
						gsp.doGet(request, response);
						
						return;
						//Parche, cambiar en un futuro por "Mis productos"
						//De momento no invalido sesión, cuando misproductos esté hecho, al seleccionar sobre un producto se pondrá la pos con el id de ese producto
					}else {
						sesion.setAttribute("error", "ProductoExistente");
					}	
				}
			}	
		
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			System.out.println(errors.toString());
		}
		
		return;
	}
}