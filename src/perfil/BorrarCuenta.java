package perfil;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class BorrarCuenta {

	@Resource (name="TIWDS") //Using Inyection
	DataSource ds;
	boolean empty=true;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			String servername = "localhost";
			HttpSession sesion = request.getSession();
			String id = sesion.getAttribute("Usuario").toString();
			Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?autoReconnect=true&useSSL=false", "root", "root");
			
			if (con==null){
				System.out.println("--->UNABLE TO CONNECT TO SERVER:" + servername);
			}else{
				
			// 3- Obtain an Statement object -> st
				Statement st = con.createStatement();
				ResultSet rs= st.executeQuery("SELECT * FROM USUARIO WHERE email='"+id+"'");
				
				while(rs.next()){
					if(empty != false){
						
						st.executeUpdate("DELETE FROM USUARIO WHERE email='"+id+"'");
						st.close();
						con.close();
						
						sesion.invalidate();
						request.getRequestDispatcher("profile.jsp").forward(request, response);
						
					}else{
					
						sesion.setAttribute("error", "UsuarioExistente");
						
					}
				}	
			}
			
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			System.out.println(errors.toString());
		}
		
		Perfil p = new Perfil();
    	p.doGet(request, response);
		return;
	}

}
