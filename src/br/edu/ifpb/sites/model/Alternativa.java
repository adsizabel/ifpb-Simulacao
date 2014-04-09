package br.edu.ifpb.sites.model;

public class Alternativa {
	private int id, codQuestao;
	private String descricao;
	private boolean correta;
	
	public Alternativa() {
		
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean getCorreta() {
		return correta;
	}	

	public void setCorreta(boolean correta) {
		this.correta = correta;
	}

	@Override
	public String toString() {
		return "Alternativa [descricao= " + descricao
				+ ", correta=" + correta + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodQuestao() {
		return codQuestao;
	}

	public void setCodQuestao(int codQuestao) {
		this.codQuestao = codQuestao;
	}
	
	
	

}
