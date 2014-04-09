package br.edu.ifpb.sites.dao;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.ifpb.sites.jdbc.ConnectionFactory;
import br.edu.ifpb.sites.model.Usuario;

public class UsuarioDao {
	private Connection conn;
	
	public UsuarioDao(){
		conn = new ConnectionFactory().getConnection();
	}
	
	public void addUsuario(Usuario novo){
		String sql = "INSERT INTO USUARIO (nome, login, senha, tipo) VALUES (?,?,?,?)";
		
		try{
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, novo.getNome());
			pst.setString(2, novo.getLogin());
			pst.setString(3, novo.getSenha());			
			pst.setString(4, novo.getTipo());			
			
			pst.execute();			
			pst.close();
			System.out.println("Teste");
		}catch (SQLException e){
			e.printStackTrace();;
		}		
	}
	
	public void removeUsuario(int codigo){
		String sql = "DELETE FROM USUARIO WHERE (codUsuario = ?)";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, codigo);
			pst.execute();
			
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void updateUsuario(Usuario usuario){
		String sql = "UPDATE USUARIO SET nome=?, login=?, senha=?, tipo=?  where codUsuario=?";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getLogin());
			pst.setString(3, usuario.getSenha());
			pst.setString(4, usuario.getTipo());
			pst.setInt(5, usuario.getCodigo());
			
			pst.execute();	
		    
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public Usuario resourceUsuario(int codigo) throws SQLException {
        
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM USUARIO WHERE CODUSUARIO = ?");
        pst.setInt(1, codigo);
        pst.execute();
        
        Usuario usuario = null;
        
        while(pst.getResultSet().next()) {
        	usuario = new Usuario();
        	usuario.setNome(pst.getResultSet().getString("nome"));
        	usuario.setLogin(pst.getResultSet().getString("login"));
        	usuario.setSenha(pst.getResultSet().getString("senha"));        	
        	usuario.setCodigo(pst.getResultSet().getInt("codUsuario"));
        	usuario.setTipo(pst.getResultSet().getString("tipo"));
        	
        }
        
		return usuario;
	
	}
	
	public Usuario recuperaUsuario(String login, String senha) {

		Usuario usuario = null;

		PreparedStatement pst;
		try {

			pst = conn
					.prepareStatement("SELECT * FROM USUARIO WHERE LOGIN = ? AND SENHA = ?");
			pst.setString(1, login);
			pst.setString(2, senha);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				usuario = new Usuario();
				usuario.setCodigo(rs.getInt("codusuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setTipo(rs.getString("tipo"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;

	}


}
