package ec.ups.edu.appdis.g2.sistemaTransaccional.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.TasaInteres;

@Stateless
public class TasaInteresDAO {

	@Inject
	private EntityManager em;

	@Inject
	private Connection con;

	// metodo de insertar con JPA utilizando el Entity manager
	public boolean insertJPA(TasaInteres tasaInteres) throws SQLException {
		em.persist(tasaInteres);
		return true;
	}

	// metodo de update con JPA utilizando el Entity manager
	public boolean updateJPA(TasaInteres tasaInteres) throws SQLException {
		em.merge(tasaInteres);
		return true;
	}

	// metodo de read con JPA utilizando el Entity manager
	public TasaInteres readJPA(int id) throws SQLException {
		TasaInteres tasaInteres = em.find(TasaInteres.class, id);
		return tasaInteres;
	}

	// metodo de delete con JPA utilizando el Entity manager
	public boolean deleteJPA(int id) throws SQLException {
		TasaInteres tasaInteres = em.find(TasaInteres.class, id);
		em.remove(tasaInteres);
		return true;
	}
}
