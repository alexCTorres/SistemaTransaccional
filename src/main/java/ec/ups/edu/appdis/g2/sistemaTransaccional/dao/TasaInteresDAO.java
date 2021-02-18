package ec.ups.edu.appdis.g2.sistemaTransaccional.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.TasaInteres;

@Stateless
public class TasaInteresDAO {

	@Inject
	private EntityManager em;

	@Inject
	private Connection con;

	/**
	 * metodo de insertar con JPA utilizando el Entity manager
	 * @param tasaInteres
	 * @return
	 * @throws SQLException
	 */
	public boolean insertJPA(TasaInteres tasaInteres) throws SQLException {
		em.persist(tasaInteres);
		return true;
	}

	/**
	 *  metodo de update con JPA utilizando el Entity manager
	 * @param tasaInteres
	 * @return
	 * @throws SQLException
	 */
	public boolean updateJPA(TasaInteres tasaInteres) throws SQLException {
		em.merge(tasaInteres);
		return true;
	}

	/**
	 * metodo de read con JPA utilizando el Entity manager
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public TasaInteres readJPA(int id) throws SQLException {
		TasaInteres tasaInteres = em.find(TasaInteres.class, id);
		return tasaInteres;
	}

	/**
	 * metodo de delete con JPA utilizando el Entity manager
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteJPA(int id) throws SQLException {
		TasaInteres tasaInteres = em.find(TasaInteres.class, id);
		em.remove(tasaInteres);
		return true;
	}
	
	/**
	 * listar tasa de interes jpa
	 * @return
	 */
	public List<TasaInteres> listaTasaI(){
		String jpql = "Select t FROM TasaInteres t";
		Query q = em.createQuery(jpql,TasaInteres.class);
		return (List<TasaInteres>) q.getResultList();
	}
	
	/**
	 * extraer el codigo maximo pk de la tasa de interes
	 * @return
	 */
	public int extraerCOdigo() {
		String jpql = "select max(e.id) from TasaInteres e";
		//select max(e.tas_id) from ef_tasa_interes e
		Query q = em.createQuery(jpql,Integer.class);
		int cod = q.getMaxResults();
		return cod+1;
	}
}
