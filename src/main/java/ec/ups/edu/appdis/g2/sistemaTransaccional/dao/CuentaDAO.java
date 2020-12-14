package ec.ups.edu.appdis.g2.sistemaTransaccional.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Cuenta;

@Stateless
public class CuentaDAO {

	@Inject
	private EntityManager em;

	@Inject
	private Connection con;

	// metodo de insertar con JPA utilizando el Entity manager
	public boolean insertJPA(Cuenta cuenta) throws SQLException {
		em.persist(cuenta);
		return true;
	}

	// metodo de update con JPA utilizando el Entity manager
	public boolean updateJPA(Cuenta cuenta) throws SQLException {
		em.merge(cuenta);
		return true;
	}

	// metodo de read con JPA utilizando el Entity manager
	public Cuenta readJPA(String numCuenta) throws SQLException {
		Cuenta c = em.find(Cuenta.class, numCuenta);
		return c;
	}

	// metodo de delete con JPA utilizando el Entity manager
	public boolean deleteJPA(String numCuenta) throws SQLException {
		Cuenta c = em.find(Cuenta.class, numCuenta);
		em.remove(c);
		return true;
	}
}
