package ec.ups.edu.appdis.g2.sistemaTransaccional.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Movimientos;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Poliza;

@Stateless
public class PolizaDAO {

	@Inject
	private EntityManager em;

	@Inject
	private Connection con;

	/**
	 * metodo de insertar con JPA utilizando el Entity manager
	 * @param poliza
	 * @return
	 * @throws SQLException
	 */
	public boolean insertJPA(Poliza poliza) throws SQLException {
		em.persist(poliza);
		return true;
	}

	/**
	 * metodo de update con JPA utilizando el Entity manager
	 * @param poliza
	 * @return
	 * @throws SQLException
	 */
	public boolean updateJPA(Poliza poliza) throws SQLException {
		em.merge(poliza);
		return true;
	}

	/**
	 * metodo de read con JPA utilizando el Entity manager
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Poliza readJPA(int id) throws SQLException {
		Poliza p = em.find(Poliza.class, id);
		return p;
	}

	/**
	 * metodo de delete con JPA utilizando el Entity manager
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteJPA(int id) throws SQLException {
		Poliza p = em.find(Poliza.class, id);
		em.remove(p);
		return true;
	}
	
	/**
	 * listar todos los Polizas por jpa por num cuenta 
	 * @param numCuenta
	 * @return
	 */
	public List<Poliza> listaPolizas(String numCuenta){
		Query query = em.createQuery("select p from Poliza p where p.cuenta.numeroCuenta = ?1", Poliza.class);
		query.setParameter(1, numCuenta);
		List<Poliza> lista = query.getResultList();
		return lista;
	}
	
	/*public List<Poliza> listaPolizasPorPersona(int id){
		Query query = em.createQuery("select po.id, po.capital, po.diaPago, po.estado, po.fechaVencimiento,\n"
				+ "po.fotoCedula, po.fotoServBasico, po.frecuenciaPago, po.interes, po.plazo, po.cuenta\n"
				+ "from Poliza po, Persona p where po.estado = 'ACTIVO' and p.id = ?1", Poliza.class);
		query.setParameter(1, id);
		List<Poliza> lista = query.getResultList();
		return lista;
	}*/
	
	/**
	 * listar polizas por id persona
	 * @param id
	 * @return
	 */
	public List<Poliza> listaPolizasPorPersona(int id){
		Query query = em.createQuery("select po from Poliza po, Persona p, Cuenta c where po.cuenta = c.numeroCuenta\n"
		+"and c.persona = p.id and po.estado = 'ACTIVO' and p.id = ?1", Poliza.class);
		query.setParameter(1, id);
		List<Poliza> lista = query.getResultList();
		return lista;
	}
	
	/**
	 * listar polizas solicitadas
	 * @return
	 */
	public List<Poliza> listaPolizasSolicitadas(){
		Query query = em.createQuery("select po from Poliza po where po.estado = 'SOLICITADO'", Poliza.class);
		List<Poliza> lista = query.getResultList();
		return lista;
	}
}
