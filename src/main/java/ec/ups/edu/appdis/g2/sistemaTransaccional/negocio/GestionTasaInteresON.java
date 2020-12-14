package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.appdis.g2.sistemaTransaccional.dao.TasaInteresDAO;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.TasaInteres;

@Stateless
public class GestionTasaInteresON {

	@Inject
	private TasaInteresDAO daoTasaInteres;

	public boolean registrarTasaInteres(TasaInteres tasaInteres) {
		return true;
	}

	public boolean actualizarTasaInteres(TasaInteres tasaInteres) {
		return true;
	}

	public TasaInteres buscarTasaInteres(int id) {
		TasaInteres t = new TasaInteres();
		try {
			t = daoTasaInteres.readJPA(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

	public boolean eliminarTasaInteres(int id) {
		return true;
	}
}
