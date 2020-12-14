package ec.ups.edu.appdis.g2.sistemaTransaccional.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Usuario;

@Stateless
public class UsuarioDAO {

	@Inject
	private EntityManager em;

	@Inject
	private Connection con;

	// metodo de insertar con JPA utilizando el Entity manager
	public boolean insertJPA(Usuario usuario) throws SQLException {
		em.persist(usuario);
		return true;
	}

	// metodo de update con JPA utilizando el Entity manager
	public boolean updateJPA(Usuario usuario) throws SQLException {
		em.merge(usuario);
		return true;
	}

	// metodo de read con JPA utilizando el Entity manager
	public Usuario readJPA(int id) throws SQLException {
		Usuario usuario = em.find(Usuario.class, id);
		return usuario;
	}

	// metodo de delete con JPA utilizando el Entity manager
	public boolean deleteJPA(int id) throws SQLException {
		Usuario usuario = em.find(Usuario.class, id);
		em.remove(usuario);
		return true;
	}
}
