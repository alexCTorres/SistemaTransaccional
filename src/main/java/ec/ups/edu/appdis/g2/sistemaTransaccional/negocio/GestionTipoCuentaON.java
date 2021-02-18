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

	/**
	 * 
	 * @param tipoCuenta
	 * @return
	 * @throws Exception
	 */
	public boolean registrarTipoCuenta(TipoCuenta tipoCuenta) throws Exception {
		try {
			daoTipoCuenta.insertJPA(tipoCuenta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return true;
	}

	/**
	 * 
	 * @param tipoCuenta
	 * @return
	 * @throws Exception
	 */
	public boolean actualizarTipoCuenta(TipoCuenta tipoCuenta) throws Exception {
		try {
			daoTipoCuenta.updateJPA(tipoCuenta);
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
	public TipoCuenta buscarTipoCuenta(int id) throws Exception {
		TipoCuenta tipoCuenta = new TipoCuenta();
		try {
			tipoCuenta = daoTipoCuenta.readJPA(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return tipoCuenta;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean eliminarTipoCuenta(int id) throws Exception {
		try {
			TipoCuenta t = daoTipoCuenta.readJPA(id);
			if (t == null) {
				System.out.println("Tipo cuenta no encontrado");
			} else {
				daoTipoCuenta.deleteJPA(t.getId());
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
	 * @param tipCuenta
	 * @return
	 */
	public TipoCuenta buscarPoNombre(String tipCuenta){
		//TipoCuenta tipo = new TipoCuenta();
		return daoTipoCuenta.buscarPorNombre(tipCuenta);
	}
}
