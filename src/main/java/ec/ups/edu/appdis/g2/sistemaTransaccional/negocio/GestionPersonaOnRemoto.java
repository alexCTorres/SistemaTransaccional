package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import javax.ejb.Remote;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Persona;

@Remote
public interface GestionPersonaOnRemoto {

	public boolean registrarPersona(Persona persona) throws Exception;

	public boolean actualizarPersona(Persona persona) throws Exception;

	public Persona buscarPersona(int id) throws Exception;

	public boolean eliminarPersona(int id) throws Exception;
}
