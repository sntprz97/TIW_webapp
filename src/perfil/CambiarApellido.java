package perfil;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class CambiarApellido {

	@Resource (name="TIWDS") //Using Inyection
	DataSource ds;
	boolean empty=true;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String newApellido = request.getParameter("CambioApellido");
		System.out.println(newApellido);
		try {
			
			String servername = "localhost";
			HttpSession sesion = request.getSession();
			String id = sesion.getAttribute("Usuario").toString();
			System.out.println(id);
			Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?autoReconnect=true&useSSL=false", "root", "root");
			
			if (con == null){
				System.out.println("--->UNABLE TO CONNECT TO SERVER:" + servername);
			} else {
				
			// 3- Obtain an Statement object -> st and execute update query
				
				Statement st = con.createStatement();
				st.executeUpdate("UPDATE USUARIO SET apellido1='"+newApellido+"' WHERE username='"+id+"'");
			
			// 4- Close statement and connection	
				
				st.close();
				con.close();
				
			}
		
		}catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			System.out.println(errors.toString());
		}
		
		Perfil p = new Perfil();
    	p.doGet(request, response);
    	
		return;
	}	
}
