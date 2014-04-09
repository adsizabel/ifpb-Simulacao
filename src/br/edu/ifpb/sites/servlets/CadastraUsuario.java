package br.edu.ifpb.sites.servlets;

import java.io.IOException;
	
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.sites.dao.UsuarioDao;
import br.edu.ifpb.sites.model.Usuario;

@WebServlet(name="CadastraUsuario", urlPatterns="/cadastraUsuario")
public class CadastraUsuario extends HttpServlet{	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doPost(request, response);		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//PrintWriter out = response.getWriter();
	     String nome= request.getParameter("nome");
	     String login= request.getParameter("login");
	     String senha= request.getParameter("senha");
	     String tipo = "comum";
	 
	     Usuario usuario = new Usuario();
	     usuario.setNome(nome);
	     usuario.setLogin(login);
	     usuario.setSenha(senha);
	     usuario.setTipo(tipo);
	   
	     UsuarioDao usuarioDao = new UsuarioDao();
	     usuarioDao.addUsuario(usuario);
	}
	
	
	
	
	

}
