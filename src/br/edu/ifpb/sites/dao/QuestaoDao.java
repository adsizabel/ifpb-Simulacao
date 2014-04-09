package br.edu.ifpb.sites.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.sites.jdbc.ConnectionFactory;
import br.edu.ifpb.sites.model.Alternativa;
import br.edu.ifpb.sites.model.Questao;

public class QuestaoDao{
	private Connection conn;
	
	public QuestaoDao(){
		conn = new ConnectionFactory().getConnection();
	}
	
	public void addQuestao(Questao nova){
		
		//System.out.println("vai salvar");
		
		int codigo= 0;
		String sql = "INSERT INTO QUESTAO (assunto, enunciado, nomeConcurso, anoConcurso, dataquestao) VALUES(?,?,?,?,?)";
		
		try{
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, nova.getAssunto());
			pst.setString(2, nova.getEnunciado());
			pst.setString(3, nova.getNomeConcurso());
			pst.setString(4, nova.getAnoConcurso());
			pst.setDate(5, new Date(System.currentTimeMillis()));
			pst.execute();			
			
			Statement codigoStatement = conn.createStatement();
			ResultSet codigoResultSet = codigoStatement.executeQuery("SELECT MAX (codquestao) AS codigo FROM questao");
			while(codigoResultSet.next()){
				codigo = codigoStatement.getResultSet().getInt("codigo");
				
			}
			
			inserirAlternativas(codigo, nova.getAlternativas());			
		
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void inserirAlternativas(int codQuestao, List<Alternativa> alternativas){
		String sql = "INSERT INTO ALTERNATIVA (descricao, correta, codQuestao) VALUES (?,?,?)";
		
		try{
			PreparedStatement pst = conn.prepareStatement(sql);
			
			for (Alternativa alternativa : alternativas) {
			  pst.setString(1, alternativa.getDescricao());
			  pst.setBoolean(2, alternativa.getCorreta());
			  pst.setInt(3, codQuestao);
			  pst.execute();
			  System.out.println("salvando alternativa");
			}			
			pst.close();
		
		}catch (SQLException e){
				e.printStackTrace();
		}
	}	
	
	public void removeQuestao(int codQuestao){
		String sql = "DELETE FROM QUESTAO WHERE (codigoQuestao = ?)";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, codQuestao);
			
		} catch (SQLException e){
			throw new RuntimeException();
		}
	}
	
	public void updateQuestao(Questao questao){
		String sql = "UPDATE INTO QUESTAO (assunto, enunciado, nomeConcurso, anoConcurso, dataquestao) VALUES(?,?,?,?,?)";
		
		try{
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, questao.getCodQuestao());
			pst.setString(2, questao.getAssunto());
			pst.setString(3, questao.getEnunciado());
			pst.setString(4, questao.getAnoConcurso());
			pst.setDate(5, new Date(System.currentTimeMillis()));
			pst.execute();			
			
		    
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	public Questao buscaQuestao(int codQuestao) throws SQLException {
        
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM QUESTAO WHERE CODQUESTAO = ?");
        pst.setInt(1, codQuestao);
        pst.execute();
        
        Questao questao = null;
        
        while(pst.getResultSet().next()) {
        	questao = new Questao();
        	questao.setCodQuestao(pst.getResultSet().getInt("Código: "));
        	questao.setAssunto(pst.getResultSet().getString("Assunto: "));
        	questao.setEnunciado(pst.getResultSet().getString("Enunciado: "));
        	questao.setNomeConcurso(pst.getResultSet().getString("Concurso: "));
        	questao.setAnoConcurso(pst.getResultSet().getString("Ano do Concurso: "));
        	questao.setDataCadastro(pst.getResultSet().getDate("Data: "));
        }
        
		return questao;	
	}
	
	public List<Alternativa> recuperaAlternativa(int codQuestao) throws SQLException{
		ArrayList<Alternativa> alternativas = new ArrayList<>();
		String sql = "select * from alternativa where codQuestao = ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		
		pst.setInt(1, codQuestao);
		pst.execute();
		
		while(pst.getResultSet().next()) {
        	Alternativa alternativa = new Alternativa();
        	alternativa.setId(pst.getResultSet().getInt("id"));
        	alternativa.setDescricao(pst.getResultSet().getString("descricao"));
        	alternativa.setCorreta(pst.getResultSet().getBoolean("correta"));
        	alternativa.setCodQuestao(pst.getResultSet().getInt("codQuestao"));
        	
        	alternativas.add(alternativa);
        }
		return alternativas;
	}
	 	
	public List<Questao> geraQuestionarioGeral() throws SQLException{
		ArrayList<Questao> questoes = new ArrayList<>();
		String sql = "select * from questao order by random() limit 3";
		PreparedStatement pst = conn.prepareStatement(sql);
		try {
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
				Questao questao = new Questao();
				questao.setCodQuestao(rs.getInt("codquestao"));
				questao.setNomeConcurso(rs.getString("nomeconcurso"));
				questao.setAnoConcurso(rs.getString("anoconcurso"));
				questao.setAssunto(rs.getString("assunto"));
				questao.setEnunciado(rs.getString("enunciado"));
				questao.setCodQuestao(rs.getInt("codQuestao"));				
				questao.setDataCadastro(rs.getDate("dataquestao"));				

				List<Alternativa> alternativas = recuperaAlternativa(rs.getInt("codquestao"));
								
				questao.setAlternativas(alternativas);
				
				questoes.add(questao);
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		return questoes;
		
	}
	
	public List<Questao> geraQuestionarioPorAssunto(String assunto) throws SQLException{
		ArrayList<Questao> questoes = new ArrayList<>();
		String sql = "select * from questao where assunto = ? order by random() limit 3";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, assunto);
		try {
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
				Questao questao = new Questao();
				questao.setCodQuestao(rs.getInt("codquestao"));
				questao.setNomeConcurso(rs.getString("nomeconcurso"));
				questao.setAnoConcurso(rs.getString("anoconcurso"));
				questao.setAssunto(rs.getString("assuntoconcurso"));
				questao.setEnunciado(rs.getString("enunciado"));
				questao.setCodQuestao(rs.getInt("codQuestao"));				
				questao.setDataCadastro(rs.getDate("dataquestao"));				

				List<Alternativa> alternativas = recuperaAlternativa(rs.getInt("codQuestao"));
								
				questao.setAlternativas(alternativas);
				
				questoes.add(questao);
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		return questoes;
		
	}
	
	public List<Questao> listarQuestoes() throws SQLException{
		ArrayList<Questao> questoes = new ArrayList<>();
		String sql = "select * from questao";
		PreparedStatement pst = conn.prepareStatement(sql);
		
		pst.execute();
		while(pst.getResultSet().next()){
			Questao questao = new Questao();
			questao.setCodQuestao(pst.getResultSet().getInt("codquestao"));
			questao.setNomeConcurso(pst.getResultSet().getString("nomeconcurso"));
			questao.setAnoConcurso(pst.getResultSet().getString("anoconcurso"));
			questao.setAssunto(pst.getResultSet().getString("assunto"));
			questao.setEnunciado(pst.getResultSet().getString("enunciado"));
			questao.setCodQuestao(pst.getResultSet().getInt("codquestao"));
			questao.setDataCadastro(pst.getResultSet().getDate("dataquestao"));
			questoes.add(questao);
		}
		
		return questoes;
	}
	
	public List<String> recuperaAssuntos() throws SQLException{
		ArrayList<String> assuntos = new ArrayList<>();
		String sql = "select distinct assunto from questao";
		PreparedStatement pst = conn.prepareStatement(sql);
		
		
		pst.execute();
		
		while(pst.getResultSet().next()) {        	
        	assuntos.add(pst.getResultSet().getString("assunto"));
        }
		return assuntos;
	}
	
	
}
	
	


