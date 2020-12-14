package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.appdis.g2.sistemaTransaccional.dao.CuentaDAO;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Cuenta;

@Stateless
public class GestionCuentaON {

	@Inject
	private CuentaDAO daoCuenta;
	
	public boolean registrarCuenta(Cuenta cuenta) {
		return true;
	}
	
	public boolean actualizarCuenta(Cuenta cuenta) {
		return true;
	}
	
	public Cuenta buscarCuenta(String numCuenta) {
		Cuenta c  = new Cuenta();
		try {
			c = daoCuenta.readJPA(numCuenta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
	public boolean eliminarCuenta(String numCuenta) {
		return true;
	}
	
	public boolean enviarCorreoDatosCuenta(String correo) {
		return true;
	}
}
