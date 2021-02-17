package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import java.sql.SQLException;
import java.util.List;
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

	public boolean actualizarUsuario(Usuario u) throws Exception {
		try {
			daoUsuario.updateJPA(u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return true;
	}
	
	public boolean actualizarUsuarioNomUser(String nomUser) throws Exception {
		try {
			daoUsuario.updateJPAUser(nomUser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return true;
	}

	public Usuario buscarUsuario(String nomUsuario) throws Exception {
		Usuario usuario = new Usuario();
		System.out.println("entra al metoso ON");
		try {
			usuario = daoUsuario.readJPA(nomUsuario);
			return usuario;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error" +e.getMessage());
			return null;
		}

	}

	public Usuario buscarUserNombre(String nomUsuario) throws Exception {
		Usuario usuario = new Usuario();
		try {
			usuario = daoUsuario.readJPA(nomUsuario);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error mensaje"+e.getMessage() +"  error sql: " +e.getErrorCode());
			throw new Exception("Error al buscar Usuario por nimbrer");
		}
		return usuario;
	}

	public boolean eliminarUsuario(String nomUsuario) throws Exception {
		try {
			Usuario r = daoUsuario.readJPA(nomUsuario);
			if (r == null) {
				System.out.println("Usuario no encontrado");
			} else {
				daoUsuario.deleteJPA(r.getNombreUsuario());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al eliminar");
		}
		return true;
	}
	
	public Usuario validarIngresoUsuario(String usuario, String contrasenia) {
		Usuario u = new Usuario();
		try {
			 u = daoUsuario.readJPA(usuario);
			 if(u.getContrasenia().equals(contrasenia)) {
				 return u;
			 }else {
				 return null;
			 }
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("usuario no encontrado negocio: " +e.getMessage());
			return null;
		}

	}
	
	public List<Usuario> getListUsuarios(){
		return daoUsuario.listaUsuarios();
	}
	

	public List<Usuario> getListUsuariosBloq(){
		return daoUsuario.listaUsuariosBloq();
	}
	
}
