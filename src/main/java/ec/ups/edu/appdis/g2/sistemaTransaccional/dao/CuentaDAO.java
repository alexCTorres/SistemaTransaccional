package ec.ups.edu.appdis.g2.sistemaTransaccional.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Persona;

@Stateless
public class CuentaDAO {

	@Inject
	private EntityManager em;

	@Inject
	private PersonaDAO daoPersona;

	@Inject
	private TipoCuentaDAO daoTipCuenta;

	@Inject
	private Connection con;

	// metodo de insertar con JPA utilizando el Entity manager
	public boolean insertJPA(Cuenta cuenta) throws SQLException {
		em.persist(cuenta);
		return true;
	}

	// metodo de update con JPA utilizando el Entity manager
	public boolean updateJPA(Cuenta cuenta) throws SQLException {
		em.merge(cuenta);
		return true;
	}

	// metodo de read con JPA utilizando el Entity manager
	public Cuenta readJPA(String numCuenta) throws SQLException {
		Cuenta c = em.find(Cuenta.class, numCuenta);
		return c;
	}

	// metodo de delete con JPA utilizando el Entity manager
	public boolean deleteJPA(String numCuenta) throws SQLException {
		Cuenta c = em.find(Cuenta.class, numCuenta);
		em.remove(c);
		return true;
	}

	// listar cuentas por id mediante jdbc
	public List<Cuenta> listaCuentasPersona(int idPersona) {
		String jpql = "select c from Cuenta c where c.per_id = ?1 ";
		List<Cuenta> listacuenta = new ArrayList<Cuenta>();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(jpql);
			ps.setInt(1, idPersona);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Cuenta u = new Cuenta();
				u.setNumeroCuenta(rs.getString("cue_numero_cuenta"));
				u.setEstado(rs.getString("cue_estado"));
				u.setFechaApertura(rs.getDate("cue_fecha_apertura"));
				u.setSaldo(rs.getDouble("cue_saldo"));
				u.setPersona(daoPersona.readJPA(rs.getInt("persona_id_fk")));
				u.setTipoCuenta(daoTipCuenta.readJPA(rs.getInt("tipcuenta_id_fk")));
				listacuenta.add(u);
			}
			ps.close();
			return listacuenta;
		} catch (SQLException e) {
			System.out.println("cuenta no encontrada " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	// listar cuentas mediante id utilizando jpa
	public List<Cuenta> listaCUentas(int idPersona) {
		Query query = em.createQuery("select c from Cuenta c where c.persona.id = ?1", Cuenta.class);
		query.setParameter(1, idPersona);
		List<Cuenta> lista = query.getResultList();
		return lista;
	}

	// metodo para listar las cuentas creadas
	public List<Cuenta> listaCuentas() {
		String jpql = "Select c FROM Cuenta c";
		Query q = em.createQuery(jpql, Cuenta.class);
		return (List<Cuenta>) q.getResultList();
	}

}
