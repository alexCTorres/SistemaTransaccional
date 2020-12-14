package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.appdis.g2.sistemaTransaccional.dao.MovimientosDAO;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Movimientos;


@Stateless
public class GestionMovimientosON implements GestionMovimientosOnRemoto{

	@Inject
	private MovimientosDAO daoMovimientos;
	
	public boolean registrarMoviemientos(Movimientos movimientos) throws Exception {
		try {
			daoMovimientos.insertJPA(movimientos);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return true;
	}
	
	public boolean actualizarMovimientos(Movimientos movimientos) throws Exception {
		try {
			daoMovimientos.updateJPA(movimientos);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return true;
	}
	
	public Movimientos buscarCuenta(int id) throws Exception {
		Movimientos m  = new Movimientos();
		try {
			m = daoMovimientos.readJPA(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return m;
	}
	
	public boolean eliminarMovimientos(int id) throws Exception {
		try {
			Movimientos m = daoMovimientos.readJPA(id);
			if(m==null) {
				System.out.println("No existe el movimiento");
			}else {
				daoMovimientos.deleteJPA(m.getId());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return true;
	}
}
