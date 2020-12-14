package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.appdis.g2.sistemaTransaccional.dao.RegistroDAO;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Registro;

@Stateless
public class GestionRegistroON {

	@Inject
	private RegistroDAO daoRegistro;
	
	public boolean registrarRegistro(Registro registro) {
		return true;
	}

	
	public Registro buscarRegistro(int id) {
		Registro r = new Registro();
		try {
			r = daoRegistro.readJPA(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	public boolean eliminarRegistro(int id) {
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
	
	private boolean cambioContrasenia(String correo) {
		return true;
	}
	
}
