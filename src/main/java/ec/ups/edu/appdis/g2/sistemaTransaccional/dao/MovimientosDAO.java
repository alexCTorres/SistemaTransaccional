package ec.ups.edu.appdis.g2.sistemaTransaccional.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Movimientos;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Poliza;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Usuario;

@Stateless
public class MovimientosDAO {

	@Inject
	private EntityManager em;

	@Inject
	private Connection con;

	/**
	 * metodo de insertar con JPA utilizando el Entity manager
	 * @param movimientos
	 * @return
	 * @throws SQLException
	 */
	public boolean insertJPA(Movimientos movimientos) throws SQLException {
		em.persist(movimientos);
		return true;
	}

	/**
	 * metodo de update con JPA utilizando el Entity manager
	 * @param movimientos
	 * @return
	 * @throws SQLException
	 */
	public boolean updateJPA(Movimientos movimientos) throws SQLException {
		em.merge(movimientos);
		return true;
	}

	/**
	 * metodo de read con JPA utilizando el Entity manager
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Movimientos readJPA(int id) throws SQLException {
		Movimientos m = em.find(Movimientos.class, id);
		return m;
	}

	/**
	 * metodo de delete con JPA utilizando el Entity manager
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteJPA(int id) throws SQLException {
		Movimientos m = em.find(Movimientos.class, id);
		em.remove(m);
		return true;
	}
	
	/**
	 * listar todos los movimientos por jpa 
	 * @return
	 */
	public List<Movimientos> listaMovimientos(){
		String jpql = "Select m FROM Movimientos m";
		Query q = em.createQuery(jpql,Movimientos.class);
		return (List<Movimientos>) q.getResultList();
	}
	
	public List<Movimientos> listaMovimientosporCuenta(String numCuenta){
		Query query = em.createQuery("Select m FROM Movimientos m, Cuenta c Where m.cuenta = c.numeroCuenta and c.numeroCuenta = ?1", Movimientos.class);
		query.setParameter(1, numCuenta);
	    List<Movimientos> listaMov = query.getResultList();
	    return listaMov;
	}
}
