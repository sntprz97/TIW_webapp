package products;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.sql.rowset.serial.SerialBlob;

public class AddProduct {

	@Resource (name = "TIWDS") //Using Inyection
	DataSource ds;
	boolean empty = true;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String nombreProducto = request.getParameter("nombreProducto");
			String marca = request.getParameter("marca");
			String talla = request.getParameter("talla");
			String descripcion = request.getParameter("desc");
			String precio = request.getParameter("precio");
			String cantidad = request.getParameter("cantidad");
			String imagenProducto = request.getParameter("imagen");

			byte[] bytes = Base64.getDecoder().decode(imagenProducto);
			
			String servername = "localhost";
			HttpSession sesion = request.getSession();
			String id = sesion.getAttribute("Usuario").toString();
			Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?autoReconnect=true&useSSL=false", "root", "root");
			
			if (con==null){
				System.out.println("--->UNABLE TO CONNECT TO SERVER:" + servername);
			}else{
				Statement st = con.createStatement();
				ResultSet rs = null;
				if(empty == true){
					rs = st.executeQuery("select idProducto from PRODUCTOS");
					int pos = 1;
					while(rs.next()){
						if(rs.getInt("idProducto") >= pos){
							pos = rs.getInt("idProducto") + 1;
						}	
					}
				
					rs.close();
					PreparedStatement pst = con.prepareStatement("INSERT INTO `PRODUCTOS` VALUES ('"+pos+"', '"+nombreProducto+"', '"+marca+"', '"+talla+"', '"+descripcion+"', '"+precio+"', '" + cantidad +"', '"+id+"', (?));");
					pst.setBlob(1, new ByteArrayInputStream(bytes),bytes.length);
					pst.executeUpdate();
					sesion.setAttribute("Position", pos);
					
					st.close();
					con.close();
				// 3- Obtain an Statement object -> st
				
				}
			}
			
		}catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			System.out.println(errors.toString());
		}
		
		request.getRequestDispatcher("seller.jsp").forward(request, response);	//Cambiar en un futuro por la página "My products"
		return;
	}	
}
