package ec.ups.edu.appdis.g2.sistemaTransaccional.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Registro;

@Stateless
public class RegistroDAO {

	@Inject
	private EntityManager em;

	@Inject
	private Connection con;

	// metodo de insertar con JPA utilizando el Entity manager
	public boolean insertJPA(Registro registro) throws SQLException {
		em.persist(registro);
		return true;
	}

	// metodo de update con JPA utilizando el Entity manager
	public boolean updateJPA(Registro registro) throws SQLException {
		em.merge(registro);
		return true;
	}

	// metodo de read con JPA utilizando el Entity manager
	public Registro readJPA(int id) throws SQLException {
		Registro r = em.find(Registro.class, id);
		return r;
	}

	// metodo de delete con JPA utilizando el Entity manager
	public boolean deleteJPA(int id) throws SQLException {
		Registro r = em.find(Registro.class, id);
		em.remove(r);
		return true;
	}

}
