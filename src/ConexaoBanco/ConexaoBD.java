package ConexaoBanco;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoBD {

	private static String url = "jdbc:mysql://localhost:3306/jsp?useTimezone=true&serverTimezone=UTC";  //mudar localhost:3306/jsp
	private static String senha = "";
	private static String user = "";
	private static Connection con = null;
	
	static {
		conectar();
	}
	
	public ConexaoBD() {
		conectar();
	}
	
	private static void conectar() {
		try {
			if(con == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(url, user, senha);
				con.setAutoCommit(false);
				System.out.println("OK");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConexao() {
		return con;
	}
}
