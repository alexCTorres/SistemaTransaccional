package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import java.sql.SQLException;

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

	public boolean registrarRegistro(Registro registro) throws Exception {
		try {
			daoRegistro.insertJPA(registro);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return true;
	}

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

	public boolean enviarCorreoIngresoCuenta(String correo) {
		return true;
	}

	public boolean bloquearCuenta(int intentos) {
		return true;
	}

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

	public boolean cambioContrasena(String correo) {
		return true;
	}

}
