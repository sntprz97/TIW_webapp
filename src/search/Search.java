package search;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class Search {
	@Resource (name="TIWDS") //Using Inyection
	DataSource ds;
	Connection con = null;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userSearch = request.getParameter("userSearch");

		try {
			String servername = "localhost";
			HttpSession sesion = request.getSession();
			Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?autoReconnect=true&useSSL=false", "root", "root");
			Vector <Product> ret = new Vector<Product>();
			if(con == null) {
				System.out.println("--->Unable to connect to the server: " + servername);
			} else {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from PRODUCTOS where nombreProducto LIKE'%"+userSearch+"%'OR descripcionbreve LIKE '%"+userSearch+"%'");
			}
		} catch(Exception e) {

		}
		
		request.getRequestDispatcher("/search.jsp").forward(request, response);
	}
}
