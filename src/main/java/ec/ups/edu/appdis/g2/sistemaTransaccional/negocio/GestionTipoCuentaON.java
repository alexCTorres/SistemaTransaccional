package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.appdis.g2.sistemaTransaccional.dao.TipoCuentaDAO;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.TipoCuenta;

@Stateless
public class GestionTipoCuentaON {


	@Inject
	private TipoCuentaDAO daoTipoCuenta;
	
	public boolean registrarTipoCuenta(TipoCuenta tipoCuenta) {
		return true;
	}
	
	public boolean actualizarTipoCuenta(TipoCuenta tipoCuenta) {
		return true;
	}
	
	public TipoCuenta buscarTipoCuenta(int id) {
		TipoCuenta tipoCuenta = new TipoCuenta();
		try {
			tipoCuenta = daoTipoCuenta.readJPA(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tipoCuenta;
	}
	
	public boolean eliminarTipoCuenta(int id) {
		return true;
	}
}
