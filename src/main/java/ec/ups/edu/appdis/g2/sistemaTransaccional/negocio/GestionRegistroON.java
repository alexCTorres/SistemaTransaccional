package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.appdis.g2.sistemaTransaccional.dao.RegistroDAO;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Registro;

@Stateless
public class GestionRegistroON implements GestionRegistroOnRemoto {

	@Inject
	private RegistroDAO daoRegistro;

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

	public boolean validarIngresoCuenta(String usuario, String contrasenia) {
		return true;
	}

	public boolean cambioContrasena(String correo) {
		return true;
	}

}
