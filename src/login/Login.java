package login;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.naming.Context;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import java.util.Iterator;


public class Login {
	@Resource (name="TIWDS") //Using Inyection
	DataSource ds;
	boolean empty=true;

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String correo= request.getParameter("email");
		String contrasena= request.getParameter("contrasena");
		
		try {


			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("TIWDS");
			Connection con = ds.getConnection();
			
			String servername = "localhost";
			HttpSession sesion = request.getSession();
			
			//Connection con = null;
			//con = ds.getConnection();
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb", "root", "root");
			//Connection con = ds.getConnection();
			
			
			if (con==null){
				System.out.println("--->UNABLE TO CONNECT TO SERVER:" + servername);
			} else {
				
			// 3- Obtain an Statement object -> st
				Statement st = con.createStatement();
				
			// 4.- Execute the query "select * from users" 
				ResultSet rs = st.executeQuery("select * from USUARIO where email='"+ correo+"'");
				
			// 5.- Iterate through the ResultSet obtained and add to the html page the id, name and surname of the users
				
			
					
				while (rs.next()){
					empty=false;
					
					if(rs.first()!=false){
						
					if(rs.getString("contrasena").compareTo(contrasena)==0){
						
					    	sesion.setAttribute("Usuario", rs.getString("username"));
					    	sesion.setAttribute("error", null);
						
					}else{
						sesion.setAttribute("error", "contrasena");
						
					}
					
				}}
				if(empty==true){
					
						
						sesion.setAttribute("error", "cuenta");
						
					}
				rs.close();
				
				
			// 6.- Close the statemente and the connection
				st.close();
				con.close();
				
			}
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			System.out.println(errors.toString());
		}

		 
		
    	String viewURL = "index.jsp";
    	
		//System.out.println(correo);
		request.getRequestDispatcher(viewURL).forward(request, response);
		return;
	}
		
}
