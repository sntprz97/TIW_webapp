package perfil;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@MultipartConfig
public class CambiarPerfil {

	@Resource (name="TIWDS") //Using Inyection
	DataSource ds;
	boolean empty=true;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String servername = "localhost";
			HttpSession sesion = request.getSession();
			String id = sesion.getAttribute("Usuario").toString();
			Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?autoReconnect=true&useSSL=false", "root", "root");
			
			if (con==null){
				System.out.println("--->UNABLE TO CONNECT TO SERVER:" + servername);
			}else {
				
				// 3- Obtain an Statement object -> st
				
				Statement st = con.createStatement();
				
				// 4.- Execute the query "select * from users" 
				
				/*ResultSet rs = st.executeQuery("select * from USUARIO where username='"+id+"'");
				rs.close();
				System.out.println("todo bien");*/
				
				st.executeUpdate("UPDATE USUARIO SET nombre='"+request.getParameter("nombre")+"' WHERE email='"+id+"'");
				st.executeUpdate("UPDATE USUARIO SET apellido1='"+request.getParameter("apellido")+"' WHERE email='"+id+"'");	
				st.executeUpdate("UPDATE USUARIO SET username='"+request.getParameter("username")+"' WHERE email='"+id+"'");
				st.executeUpdate("UPDATE USUARIO SET direccion='"+request.getParameter("direccion")+"' WHERE email='"+id+"'");
				
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
