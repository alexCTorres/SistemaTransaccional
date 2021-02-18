package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.appdis.g2.sistemaTransaccional.dao.RegistroDAO;
import ec.ups.edu.appdis.g2.sistemaTransaccional.dao.UsuarioDAO;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Registro;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Usuario;

@Stateless
public class GestionRegistroON  {

	@Inject
	private RegistroDAO daoRegistro;

	@Inject
	private UsuarioDAO daoUsuario;

	/**
	 * 
	 * @param registro
	 * @return
	 * @throws Exception
	 */
	public boolean registrarRegistro(Registro registro) throws Exception {
		try {
			daoRegistro.insertJPA(registro);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Error al registrar" +e.getMessage());
		}
		return true;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Registro buscarRegistro(int id) throws Exception {
		Registro r = new Registro();
		try {
			r = daoRegistro.readJPA(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return r;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean eliminarRegistro(int id) throws Exception {
		try {
			Registro r = daoRegistro.readJPA(id);
			if (r == null) {
				System.out.println("Registro no encontrado");
			} else {
				daoRegistro.deleteJPA(r.getId());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return true;
	}

	/**
	 * 
	 * @param usuario
	 * @return
	 * @throws Exception
	 */
	public Usuario buscarUsuarioNombre(String usuario) throws Exception {
		Usuario u = new Usuario();
		System.out.println("entra al dao");
		u = daoUsuario.buscarPorUsuarioJPA(usuario);
		System.out.println("sale del dao");
		if (u==null) {
			System.out.println("usuario no encontrado");
			return null;
		} else {
			return u;
		}
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	public List<Registro> listarRegistros(String user){
		return daoRegistro.listaRegistros(user);
	}

}
