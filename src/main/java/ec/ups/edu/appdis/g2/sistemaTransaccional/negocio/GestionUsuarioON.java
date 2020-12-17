package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.appdis.g2.sistemaTransaccional.dao.UsuarioDAO;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Usuario;

@Stateless
public class GestionUsuarioON  {

	@Inject
	private UsuarioDAO daoUsuario;

	public boolean registrarUsuario(Usuario usuario) throws Exception {
		try {
			daoUsuario.insertJPA(usuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return true;
	}

	public boolean actualizarUsuario(Usuario usuario) throws Exception {
		try {
			daoUsuario.updateJPA(usuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return true;
	}

	public Usuario buscarUsuario(int id) throws Exception {
		Usuario usuario = new Usuario();
		try {
			usuario = daoUsuario.readJPA(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return usuario;
	}

	public boolean eliminarUsuario(int id) throws Exception {
		try {
			Usuario r = daoUsuario.readJPA(id);
			if (r == null) {
				System.out.println("Usuario no encontrado");
			} else {
				daoUsuario.deleteJPA(r.getId());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al eliminar");
		}
		return true;
	}
}
