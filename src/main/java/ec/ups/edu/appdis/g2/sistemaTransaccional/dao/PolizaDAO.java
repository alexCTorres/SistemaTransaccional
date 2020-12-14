package ec.ups.edu.appdis.g2.sistemaTransaccional.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Poliza;

@Stateless
public class PolizaDAO {

	@Inject
	private EntityManager em;

	@Inject
	private Connection con;

	// metodo de insertar con JPA utilizando el Entity manager
	public boolean insertJPA(Poliza poliza) throws SQLException {
		em.persist(poliza);
		return true;
	}

	// metodo de update con JPA utilizando el Entity manager
	public boolean updateJPA(Poliza poliza) throws SQLException {
		em.merge(poliza);
		return true;
	}

	// metodo de read con JPA utilizando el Entity manager
	public Poliza readJPA(int id) throws SQLException {
		Poliza p = em.find(Poliza.class, id);
		return p;
	}

	// metodo de delete con JPA utilizando el Entity manager
	public boolean deleteJPA(int id) throws SQLException {
		Poliza p = em.find(Poliza.class, id);
		em.remove(p);
		return true;
	}
}
