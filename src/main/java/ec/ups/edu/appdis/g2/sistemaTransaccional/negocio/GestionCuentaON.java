package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import ec.ups.edu.appdis.g2.sistemaTransaccional.dao.CuentaDAO;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Cuenta;

@Stateless
public class GestionCuentaON {

	@Inject
	private CuentaDAO daoCuenta;

	/**
	 * 
	 * @param cuenta
	 * @return 
	 * @throws Exception
	 */
	public boolean registrarCuenta(Cuenta cuenta) throws Exception {
		try {
			daoCuenta.insertJPA(cuenta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return true;
	}

	/**
	 * 
	 * @param cuenta
	 * @return
	 * @throws Exception
	 */
	public boolean actualizarCuenta(Cuenta cuenta) throws Exception {
		try {
			daoCuenta.updateJPA(cuenta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return true;
	}

	/**
	 * 
	 * @param numCuenta
	 * @return
	 * @throws Exception
	 */
	public Cuenta buscarCuenta(String numCuenta) throws Exception {
		Cuenta c = new Cuenta();
		try {
			c = daoCuenta.readJPA(numCuenta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return c;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<Cuenta> listaCuentas(int id){
		return daoCuenta.listaCUentas(id);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Cuenta> listarCuentasALL(){
		return daoCuenta.listaCuentas();
	}
	
	/**
	 * 
	 * @param nomUsuario
	 * @return
	 */
	public List<Cuenta> listarCuentasprUsuario(String nomUsuario){
		return daoCuenta.listaCuentasUsuario(nomUsuario);
	}
}
