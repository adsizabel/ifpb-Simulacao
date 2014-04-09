package br.edu.ifpb.sites.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.edu.ifpb.sites.jdbc.ConnectionFactory;
import br.edu.ifpb.sites.model.Simulado;

public class SimuladoDao {

	private Connection conn;
	
	public SimuladoDao(){
		conn = new ConnectionFactory().getConnection();
	}
	
	public Simulado addSimulado(Simulado novo){
		String sql = "INSERT INTO SIMULADO (codusuario, pontos, tempo, acertos) VALUES (?,?,?,?)";
		
		try{
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, novo.getCodUsuario());
			pst.setInt(2, novo.getPontos());
			pst.setInt(3, novo.getTempo());
			pst.setInt(4, novo.getAcertos());
			pst.executeUpdate();
			pst.close();
			
		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}		
		
		return novo;
		
	}
	
	public void removeSimulado(String idSimulado){
		String sql = "DELETE FROM SIMULADO WHERE (codsimulado = ?)";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, idSimulado);
			
		} catch (SQLException e){
			throw new RuntimeException();
		}
	}
	
	public void updateSimulado(Simulado simulado){
		String sql = "UPDATE SIMULADO SET codusuario = ?, pontos = ?, tempo = ?, acertos = ? WHERE CODSIMULADO = ?";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, simulado.getCodUsuario());
			pst.setInt(2, simulado.getPontos());
			pst.setInt(3, simulado.getTempo());
			pst.setInt(4, simulado.getAcertos());
			pst.setInt(5, simulado.getCodSimulado());
			
			pst.execute();		
			pst.close();
			
		} catch (SQLException e){
			e.printStackTrace();;
		}
	}
	
	public Simulado resourceSimulado(int idSimulado) throws SQLException {
        
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM SIMULADO WHERE CODSIMULADO = ?");
        pst.setInt(1, idSimulado);
        pst.execute();
        
        Simulado simulado = null;
        
        while(pst.getResultSet().next()) {
        	
        	simulado = new Simulado();
        	
        	simulado.setPontos(pst.getResultSet().getInt("Pontos: "));
        	simulado.setCodSimulado(pst.getResultSet().getInt("Descrição: "));
        	simulado.setCodUsuario(pst.getResultSet().getInt("Descrição: "));
        	simulado.setTempo(pst.getResultSet().getInt("Tempo: "));  	
        }
        
		return simulado;
	
	}
}

