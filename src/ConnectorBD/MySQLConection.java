package ConnectorBD;

import java.sql.Connection;

public class MySQLConection {

	public static Connection getConexion() {
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/bd1";
			String usuario = "root";
			String contrase�a = "";
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
