package perfil;

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

public class Perfil {

	@Resource (name = "TIWDS") //Using Inyection
	DataSource ds;
	boolean empty = true;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {

			String servername = "localhost";
			HttpSession sesion = request.getSession();
			String id = sesion.getAttribute("Usuario").toString();
			Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?autoReconnect=true&useSSL=false", "root", "root");
			
			if (con==null){
				System.out.println("--->UNABLE TO CONNECT TO SERVER:" + servername);
			} else {
				
			// 3- Obtain an Statement object -> st
				
				Statement st = con.createStatement();
				
			// 4.- Execute the query "select * from users" 
				
				ResultSet rs = st.executeQuery("select * from USUARIO where email='"+id+"'");

			// 5.- Iterate through the ResultSet obtained and add to the jsp page the name, surname, email, user name and address of the users
				
				while(rs.next()){
					request.setAttribute("perfilA", rs.getString("nombre"));
					request.setAttribute("perfilB", rs.getString("apellido1"));
					request.setAttribute("perfilC", rs.getString("email"));
					request.setAttribute("perfilD", rs.getString("username"));
					request.setAttribute("perfilE", rs.getString("direccion"));
					
					request.getRequestDispatcher("/profile.jsp").forward(request, response);
				}	
				
				rs.close();
				
			// 6.- Close the statement and the connection
					
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