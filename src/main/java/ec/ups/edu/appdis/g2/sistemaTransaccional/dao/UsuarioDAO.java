package ec.ups.edu.appdis.g2.sistemaTransaccional.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.naming.spi.DirStateFactory.Result;
import javax.persistence.EntityManager;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Usuario;

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
	public Usuario readJPA(int id) throws SQLException {
		Usuario usuario = em.find(Usuario.class, id);
		return usuario;
	}
	
	public Usuario buscarPorUsuarioJPA(String usuario) throws SQLException {
		System.out.println("entra al sql");
		String sql ="SELECT * FROM ef_usuarios WHERE usu_nombre_usuario = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, usuario);
		ResultSet rs = ps.executeQuery();
		System.out.println("salio de la consulta y ejecuto usuario" +rs.getString("usu_nombre_usuario"));
		Usuario u = new Usuario();
		if(rs.next()){
			u.setId(rs.getInt("usu_id"));
			u.setNombreUsuario(rs.getString("usu_nombre_usuario"));
			u.setContrasenia(rs.getString("usu_contrasenia"));
			u.setRol(rs.getString("usu_rol"));
			u.setEstado(rs.getString("usu_estado"));
			u.setIntentosLogin(rs.getInt("usu_intentos_logeo"));
			u.setPersona(daoPersona.readJPA(rs.getInt("fk_persona_id")));
			
		}else {
			System.out.println("Usuario no encontrado");
		}
		ps.close();
		return u;
	}

	// metodo de delete con JPA utilizando el Entity manager
	public boolean deleteJPA(int id) throws SQLException {
		Usuario usuario = em.find(Usuario.class, id);
		em.remove(usuario);
		return true;
	}
}
