package br.edu.ifpb.sites.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.sites.dao.QuestaoDao;
import br.edu.ifpb.sites.dao.SimuladoDao;
import br.edu.ifpb.sites.model.Questao;
import br.edu.ifpb.sites.model.Usuario;
import br.edu.ifpb.sites.model.Simulado;


@WebServlet(name="GeraSimulado", urlPatterns="/geraSimulado")
public class GeraSimulado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		//Recuperar usuário logado
    		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    		//Instanciar SimuladoDao
    		SimuladoDao simuladoDao = new SimuladoDao();
    		//Instanciar QuestaoDao
    		QuestaoDao questaodaocontroller = new QuestaoDao();
    		//Instanciar lista de questões
    		List<Questao> questoes = null;
    		//Gerar novo Simulado
    		Simulado simulado = new Simulado();
    		//Atribuir dono do Simulado
    		simulado.setCodUsuario(usuario.getCodigo());
    		
    		try {
    			//Recuperar assunto escolhido pelo usuário
    			String assunto = request.getParameter("assunto");
    			//Sortear questões do Simulado
    			if (assunto.equalsIgnoreCase("geral")) {
    				questoes = questaodaocontroller.geraQuestionarioGeral();
    			} else {
    				questoes = questaodaocontroller.geraQuestionarioPorAssunto(assunto);
    			}
    			//Adicionar questões ao Simulado
    			simulado.setQuestoes(questoes);
    			//Salvar Simulado
//    			simuladoDao.addSimulado(simulado);
    			//Adicionar Simulado a sessão
				request.getSession().setAttribute("simulado", simulado);
				//Encaminhar para a página de exibição do simulado
    			request.getRequestDispatcher("result.jsp").forward(request, response);
    			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	
}
