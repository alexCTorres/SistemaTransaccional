package ec.ups.edu.appdis.g2.sistemaTransaccional.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.TransferenciasExternas;

@Stateless
public class TransferenciasExternasDAO {

	@Inject
	private EntityManager em;

	@Inject
	private Connection con;

	// metodo de insertar con JPA utilizando el Entity manager
	public boolean insertJPA(TransferenciasExternas transExternas) throws SQLException {
		em.persist(transExternas);
		return true;
	}

	// metodo de update con JPA utilizando el Entity manager
	public boolean updateJPA(TransferenciasExternas transExternas) throws SQLException {
		em.merge(transExternas);
		return true;
	}

	// metodo de read con JPA utilizando el Entity manager
	public TransferenciasExternas readJPA(int id) throws SQLException {
		TransferenciasExternas transExternas = em.find(TransferenciasExternas.class, id);
		return transExternas;
	}

	// metodo de delete con JPA utilizando el Entity manager
	public boolean deleteJPA(int id) throws SQLException {
		TransferenciasExternas transExternas = em.find(TransferenciasExternas.class, id);
		em.remove(transExternas);
		return true;
	}
}
