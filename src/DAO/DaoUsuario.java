package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConexaoBanco.ConexaoBD;
import beans.BeanCursoJsp;

public class DaoUsuario {
	private Connection con;

	public DaoUsuario() {
		con = ConexaoBD.getConexao();
	}

	public void inserir(BeanCursoJsp bean) {

		try {
			String sql = "insert into usuario(login,senha,nome,telefone,cep,rua,bairro,cidade,estado,ibge,fotoBase64,contentType) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, bean.getLogin());
			ps.setString(2, bean.getSenha());
			ps.setString(3, bean.getNome());
			ps.setString(4, bean.getTelefone());
			ps.setString(5, bean.getCep());
			ps.setString(6, bean.getRua());
			ps.setString(7, bean.getBairro());
			ps.setString(8, bean.getCidade());
			ps.setString(9, bean.getEstado());
			ps.setString(10, bean.getIbge());
			ps.setString(11, bean.getFotoBase64());
			ps.setString(12, bean.getContentType());

			System.out.println(bean.getFotoBase64());
			System.out.println(bean.getContentType());

			ps.execute();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public List<BeanCursoJsp> listar() throws Exception {
		String sql = "select * from usuario";

		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		List<BeanCursoJsp> list = new ArrayList<BeanCursoJsp>();

		while (rs.next()) {
			BeanCursoJsp bean = new BeanCursoJsp();
			bean.setId(rs.getLong("id"));
			bean.setLogin(rs.getString("login"));
			bean.setSenha(rs.getString("senha"));
			bean.setNome(rs.getString("nome"));
			bean.setTelefone(rs.getString("telefone"));
			bean.setCep(rs.getString("cep"));
			bean.setRua(rs.getString("rua"));
			bean.setBairro(rs.getString("bairro"));
			bean.setCidade(rs.getString("cidade"));
			bean.setEstado(rs.getString("estado"));
			bean.setIbge(rs.getString("ibge"));
			bean.setFotoBase64(rs.getString("fotoBase64"));
			bean.setContentType(rs.getString("contentType"));
			list.add(bean);
		}
		return list;
	}

	public void delete(String id) {
		try {
			String sql = "delete from usuario where id = '" + id + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.execute();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public BeanCursoJsp consultar(String id) throws Exception {
		String sql = "select * from usuario where id = '" + id + "'";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			BeanCursoJsp bean = new BeanCursoJsp();
			bean.setId(rs.getLong("id"));
			bean.setLogin(rs.getString("login"));
			bean.setSenha(rs.getString("senha"));
			bean.setNome(rs.getString("nome"));
			bean.setTelefone(rs.getString("telefone"));
			bean.setCep(rs.getString("cep"));
			bean.setRua(rs.getString("rua"));
			bean.setBairro(rs.getString("bairro"));
			bean.setCidade(rs.getString("cidade"));
			bean.setEstado(rs.getString("estado"));
			bean.setIbge(rs.getString("ibge"));

			return bean;
		}
		return null;
	}

	public boolean validarLogin(String login) throws Exception {
		String sql = "select count(1) as qtd from usuario where login = '" + login + "'";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return rs.getInt("qtd") <= 0; // return true
		}
		return false;
	}

	public boolean validarLoginUpdate(String login, String id) throws Exception {
		String sql = "select count(1) as qtd from usuario where login = '" + login + "' and id != " + id;
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return rs.getInt("qtd") <= 0; // return true
		}
		return false;
	}

	public void editar(BeanCursoJsp bean) {
		try {
			String sql = "update usuario set login = ?, senha = ?, nome = ?, telefone = ?, cep = ?, rua = ?, bairro = ?,"
					+ "cidade = ?, estado = ?, ibge = ?, fotoBase64 = ?, contentType = ?" + " where id = " + bean.getId();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, bean.getLogin());
			ps.setString(2, bean.getSenha());
			ps.setString(3, bean.getNome());
			ps.setString(4, bean.getTelefone());
			ps.setString(5, bean.getCep());
			ps.setString(6, bean.getRua());
			ps.setString(7, bean.getBairro());
			ps.setString(8, bean.getCidade());
			ps.setString(9, bean.getEstado());
			ps.setString(10, bean.getIbge());
			ps.setString(11, bean.getFotoBase64());
			ps.setString(12, bean.getContentType());

			ps.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
