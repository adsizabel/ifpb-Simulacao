package br.edu.ifpb.sites.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.edu.ifpb.sites.jdbc.ConnectionFactory;
import br.edu.ifpb.sites.model.Alternativa;

public class AlternativaDao {

	private Connection conn;
	
	public AlternativaDao(){
		conn = new ConnectionFactory().getConnection();
	}
	
	public void addAlternativa(Alternativa nova){
	String sql ="INSERT INTO ALTERNATIVA (descricao, correta, codQuestao) VALUES (?,?,?)";
		
		try{
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, nova.getDescricao());
			pst.setBoolean(2, nova.getCorreta());
		
			pst.execute();
			pst.close();
			
		}catch (SQLException e){
			e.printStackTrace();
			
		}		
	}
	
	public void removeAlternativa(String id){
		String sql = "DELETE FROM ALTERNATIVA WHERE (id = ?)";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			
		} catch (SQLException e){
			throw new RuntimeException();
		}
	}
	
	public void updateAlternativa(Alternativa alternativa){
		String sql = "UPDATE INTO ALTERNATIVA id, descricao, correta ) VALUES (?,?,?)";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, alternativa.getDescricao());
			pst.setBoolean(2, alternativa.getCorreta());
			
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public Alternativa buscaAlternativa(int id) throws SQLException {
        
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM ALTERNATIVA WHERE ID = ?");
        pst.setInt(1, id);
        pst.execute();
        
        Alternativa alternativa = null;
        
        while(pst.getResultSet().next()) {
        	alternativa = new Alternativa();
        	alternativa.setId(id);
        	alternativa.setDescricao(pst.getResultSet().getString("descricao"));
        	alternativa.setCorreta(pst.getResultSet().getBoolean("correta"));
        	alternativa.setCodQuestao(pst.getResultSet().getInt("codquestao"));
        }
        
		return alternativa;
	
	}
	

}

