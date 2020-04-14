package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConexaoBanco.ConexaoBD;
import beans.Produto;

public class DaoProduto {

	private Connection con;

	public DaoProduto() {
		con = ConexaoBD.getConexao();
	}

	public void Inserir(Produto prod) {

		try {
			String sql = "insert into produto(nomeProd,qtd,preco) values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, prod.getNomeProd());
			ps.setDouble(2, prod.getQtd());
			ps.setDouble(3, prod.getPreco());
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

	public List<Produto> listar() throws SQLException {
		String sql = "select * from produto";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Produto> list = new ArrayList<Produto>();

		while (rs.next()) {
			Produto prod = new Produto();
			prod.setId(rs.getLong("id"));
			prod.setNomeProd(rs.getString("nomeProd"));
			prod.setQtd(rs.getDouble("qtd"));
			prod.setPreco(rs.getDouble("preco"));
			list.add(prod);
		}
		return list;
	}

	public Produto consultarEditar(String id) throws SQLException {
		String sql = "select * from produto where id = " + id;

		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			Produto prod = new Produto();
			prod.setId(rs.getLong("id"));
			prod.setNomeProd(rs.getString("nomeProd"));
			prod.setQtd(rs.getDouble("qtd"));
			prod.setPreco(rs.getDouble("preco"));

			return prod;
		}
		return null;

	}

	public void editar(Produto prod) {
		try {
			String sql = "update produto set nomeProd = ?, qtd = ?, preco = ? where id = " + prod.getId();

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, prod.getNomeProd());
			ps.setDouble(2, prod.getQtd());
			ps.setDouble(3, prod.getPreco());
			ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public boolean validarUpdate(String nomeProd, String id) throws SQLException {
		String sql = "select count(1) as qtda from produto where nomeProd = '" + nomeProd + "' id != " + id;
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return rs.getInt("qtda") <= 0;
		}
		return false;
	}

	public boolean validarInserir(String id) throws SQLException {
		String sql = "select count(1) as qtda from produto where id = " + id;
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return rs.getInt("qtda") <= 0;
		}
		return false;
	}
	
	public void deletar(String id) throws SQLException {
		String sql = "delete from produto where id = " + id;
		PreparedStatement ps = con.prepareStatement(sql);
		ps.execute();
	}

}
