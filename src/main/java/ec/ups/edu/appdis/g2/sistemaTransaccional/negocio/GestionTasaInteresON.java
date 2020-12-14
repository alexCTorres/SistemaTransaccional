package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.crypto.dsig.Manifest;

import ec.ups.edu.appdis.g2.sistemaTransaccional.dao.TasaInteresDAO;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.TasaInteres;

@Stateless
public class GestionTasaInteresON implements GestionTasaInteresOnRemoto {

	@Inject
	private TasaInteresDAO daoTasaInteres;

	public boolean registrarTasaInteres(TasaInteres tasaInteres) throws Exception {
		try {
			daoTasaInteres.insertJPA(tasaInteres);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return true;
	}

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
}
