package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ConexaoBanco.ConexaoBD;

public class DaoLogin {
	private Connection con;
	
	public DaoLogin() {
		con = ConexaoBD.getConexao();
	}
	
	public boolean validarLogin(String login, String senha) throws SQLException {
		String sql = "select * from usuario where login = '"+ login + "' and senha = '"+ senha + "'";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			return true;
		}else {
			return false;
		}
	}
}
