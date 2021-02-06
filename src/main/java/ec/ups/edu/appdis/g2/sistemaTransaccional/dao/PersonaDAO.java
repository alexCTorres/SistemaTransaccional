package ec.ups.edu.appdis.g2.sistemaTransaccional.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Persona;

@Stateless
public class PersonaDAO {

	@Inject
	private EntityManager em;

	@Inject
	private Connection con;

	/*
	 * public boolean insert(Persona persona) throws SQLException { String sql =
	 * "Insert Into vehiculo VALUES (?,?,?,?,?,?,?,?)"; PreparedStatement ps =
	 * con.prepareStatement(sql); ps.setInt(1, persona.getId()); ps.setString(2,
	 * persona.getNombres()); ps.setString(3, persona.getApellidos());
	 * ps.executeUpdate(); ps.close(); return true; }
	 */

	// metodo de insertar con JPA utilizando el Entity manager
	public boolean insertJPA(Persona persona) throws SQLException {
		em.persist(persona);
		return true;
	}

	// metodo de update con JPA utilizando el Entity manager
	public boolean updateJPA(Persona persona) throws SQLException {
		em.merge(persona);
		return true;
	}

	// metodo de read con JPA utilizando el Entity manager
	public Persona readJPA(int id) throws SQLException {
		Persona p = em.find(Persona.class, id);
		return p;
	}
	

	// metodo de delete con JPA utilizando el Entity manager
	public boolean deleteJPA(String cedula) throws SQLException {
		Persona p = em.find(Persona.class, cedula);
		em.remove(p);
		return true;
	}
	
//metodo para buscar una persona mediante la cedula
	public Persona buscarPorCedula(String cedula) {
		String jpql = "Select p FROM Persona p where cedula = ?1";
		Persona p = new Persona();
		Query q = em.createQuery(jpql,Persona.class);
		q.setParameter(1, cedula);
		//p = q.getSingleResult();
		return p;
	}
}
