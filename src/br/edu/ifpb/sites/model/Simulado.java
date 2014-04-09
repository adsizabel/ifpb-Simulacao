package br.edu.ifpb.sites.model;

import java.util.List;



public class Simulado {
	private int pontos, codSimulado, codUsuario, tempo, acertos;
	private List<Questao> questoes;
	
	
	public Simulado(){
		
	}

	public int getPontos() {
		return pontos;
	}

	public int getCodSimulado() {
		return codSimulado;
	}

	public int getTempo() {
		return tempo;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public void setCodSimulado(int idSimulado) {
		this.codSimulado = idSimulado;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	public int getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(int idUsuario) {
		this.codUsuario = idUsuario;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public int getAcertos() {
		return acertos;
	}

	public void setAcertos(int acertos) {
		this.acertos = acertos;
	}

	@Override
	public String toString() {
		return "Simulado [pontos=" + pontos + ", codSimulado=" + codSimulado
				+ ", codUsuario=" + codUsuario + ", tempo=" + tempo
				+ ", acertos=" + acertos + ", questoes=" + questoes + "]";
	}
	
	
}
