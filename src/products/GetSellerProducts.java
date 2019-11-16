package products;

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
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class GetSellerProducts {
	
	@Resource (name = "TIWDS") //Using Inyection
	DataSource ds;
	boolean empty = true;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {

			String servername = "localhost";
			Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?autoReconnect=true&useSSL=false", "root", "root");
			
			if (con==null){
				System.out.println("--->UNABLE TO CONNECT TO SERVER:" + servername);
			} else {
				
			// 3- Obtain an Statement object -> st
				
				Statement st = con.createStatement();
				
			// 4.- Execute the query "select * from users" 
				
				ResultSet rs = st.executeQuery("select * from PRODUCTOS");
				
				ArrayList<HashMap<String, String>> productos = new ArrayList<>();
				while(rs.next()) {
					Blob blob = rs.getBlob("imagen");
					byte [] bytes = blob.getBytes(1l, (int)blob.length());
					String image = Base64.getEncoder().encodeToString(bytes);
					
					productos.add(new HashMap<String, String>(){{
						put("\"nombreProducto\"", "\"" + rs.getString("nombreProducto") + "\"");
						put("\"precio\"", "\"" + rs.getString("precio") + "\"");
						put("\"imagen\"", "\"" + image + "\"");
					}});
				}
				
				String res = productos.toString();
				res = res.replace('=',':');
				
				rs.close();
				
			// 6.- Close the statement and the connection
					
				st.close();
				con.close();
				
		        PrintWriter out = response.getWriter();
		        response.setCharacterEncoding("UTF-8");
		        out.print(res);
		        out.flush(); 
		        
			}
			
		}catch (Exception e) {
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				System.out.println(errors.toString());
		}
		
		return;					
	}

}
