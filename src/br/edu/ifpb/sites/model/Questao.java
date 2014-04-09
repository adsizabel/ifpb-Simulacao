package br.edu.ifpb.sites.model;

import java.sql.Date;
import java.util.List;

public class Questao {
	private String assunto, enunciado, nomeConcurso, anoConcurso;
	private int codQuestao;
	private List<Alternativa> alternativas;
	private Date dataQuestao;
	
	public Questao() {
	}
	
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	public int getCodQuestao() {
		return codQuestao;
	}
	public void setCodQuestao(int codQuestao) {
		this.codQuestao = codQuestao;
	}

	@Override
	public String toString() {
		return "Questao [assunto=" + assunto + ", enunciado=" + enunciado
				+ ", codQuestao=" + codQuestao + "]";
	}

	public List<Alternativa> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(List<Alternativa> alternativas) {
		this.alternativas = alternativas;
	}

	public String getNomeConcurso() {
		return nomeConcurso;
	}

	public void setNomeConcurso(String nomeConcurso) {
		this.nomeConcurso = nomeConcurso;
	}

	public Date getdataQuestao() {
		return dataQuestao;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataQuestao = dataCadastro;
	}

	public String getAnoConcurso() {
		return anoConcurso;
	}

	public void setAnoConcurso(String anoConcurso) {
		this.anoConcurso = anoConcurso;
	}
	
	
	
}
