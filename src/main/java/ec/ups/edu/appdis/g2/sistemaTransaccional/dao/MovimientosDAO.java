package ec.ups.edu.appdis.g2.sistemaTransaccional.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Movimientos;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Usuario;

@Stateless
public class MovimientosDAO {

	@Inject
	private EntityManager em;

	@Inject
	private Connection con;

	// metodo de insertar con JPA utilizando el Entity manager
	public boolean insertJPA(Movimientos movimientos) throws SQLException {
		em.persist(movimientos);
		return true;
	}

	// metodo de update con JPA utilizando el Entity manager
	public boolean updateJPA(Movimientos movimientos) throws SQLException {
		em.merge(movimientos);
		return true;
	}

	// metodo de read con JPA utilizando el Entity manager
	public Movimientos readJPA(int id) throws SQLException {
		Movimientos m = em.find(Movimientos.class, id);
		return m;
	}

	// metodo de delete con JPA utilizando el Entity manager
	public boolean deleteJPA(int id) throws SQLException {
		Movimientos m = em.find(Movimientos.class, id);
		em.remove(m);
		return true;
	}
	
	public List<Movimientos> listaMovimientos(){
		String jpql = "Select m FROM Movimientos m";
		Query q = em.createQuery(jpql,Movimientos.class);
		return (List<Movimientos>) q.getResultList();
	}
}
