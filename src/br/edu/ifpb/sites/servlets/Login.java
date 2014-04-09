package br.edu.ifpb.sites.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.sites.dao.QuestaoDao;
import br.edu.ifpb.sites.dao.UsuarioDao;
import br.edu.ifpb.sites.model.Usuario;


@WebServlet(name="Login", urlPatterns="/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	  //PrintWriter out = response.getWriter();

		String login = request.getParameter("login");
		String senha = request.getParameter("senha");

		UsuarioDao usuarioDao = new UsuarioDao();

		Usuario usuarioAutenticado = usuarioDao.recuperaUsuario(login, senha);

		String error = "";
		String paginaDeRedirecionamento = "login.jsp";

		if (usuarioAutenticado != null) {
			
			request.getSession().setMaxInactiveInterval(30);
			request.getSession().setAttribute("usuarioLogado", usuarioAutenticado);
			
			if (usuarioAutenticado.getTipo().equals("comum")) {
				QuestaoDao questaoDao = new QuestaoDao();
				try {
					request.getSession().setAttribute("assuntos", questaoDao.recuperaAssuntos());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				paginaDeRedirecionamento = "index.jsp";
			} else {
				paginaDeRedirecionamento = "usuarioadmin.jsp";
			}
				
		} else {
			error = "Usuário ou senha inválidos.";
			request.setAttribute("error", error);
		}

		request.getRequestDispatcher(paginaDeRedirecionamento).include(request, response);

	}

}
