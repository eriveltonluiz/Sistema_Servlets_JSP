package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConexaoBanco.ConexaoBD;
import beans.Telefone;

public class DaoTelefone {
	private Connection con;

	public DaoTelefone() {
		con = ConexaoBD.getConexao();
	}

	public void Inserir(Telefone tel) {

		try {
			String sql = "insert into telefones(numero,tipo,usuario) values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, tel.getNumero());
			ps.setString(2, tel.getTipo());
			ps.setLong(3, tel.getUsuario());
			ps.execute();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public List<Telefone> listar(Long user) throws Exception {
		String sql = "select * from telefones where usuario = " + user;
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Telefone> list = new ArrayList<Telefone>();

		while (rs.next()) {
			Telefone tel = new Telefone();
			tel.setIdTel(rs.getLong("idTel"));
			tel.setNumero(rs.getString("numero"));
			tel.setTipo(rs.getString("tipo"));
			tel.setUsuario(rs.getLong("usuario"));
			list.add(tel);
			System.out.println(tel.getIdTel());
			System.out.println(tel.getNumero());
			System.out.println(tel.getTipo());
			System.out.println(tel.getUsuario());
		}
		return list;
	}
	
	public void deletar(String id){
		try {
		String sql = "delete from telefones where idTel = " + id;
		PreparedStatement ps = con.prepareStatement(sql);
		ps.execute();
		con.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
