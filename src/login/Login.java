package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import products.Producto;

public class Login {
	@Resource (name="TIWDS") //Using Inyection
	DataSource ds;
	boolean empty=true;

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String correo = request.getParameter("email");
		String contrasena = request.getParameter("password");
		
		try {

			/*Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("TIWDS");
			Connection con = ds.getConnection();*/
			
			String servername = "localhost";
			HttpSession sesion = request.getSession();
			
			Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?autoReconnect=true&useSSL=false", "root", "root");
			//Connection con = ds.getConnection();
			
			
			if (con==null){
				System.out.println("--->UNABLE TO CONNECT TO SERVER:" + servername);
			} else {
				
			// 3- Obtain an Statement object -> st
				Statement st = con.createStatement();
				
				ResultSet rtProductos = st.executeQuery("select * from PRODUCTOS");
				ArrayList<Producto> productos = new ArrayList<>();
				
				while(rtProductos.next()) {
					Blob blob = rtProductos.getBlob("imagen");
					byte [] bytes = blob.getBytes(1l, (int)blob.length());
					String image = Base64.getEncoder().encodeToString(bytes);
					
					productos.add(new Producto(
							rtProductos.getString("idProducto"), 
							rtProductos.getString("nombreProducto"), 
							rtProductos.getString("marca"), 
							rtProductos.getString("talla"), 
							rtProductos.getString("descripcionBreve"), 
							rtProductos.getFloat("precio"), 
							rtProductos.getInt("cantidad"), 
							rtProductos.getString("idUsuario"), 
							image));
				}
				
			// 4.- Execute the query "select * from users" 
				ResultSet rtVendedor = st.executeQuery("SELECT * FROM VENDEDORES WHERE username='"+correo+"'");
				
			// 5.- Iterate through the ResultSet obtained and add to the html page the id, name and surname of the users
				
				while(rtVendedor.next()) {
					empty = false;
					if(rtVendedor.first()!=false) {
						sesion.setAttribute("Usuario", rtVendedor.getString("username"));
						sesion.setAttribute("error", null);
						request.setAttribute("productos", productos);
						request.getRequestDispatcher("products.jsp").forward(request, response);
					}
				}
				
				ResultSet rtUsuario = st.executeQuery("select * from USUARIO where email='"+correo+"'");
				System.out.println("hola");
				
				while(rtUsuario.next()){
					empty=false;
					
					if(rtUsuario.first()!=false){
						if(rtUsuario.getString("contrasena").compareTo(contrasena)==0){
					    	sesion.setAttribute("Usuario", rtUsuario.getString("email"));
					    	sesion.setAttribute("NombreUsuario", rtUsuario.getString("nombre"));
					    	sesion.setAttribute("error", null);
					    	request.setAttribute("productos", productos);
					    	request.getRequestDispatcher("index-client.jsp").forward(request, response);

						}else{
							sesion.setAttribute("error", "contrasena");
						}
					}
				}
				
				if(empty==true){
						sesion.setAttribute("error", "cuenta");
						System.out.println("No existe esta cuenta");
						request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				
				rtProductos.close();
				rtUsuario.close();
				rtVendedor.close();
				
				
			// 6.- Close the statemente and the connection
				st.close();
				con.close();
				
			}
			
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			System.out.println(errors.toString());
		}
		
		return;
	}
		
}
