package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import javax.ejb.Remote;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Registro;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Usuario;

@Remote
public interface GestionRegistroOnRemoto {

	public boolean registrarRegistro(Registro registro) throws Exception;

	public Registro buscarRegistro(int id) throws Exception;

	public boolean eliminarRegistro(int id) throws Exception;

	public boolean enviarCorreoIngresoCuenta(String correo);

	public boolean bloquearCuenta(int intentos);

	public Usuario buscarUsuarioNombre(String usuario) throws Exception;

	public boolean cambioContrasena(String correo);
}
