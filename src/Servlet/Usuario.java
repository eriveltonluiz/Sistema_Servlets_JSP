package Servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import DAO.DaoUsuario;
import beans.BeanCursoJsp;

@WebServlet("/Usuario")
@MultipartConfig
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario dao = new DaoUsuario();

	public Usuario() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			System.out.println(user);
			System.out.println(acao);

			if (acao.equalsIgnoreCase("delete")) {
				dao.delete(user);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastrarUsuario.jsp");
				request.setAttribute("usuarios", dao.listar());
				dispatcher.forward(request, response);
			}

			else if (acao.equalsIgnoreCase("editar")) {
				BeanCursoJsp bean = dao.consultar(user);
				System.out.println("-----");
				System.out.println(bean.getId());
				System.out.println(bean.getLogin());
				System.out.println(bean.getSenha());
				System.out.println(bean.getTelefone());

				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastrarUsuario.jsp");
				request.setAttribute("user", bean);
				dispatcher.forward(request, response);
			} else if (acao.equalsIgnoreCase("listar")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastrarUsuario.jsp");
				request.setAttribute("usuarios", dao.listar());
				dispatcher.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("cancelar")) {
			try {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastrarUsuario.jsp");
				request.setAttribute("usuarios", dao.listar());
				dispatcher.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String telefone = request.getParameter("telefone");
			String cep = request.getParameter("cep");
			String rua = request.getParameter("rua");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String estado = request.getParameter("estado");
			String ibge = request.getParameter("ibge");
			
			BeanCursoJsp bean = new BeanCursoJsp();
			bean.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			bean.setLogin(login);
			bean.setSenha(senha);
			bean.setNome(nome);
			bean.setTelefone(telefone);
			bean.setCep(cep);
			bean.setRua(rua);
			bean.setBairro(bairro);
			bean.setCidade(cidade);
			bean.setEstado(estado);
			bean.setIbge(ibge);

			try {
				/*Inicio File upload de imagens*/
					/*List<FileItem> fileItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
					
					for (FileItem fileItem : fileItems) {
						if(fileItem.getFieldName().equals("foto")) {
							String fotoBase64 = new Base64().encodeBase64String(fileItem.get());
							String contentType = fileItem.getContentType();
							bean.setFotoBase64(fotoBase64);
							bean.setContentType(contentType);
						}
					} Usar apenas quando inserir no banco imagens*/
					
				if(ServletFileUpload.isMultipartContent(request)) {
					
					Part imagemFoto = request.getPart("foto");
					new Base64();
					String fotoBase64 = Base64.encodeBase64String(convertStreamForByte(imagemFoto.getInputStream()));
					bean.setFotoBase64(fotoBase64);
					bean.setContentType(imagemFoto.getContentType());
				}
				
				boolean podeInserir = true;

				if (login == null || login.isEmpty()) {
					request.setAttribute("msg", "Login deve ser informado!");
					podeInserir = false;
				} else if (senha == null || senha.isEmpty()) {
					request.setAttribute("msg", "Senha deve ser informado!");
					podeInserir = false;
				} else if (nome == null || nome.isEmpty()) {
					request.setAttribute("msg", "Nome deve ser informado!");
					podeInserir = false;
				} else if (telefone == null || senha.isEmpty()) {
					request.setAttribute("msg", "Telefone deve ser informado!");
					podeInserir = false;
				}

				else if (id == null || id.isEmpty() && !dao.validarLogin(login)) {
					request.setAttribute("msg", "Usuário com esse nome já existe!");
					podeInserir = false;
				}

				else if (id == null || id.isEmpty() && dao.validarLogin(login)) {
					dao.inserir(bean);
				} else if (id != null && !id.isEmpty()) {
					if (!dao.validarLoginUpdate(login, id)) {
						request.setAttribute("msg", "Usuário com esse nome já existe!");
						podeInserir = false;
					} else {
						dao.editar(bean);
					}
				}

				if (!podeInserir) {
					request.setAttribute("user", bean);
				}

				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastrarUsuario.jsp");
				request.setAttribute("usuarios", dao.listar());
				dispatcher.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
	
	// converte o fluxo de entrada de imagens para um array de byte
	private byte[] convertStreamForByte(InputStream imagem) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = imagem.read();
		while(reads != -1) {
			baos.write(reads);
			reads = imagem.read();
		}
		return baos.toByteArray();
	}
}
