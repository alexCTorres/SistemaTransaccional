package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import javax.ejb.Remote;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Usuario;

@Remote
public interface GestionUsuarioOnRemoto {

	public boolean registrarUsuario(Usuario usuario) throws Exception;

	public boolean actualizarUsuario(Usuario usuario) throws Exception;

	public Usuario buscarUsuario(int id) throws Exception;

	public boolean eliminarUsuario(int id) throws Exception;

}
