package ec.ups.edu.appdis.g2.sistemaTransaccional.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Registro;

@Stateless
public class RegistroDAO {

	@Inject
	private EntityManager em;

	@Inject
	private Connection con;

	/**
	 * metodo de insertar con JPA utilizando el Entity manager
	 * @param registro
	 * @return
	 * @throws SQLException
	 */
	public boolean insertJPA(Registro registro) throws SQLException {
		em.persist(registro);
		return true;
	}

	/**
	 * metodo de update con JPA utilizando el Entity manager
	 * @param registro
	 * @return
	 * @throws SQLException
	 */
	public boolean updateJPA(Registro registro) throws SQLException {
		em.merge(registro);
		return true;
	}

	/**
	 * metodo de read con JPA utilizando el Entity manager
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Registro readJPA(int id) throws SQLException {
		Registro r = em.find(Registro.class, id);
		return r;
	}

	/**
	 * metodo de delete con JPA utilizando el Entity manager
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteJPA(int id) throws SQLException {
		Registro r = em.find(Registro.class, id);
		em.remove(r);
		return true;
	}

	/**
	 * listar cuentas mediante id utilizando jpa
	 * @param usuario
	 * @return
	 */
		public List<Registro> listaRegistros(String usuario) {
			Query query = em.createQuery("select r from Registro r where r.usuario.nombreUsuario = ?1 order by 3 desc", Registro.class);
			query.setParameter(1, usuario);
			List<Registro> lista = query.getResultList();
			return lista;
		}
}
