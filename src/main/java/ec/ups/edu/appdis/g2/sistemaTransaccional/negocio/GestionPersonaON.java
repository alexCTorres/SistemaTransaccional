package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.appdis.g2.sistemaTransaccional.dao.PersonaDAO;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Persona;

@Stateless
public class GestionPersonaON {

	@Inject
	private PersonaDAO daoPersona;

	/**
	 * 
	 * @param persona
	 * @return
	 * @throws Exception
	 */
	public boolean registrarPersona(Persona persona) throws Exception {
		try {
			daoPersona.insertJPA(persona);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return true;
	}

	/**
	 * 
	 * @param persona
	 * @return
	 * @throws Exception
	 */
	public boolean actualizarPersona(Persona persona) throws Exception {
		try {
			daoPersona.updateJPA(persona);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return true;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Persona buscarPersona(int id) throws Exception {
		Persona p = new Persona();
		try {
			p = daoPersona.readJPA(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return p;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean eliminarPersona(int id) throws Exception {
		try {
			Persona p = daoPersona.readJPA(id);
			if (p == null) {
				System.out.println("Persona no encontrada");
			} else {
				daoPersona.deleteJPA(p.getCedula());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}

		return true;
	}

	/**
	 * 
	 * @param cedula
	 * @return
	 */
	public Persona buscarPorCed(String cedula) {
		Persona p = new Persona();
		p = daoPersona.buscarPorCedula(cedula);
		return p;
	}
}
