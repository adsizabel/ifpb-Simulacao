package br.edu.ifpb.sites.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.ifpb.sites.jdbc.ConnectionFactory;

public class LoginDao {
	
	Connection conn;
	
	public LoginDao(){
		conn = new ConnectionFactory().getConnection();
	}
	
	public boolean autentica(String login, String senha) throws SQLException{
		String sql = "select * from usuario where login=? and senha = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, login);
		pstm.setString(2, senha);
		ResultSet rs = pstm.executeQuery();
		
		return rs.next();
	}

}
