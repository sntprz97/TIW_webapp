package products;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Base64;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class ModifyProduct {

	@Resource (name="TIWDS") //Using Inyection
	DataSource ds;
	boolean empty=true;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String imagenProducto = request.getParameter("imagen");
		String idProducto = request.getParameter("idProducto");
		
		System.out.println(idProducto);
		
		byte[] bytes = Base64.getDecoder().decode(imagenProducto);
		
		try {
			
			String servername = "localhost";
			Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?autoReconnect=true&useSSL=false", "root", "root");
			
			if (con==null){
				
				System.out.println("--->UNABLE TO CONNECT TO SERVER:" + servername);
				
			}else{
				
				Statement st = con.createStatement();
				
				st.executeUpdate("UPDATE PRODUCTOS SET nombreProducto='"+request.getParameter("nombreProducto")+"' WHERE idProducto='"+idProducto+"'");
				st.executeUpdate("UPDATE PRODUCTOS SET marca='"+request.getParameter("marca")+"' WHERE idProducto='"+idProducto+"'");
				st.executeUpdate("UPDATE PRODUCTOS SET talla='"+request.getParameter("talla")+"' WHERE idProducto='"+idProducto+"'");
				st.executeUpdate("UPDATE PRODUCTOS SET descripcionbreve='"+request.getParameter("desc")+"' WHERE idProducto='"+idProducto+"'");
				st.executeUpdate("UPDATE PRODUCTOS SET precio='"+request.getParameter("precio")+"' WHERE idProducto='"+idProducto+"'");
				st.executeUpdate("UPDATE PRODUCTOS SET cantidad='"+request.getParameter("cantidad")+"' WHERE idProducto='"+idProducto+"'");
				
				PreparedStatement pst = con.prepareStatement("UPDATE PRODUCTOS SET imagen="+"(?)"+" WHERE idProducto='"+idProducto+"'");
				pst.setBlob(1, new ByteArrayInputStream(bytes),bytes.length);
				pst.executeUpdate();
				
				GetSellerProducts gsp = new GetSellerProducts();
				gsp.doGet(request, response);
				
				pst.close();
				st.close();
				con.close();
			}
			
		}catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			System.out.println(errors.toString());
		}
		
		return;
	}
}
