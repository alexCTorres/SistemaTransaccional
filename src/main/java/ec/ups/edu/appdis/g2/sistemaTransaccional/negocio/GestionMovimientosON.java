package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.appdis.g2.sistemaTransaccional.dao.MovimientosDAO;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Movimientos;


@Stateless
public class GestionMovimientosON {

	@Inject
	private MovimientosDAO daoMovimientos;
	
	/**
	 * 
	 * @param movimientos
	 * @return
	 * @throws Exception
	 */
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
	
	/**
	 * 
	 * @param movimientos
	 * @return
	 * @throws Exception
	 */
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
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
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
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
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
	
	/**
	 * 
	 * @return
	 */
	public List<Movimientos> getMovimientis(){
		return daoMovimientos.listaMovimientos();
	}
	
	/**
	 * 
	 * @param numCuenta
	 * @return
	 */
	public List<Movimientos> getMovimientosCuenta(String numCuenta){
		return daoMovimientos.listaMovimientosporCuenta(numCuenta);
	}
}
