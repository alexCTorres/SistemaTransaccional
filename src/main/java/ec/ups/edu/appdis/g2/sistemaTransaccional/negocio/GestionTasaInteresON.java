package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import ec.ups.edu.appdis.g2.sistemaTransaccional.dao.TasaInteresDAO;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.TasaInteres;

@Stateless
public class GestionTasaInteresON {

	@Inject
	private TasaInteresDAO daoTasaInteres;

	/**
	 * 
	 * @param tasaInteres
	 * @return
	 * @throws Exception
	 */
	public boolean registrarTasaInteres(TasaInteres tasaInteres) throws Exception {
		try {
			daoTasaInteres.insertJPA(tasaInteres);
			System.out.println("Insertado corectamente DAO interes ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param tasaInteres
	 * @return
	 * @throws Exception
	 */
	public boolean actualizarTasaInteres(TasaInteres tasaInteres) throws Exception {
		try {
			daoTasaInteres.updateJPA(tasaInteres);
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
	public TasaInteres buscarTasaInteres(int id) throws Exception {
		TasaInteres t = new TasaInteres();
		try {
			t = daoTasaInteres.readJPA(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return t;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean eliminarTasaInteres(int id) throws Exception {
		try {
			TasaInteres ti = daoTasaInteres.readJPA(id);
			if (ti == null) {
				System.out.println("Tasa de interes no encontrada");
			} else {
				daoTasaInteres.deleteJPA(ti.getId());
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
	public List<TasaInteres> getTasaInteres(){
		return daoTasaInteres.listaTasaI();
	}
	
	/**
	 * 
	 * @return
	 */
	public int extraerCodigo() {
		return daoTasaInteres.extraerCOdigo();
	}
}
