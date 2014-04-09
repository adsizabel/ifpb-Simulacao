package br.edu.ifpb.sites.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.sites.dao.AlternativaDao;
import br.edu.ifpb.sites.dao.SimuladoDao;
import br.edu.ifpb.sites.model.Alternativa;
import br.edu.ifpb.sites.model.Simulado;
import br.edu.ifpb.sites.model.Usuario;

@WebServlet(name="TrataQuestionario", urlPatterns="/trataQuestionario")
public class TrataQuestionario extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final int PONTOS_POR_QUESTAO = 2;
       
    public TrataQuestionario() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<String> listaDosNomesDasQuestoes = new ArrayList<>();
		Enumeration<String> enumeration = request.getParameterNames();
		while(enumeration.hasMoreElements()){
			String nomeDoParametro = enumeration.nextElement();
			if (nomeDoParametro.startsWith("questao_")){
				listaDosNomesDasQuestoes.add(nomeDoParametro);
			}
		}
		
		
		AlternativaDao alternativaDao = new AlternativaDao();
		int qtdeAcertos = 0;
		for (String nomeDoParametro: listaDosNomesDasQuestoes){
			Integer idDaAlternativaMarcada = Integer.parseInt(request.getParameter(nomeDoParametro));
			try {
				Alternativa alternativaMarcada = alternativaDao.buscaAlternativa(idDaAlternativaMarcada);
				if (alternativaMarcada.getCorreta()){
					qtdeAcertos++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		String tempo = String.valueOf(request.getParameter("tempo"));
		
		Usuario usuarioLogado = (Usuario)request.getSession().getAttribute("usuarioLogado");
		
		Simulado simulado = new Simulado();
		simulado.setCodUsuario(usuarioLogado.getCodigo());
		simulado.setTempo(Integer.parseInt(tempo));
		simulado.setAcertos(qtdeAcertos);
		simulado.setPontos(qtdeAcertos * PONTOS_POR_QUESTAO );
		
		SimuladoDao simuladoDao = new SimuladoDao();
		simuladoDao.addSimulado(simulado);
		
		request.getSession().setAttribute("simulado", simulado);
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}

}
