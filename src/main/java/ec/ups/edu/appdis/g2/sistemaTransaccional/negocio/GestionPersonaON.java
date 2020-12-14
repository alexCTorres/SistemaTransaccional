package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.appdis.g2.sistemaTransaccional.dao.PersonaDAO;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Persona;

@Stateless
public class GestionPersonaON implements GestionPersonaOnRemoto {

	@Inject
	private PersonaDAO daoPersona;
	
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
	
	public Persona buscarPersona(String cedula) throws Exception {
		Persona p = new Persona();
		try {
			p = daoPersona.readJPA(cedula);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return p;
	}
	
	public boolean eliminarPersona(String cedula) throws Exception {
		try {
			Persona p = daoPersona.readJPA(cedula);
			if(p==null) {
				System.out.println("Persona no encontrada");
			}else {
				daoPersona.deleteJPA(p.getCedula());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		
		return true;
	}
}
