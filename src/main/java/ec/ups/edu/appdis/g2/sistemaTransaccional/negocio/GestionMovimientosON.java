package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.appdis.g2.sistemaTransaccional.dao.MovimientosDAO;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Movimientos;


@Stateless
public class GestionMovimientosON {

	@Inject
	private MovimientosDAO daoMovimientos;
	
	public boolean registrarMoviemientos(Movimientos movimientos) {
		return true;
	}
	
	public boolean actualizarMovimientos(Movimientos movimientos) {
		return true;
	}
	
	public Movimientos buscarCuenta(int id) {
		Movimientos m  = new Movimientos();
		try {
			m = daoMovimientos.readJPA(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}
	
	public boolean eliminarMoviemientos(int id) {
		return true;
	}
}
