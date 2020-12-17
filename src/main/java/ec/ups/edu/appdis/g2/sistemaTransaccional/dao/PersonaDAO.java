package ec.ups.edu.appdis.g2.sistemaTransaccional.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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
}
