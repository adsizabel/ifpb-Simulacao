package br.edu.ifpb.sites.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection getConnection(){
		Connection con = null;
		try{
			Class.forName("org.postgresql.Driver");
			con =  DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/simulado", "postgres","izabel");
		}catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;	
	}
}
