package Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DaoProduto;
import beans.Produto;

@WebServlet("/salvarProduto")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoProduto dao = new DaoProduto();

	public ProdutoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");
			String produto = request.getParameter("produto");
			System.out.println(acao);
			System.out.println(produto);

			if (acao.equalsIgnoreCase("listar")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastro-produto.jsp");
				request.setAttribute("produtos", dao.listar());
				dispatcher.forward(request, response);
			}

			else if (acao.equalsIgnoreCase("editar")) {
				Produto prod = dao.consultarEditar(produto);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastro-produto.jsp");
				request.setAttribute("produto", prod);
				dispatcher.forward(request, response);
			}
			else if(acao.equalsIgnoreCase("deletar")) {
				dao.deletar(produto);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastro-produto.jsp");
				request.setAttribute("produtos", dao.listar());
				dispatcher.forward(request, response);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			boolean podeInserir = true;
			String id = request.getParameter("id");
			String nomeProd = request.getParameter("nomeProd");
			String qtd = request.getParameter("qtd");
			String preco = request.getParameter("preco");

			Produto prod = new Produto();
			prod.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
			prod.setNomeProd(nomeProd);
			prod.setQtd(Double.parseDouble(qtd));
			prod.setPreco(Double.parseDouble(preco));

			if (id == null || id.isEmpty() && dao.validarInserir(id)) {
				dao.Inserir(prod);
			}else if (id != null || !id.isEmpty()) {
				if(dao.validarUpdate(nomeProd,id)) {
					dao.editar(prod);
				}else {
					request.setAttribute("msg", "Campo nome já existente");
					podeInserir = false;
				}
			}
			
			if (!podeInserir) {
				request.setAttribute("produto", prod);
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastro-produto.jsp");
			request.setAttribute("produtos", dao.listar());
			dispatcher.forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
