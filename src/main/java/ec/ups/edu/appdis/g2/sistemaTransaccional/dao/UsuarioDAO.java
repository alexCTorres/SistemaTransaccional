package ec.ups.edu.appdis.g2.sistemaTransaccional.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Usuario;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionUsuarioON;

@Stateless
public class UsuarioDAO {

	@Inject
	private EntityManager em;

	@Inject
	private Connection con;

	@Inject
	private PersonaDAO daoPersona;

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
	public Usuario readJPA(String nomUsuario) throws Exception {
		Usuario usuario = new Usuario(); 
		try {
			usuario =  em.find(Usuario.class, nomUsuario);
			System.out.println("Usuario encontrado DAO: " + usuario.getNombreUsuario());
			return usuario;
		}catch (Exception e) {
			System.out.println("No encontrado error DAOUsuario: " +e.getMessage());
			return null;
		}

	}


	public Usuario buscarPorUsuarioJPA(String usuario) throws SQLException {
		System.out.println("entra al sql");
		String sql = "SELECT u FROM ef_usuarios u WHERE u.usu_nombre_usuario=?1";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, usuario);
		ResultSet rs = ps.executeQuery();
		System.out.println("salio de la consulta y ejecuto usuario" + rs.getString("usu_nombre_usuario"));
		Usuario u = new Usuario();
		if (rs.next()) {
			u.setNombreUsuario(rs.getString("usu_nombre_usuario"));
			u.setContrasenia(rs.getString("usu_contrasenia"));
			u.setRol(rs.getString("usu_rol"));
			u.setEstado(rs.getString("usu_estado"));
			u.setIntentosLogin(rs.getInt("usu_intentos_logeo"));
			u.setPersona(daoPersona.readJPA(rs.getInt("fk_persona_id")));

		} else {
			System.out.println("Usuario no encontrado");
		}
		ps.close();
		return u;
	}

	// metodo de delete con JPA utilizando el Entity manager
	public boolean deleteJPA(String nomUsuario) throws SQLException {
		Usuario usuario = em.find(Usuario.class, nomUsuario);
		em.remove(usuario);
		return true;
	}
	
	public List<Usuario> listaUsuarios(){
		String jpql = "Select u FROM Usuario u";
		Query q = em.createQuery(jpql,Usuario.class);
		return (List<Usuario>) q.getResultList();
	}
}
