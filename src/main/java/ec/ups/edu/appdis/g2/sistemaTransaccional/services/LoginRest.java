package ec.ups.edu.appdis.g2.sistemaTransaccional.services;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Usuario;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionUsuarioON;

@Path("/login")
public class LoginRest {

	@Inject
	private GestionUsuarioON usuarioON;
	
	/*
	 * put -> crear recurso
	 * get -> consultar recurso
	 * post -> actualizar un recurso / llamar a una transaccion o procedimiento
	 * delete -> borrar un recurso
	 * 
	 * */
	
	@GET
	@Path("/verCredenciales")
	@Produces("application/json")
	public Usuario logeo(@QueryParam("nombreUsuario") String nombreUsuario, @QueryParam("password") String password) {
		Usuario u = new Usuario();
		try {
			u = usuarioON.buscarUsuario(nombreUsuario);
			if(u.getContrasenia().equals(password)==true) {
				return u;
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
	

	@GET
	@Path("/buscarUser")
	@Produces("application/json")
	public Usuario buscarUsuario(@QueryParam("nombreUsuario") String nombreUsuario) {
		Usuario u = new Usuario();
		try {
			u = usuarioON.buscarUsuario(nombreUsuario);
			if(u!=null) {
				return u;
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
}
