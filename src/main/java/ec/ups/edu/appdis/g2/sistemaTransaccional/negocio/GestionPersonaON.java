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
	
	public boolean registrarPersona(Persona persona) {
		return true;
	}
	
	public boolean actualizarPersona(Persona persona) {
		return true;
	}
	
	public Persona buscarPersona(String cedula) {
		Persona p = new Persona();
		try {
			p = daoPersona.readJPA(cedula);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	
	public boolean eliminarPersona(String cedula) {
		return true;
	}
}