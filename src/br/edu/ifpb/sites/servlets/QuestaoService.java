package br.edu.ifpb.sites.servlets;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.edu.ifpb.sites.dao.QuestaoDao;
import br.edu.ifpb.sites.model.Alternativa;
import br.edu.ifpb.sites.model.Questao;

public class QuestaoService {

	private QuestaoDao questaoDao;
	
	public QuestaoService(QuestaoDao questaoDao) {
		this.questaoDao = questaoDao;
	}
	
	public void cadastraQuestao(HttpServletRequest request){
		String nomeConcurso = request.getParameter("nomeConcurso");
		String data = request.getParameter("ano");
		String assunto = request.getParameter("assunto");
		String enunciado = request.getParameter("enunciado");
		String alternativaCorreta = request.getParameter("alternativaCorreta");

		Questao questao = new Questao();
		questao.setNomeConcurso(nomeConcurso);
		questao.setAnoConcurso(data);
		questao.setAssunto(assunto);
		questao.setEnunciado(enunciado);

		String descricaoAlternativa1 = request.getParameter("alternativa1");
		String descricaoAlternativa2 = request.getParameter("alternativa2");
		String descricaoAlternativa3 = request.getParameter("alternativa3");
		String descricaoAlternativa4 = request.getParameter("alternativa4");
		String descricaoAlternativa5 = request.getParameter("alternativa5");

		List<Alternativa> listaDeAlternativas = new ArrayList<>();
		
		Alternativa alternativa1 = new Alternativa();		
		alternativa1.setDescricao(descricaoAlternativa1);

		Alternativa alternativa2 = new Alternativa();
		alternativa2.setDescricao(descricaoAlternativa2);

		Alternativa alternativa3 = new Alternativa();
		alternativa3.setDescricao(descricaoAlternativa3);

		Alternativa alternativa4 = new Alternativa();
		alternativa4.setDescricao(descricaoAlternativa4);

		Alternativa alternativa5 = new Alternativa();
		alternativa5.setDescricao(descricaoAlternativa5);

		switch (alternativaCorreta) {
		case "a":
			alternativa1.setCorreta(true);			
			break;
		case "b":
			alternativa2.setCorreta(true);			
			break;
		case "c":
			alternativa3.setCorreta(true);			
			break;
		case "d":
			alternativa4.setCorreta(true);			
			break;
		case "e":
			alternativa1.setCorreta(true);			
			break;			
		default:
			break;
		}		
		
		listaDeAlternativas.add(alternativa1);
		listaDeAlternativas.add(alternativa2);
		listaDeAlternativas.add(alternativa3);
		listaDeAlternativas.add(alternativa4);
		listaDeAlternativas.add(alternativa5);

		questao.setAlternativas(listaDeAlternativas);
		
		questaoDao.addQuestao(questao);
	}
	
}
