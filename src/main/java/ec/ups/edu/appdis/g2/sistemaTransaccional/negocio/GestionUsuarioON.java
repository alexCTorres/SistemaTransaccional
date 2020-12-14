package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.appdis.g2.sistemaTransaccional.dao.UsuarioDAO;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Usuario;

@Stateless
public class GestionUsuarioON {

	@Inject
	private UsuarioDAO daoUsuario;
	
	public boolean registrarUsuario(Usuario usuario) {
		return true;
	}
	
	public boolean actualizarUsuario(Usuario usuario) {
		return true;
	}
	
	public Usuario buscarUsuario(int id) {
		Usuario usuario = new Usuario();
		try {
			usuario = daoUsuario.readJPA(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuario;
	}
	
	public boolean eliminarUsuario(int id) {
		return true;
	}
}
