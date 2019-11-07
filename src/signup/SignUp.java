package signup;

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

public class SignUp {

	@Resource (name = "TIWDS") //Using Inyection
	DataSource ds;
	boolean empty = true;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contrasena = request.getParameter("password");
		String correo = request.getParameter("email");
		String usuario = request.getParameter("Username");
		
		
		try {
			String servername = "localhost";
			HttpSession sesion = request.getSession();
			Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://:3306/usersdb", "root", "root");
			
			if(con == null) {
				System.out.println("--->Unable to connect to the server: " + servername);
			} else {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from USUARIO where email='"+ correo+"'");
				System.out.println("haceprimeraquery"); //Para probar
				
				while (rs.next()){
					empty = false;
					sesion.setAttribute("error", "cuentaExiste");
					System.out.println("llega"); //Prueba
				}	
				if(empty == true){
					rs = st.executeQuery("select idUsuario from USUARIO");
					int pos = 1;
					while(rs.next()){
						System.out.println(rs.getInt("idUsuario"));
						if(rs.getInt("idUsuario") >= pos){
							System.out.println(pos);
							pos = rs.getInt("idUsuario") + 1;
						}
					}
				
				st.close();
				con.close();
				}
			}
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			System.out.println(errors.toString());
		}
		
		String viewURL = "index.jsp";
    	
		System.out.println(correo);
		request.getRequestDispatcher(viewURL).forward(request, response);
		return;
	}
}
