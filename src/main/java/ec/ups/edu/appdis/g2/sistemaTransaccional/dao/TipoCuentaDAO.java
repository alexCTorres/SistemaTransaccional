package ec.ups.edu.appdis.g2.sistemaTransaccional.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.TipoCuenta;

@Stateless
public class TipoCuentaDAO {

	@Inject
	private EntityManager em;

	@Inject
	private Connection con;

	// metodo de insertar con JPA utilizando el Entity manager
	public boolean insertJPA(TipoCuenta tipoCuenta) throws SQLException {
		em.persist(tipoCuenta);
		return true;
	}

	// metodo de update con JPA utilizando el Entity manager
	public boolean updateJPA(TipoCuenta tipoCuenta) throws SQLException {
		em.merge(tipoCuenta);
		return true;
	}

	// metodo de read con JPA utilizando el Entity manager
	public TipoCuenta readJPA(int id) throws SQLException {
		TipoCuenta tipoCuenta = em.find(TipoCuenta.class, id);
		return tipoCuenta;
	}

	// metodo de delete con JPA utilizando el Entity manager
	public boolean deleteJPA(int id) throws SQLException {
		TipoCuenta tipoCuenta = em.find(TipoCuenta.class, id);
		em.remove(tipoCuenta);
		return true;
	}
}
