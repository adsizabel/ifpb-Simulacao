package br.edu.ifpb.sites.model;

public class Usuario {
	private String nome, login, senha;
	private int codigo;
	private static final String USUARIOCOMUM = "usuarioComum", ADMINISTRADOR = "administrador";
	private String tipo;
	
	public Usuario(){
	}
		
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getCodigo() {
		return codigo;
	}

	public String getUSUARIOCOMUM() {
		return USUARIOCOMUM;
	}

	public String getADMINISTRADOR() {
		return ADMINISTRADOR;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", login=" + login + ", senha="
				+ senha + ", cod=" + codigo + "]";
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
	

}
