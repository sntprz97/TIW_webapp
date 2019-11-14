package signUp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class SignUp {

	@Resource (name = "TIWDS") //Using Inyection
	DataSource ds;
	boolean empty = true;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombre = request.getParameter("name");
		String apellido = request.getParameter("surname");
		String usuario = request.getParameter("username");
		String correo = request.getParameter("email");
		String contrasena = request.getParameter("password");
		
		try {
			
			String servername = "localhost";
			HttpSession sesion = request.getSession();
			Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?autoReconnect=true&useSSL=false", "root", "root");
			//System.out.println("Successful connection");
			
			if(con == null) {
				System.out.println("--->Unable to connect to the server: " + servername);
			} else {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from USUARIO where email='"+ correo+"'");
				//System.out.print("haceprimeraquery"); //Para probar
				
				while (rs.next()){
					empty = false;
					sesion.setAttribute("error", "cuentaExiste");
					//System.out.println("llega"); //Prueba
				}	
				if(empty == true){
					rs = st.executeQuery("select idUsuario from USUARIO");
					int pos = 1;
					while(rs.next()){
						//System.out.println(rs.getInt("idUsuario"));
						if(rs.getInt("idUsuario") >= pos){
							pos = rs.getInt("idUsuario") + 1;
						}	
					}
				
					rs.close();
					String sql = "INSERT INTO `USUARIO` (`idUsuario`, `nombre`, `apellido1`, `username`, `email`, `contrasena`, `direccion`) VALUES ('"+pos+"', '"+nombre+"', '"+apellido+"', '"+usuario+"', '"+correo+"', '"+contrasena+"', '"+"null"+"');";
					st.executeUpdate(sql);
					sesion.setAttribute("Usuario", correo);
					sesion.setAttribute("NombreUsuario", nombre);
					
					st.close();
					con.close();
				}
			}
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			System.out.println(errors.toString());
		}
		
		String viewURL = "index2.jsp";
		request.getRequestDispatcher(viewURL).forward(request, response);
		return;
	}
}
