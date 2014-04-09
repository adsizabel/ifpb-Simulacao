package br.edu.ifpb.sites.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.sites.model.Questao;
import br.edu.ifpb.sites.dao.QuestaoDao;


@WebServlet(name="ListarQuestoes", urlPatterns="/listarQuestoes")
public class ListarQuestoes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			QuestaoDao questaoDao = new QuestaoDao();
			List<Questao> questoes = new ArrayList<>();			
			questoes = questaoDao.listarQuestoes();
			//request.setAttribute("questoes", questoes);
			request.getSession().setAttribute("questoes", questoes);
			request.getRequestDispatcher("/editarquestoes.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	
	}

}
