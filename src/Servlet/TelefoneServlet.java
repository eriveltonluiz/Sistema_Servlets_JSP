package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DaoTelefone;
import DAO.DaoUsuario;
import beans.BeanCursoJsp;
import beans.Telefone;

@WebServlet("/salvarTelefone")
public class TelefoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario dao = new DaoUsuario();
	private DaoTelefone daot = new DaoTelefone();

	public TelefoneServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");
			if (acao.endsWith("addfone")) {

				String user = request.getParameter("user");
				BeanCursoJsp usuario = dao.consultar(user);

				request.getSession().setAttribute("userEscolhido", usuario);
				request.setAttribute("userEscolhido", usuario);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/telefones.jsp");
				request.setAttribute("telefones", daot.listar(usuario.getId()));
				dispatcher.forward(request, response);
			} else if (acao.endsWith("deletar")) {
				String foneId = request.getParameter("foneId");
				daot.deletar(foneId);

				BeanCursoJsp bean = (BeanCursoJsp) request.getSession().getAttribute("userEscolhido");

				RequestDispatcher dispatcher = request.getRequestDispatcher("/telefones.jsp");
				request.setAttribute("telefones", daot.listar(bean.getId()));
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			BeanCursoJsp bean = (BeanCursoJsp) request.getSession().getAttribute("userEscolhido");
			String numero = request.getParameter("numero");
			String tipo = request.getParameter("tipo");

			Telefone tel = new Telefone();
			tel.setNumero(numero);
			tel.setTipo(tipo);
			tel.setUsuario(bean.getId());

			daot.Inserir(tel);

			request.getSession().setAttribute("userEscolhido", bean);
			request.setAttribute("userEscolhido", bean);

			RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
			request.setAttribute("telefones", daot.listar(bean.getId()));
			request.setAttribute("msg", "Salvo com sucesso");
			view.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
