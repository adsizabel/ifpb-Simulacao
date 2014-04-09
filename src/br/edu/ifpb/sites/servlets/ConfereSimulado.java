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

import br.edu.ifpb.sites.dao.AlternativaDao;
import br.edu.ifpb.sites.dao.SimuladoDao;
import br.edu.ifpb.sites.model.Alternativa;
import br.edu.ifpb.sites.model.Questao;
import br.edu.ifpb.sites.model.Simulado;

@WebServlet(name="ConfereSimulado", urlPatterns="/confereSimulado")
public class ConfereSimulado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConfereSimulado() {
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//Instanciar SimuladoDao
		SimuladoDao simuladodequestoes = new SimuladoDao();
		//Instanciar AlternativaDAO
		AlternativaDao alternativaDao = new AlternativaDao();
		//Recuperar o Simulado salvo na sessão
		Simulado simulado = (Simulado) request.getSession().getAttribute("simulado");
		//Lista de questões marcadas
		List<String> alternativasMarcadas = new ArrayList<>();
		//Percorrer lista de questões do simulado e comparar as certas e erradas
		for (Questao questao : simulado.getQuestoes()) {
			
			int idAlternativa = Integer.parseInt(request.getParameter("" + questao.getCodQuestao()));
			Alternativa alternativa = null;
			try {
				alternativa = alternativaDao.buscaAlternativa(idAlternativa);
				if (alternativa.getCorreta()) {
					simulado.setAcertos(simulado.getAcertos() + 1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			alternativasMarcadas.add("" + idAlternativa);
			
		}
		simulado.setPontos(simulado.getAcertos());
		simulado.setTempo(Integer.parseInt(request.getParameter("tempo")));
		request.setAttribute("simulado", simulado);
		request.setAttribute("alternativasMarcadas", alternativasMarcadas);
	
	}

}
