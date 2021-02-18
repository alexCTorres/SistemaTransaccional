package ec.ups.edu.appdis.g2.sistemaTransaccional.services;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Persona;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Usuario;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionCorreoElectronico;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionCuentaON;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionPersonaON;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionUsuarioON;

@Path("/cuenta")
public class CuentaRest {

	@Inject
	private GestionCuentaON cuentaON;
	
	@Inject
	private GestionPersonaON personaON;
	
	@Inject
	private GestionUsuarioON usuarioON;
	
	@Inject
	private GestionCorreoElectronico correoON;

	
	/*
	 * put -> crear recurso
	 * get -> consultar recurso
	 * post -> actualizar un recurso / llamar a una transaccion o procedimiento
	 * delete -> borrar un recurso
	 * 
	 * */
	
	@GET
	@Path("/cuentasList")
	@Produces("application/json")
	public List<Cuenta> listaCuentas(@QueryParam("nomUsuario") String nomUsuario) {
		System.out.println("Entra a listar cuentas");
		try {
			return cuentaON.listarCuentasprUsuario(nomUsuario);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Path("/buscarCuenta")
	@Produces("application/json")
	public Cuenta buscarCuenta(@QueryParam("numCuenta") String numCuenta) {
		System.out.println("Entra a listar cuentas");
		try {
			return cuentaON.buscarCuenta(numCuenta);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@POST
	@Path("/actializarCuenta")
	@Produces("application/json")
	public Respuesta actualizarCuenta(Cuenta c) {
		Respuesta r = new Respuesta();
		Cuenta cuentaNew = new Cuenta();
		try {
			cuentaON.actualizarCuenta(c);
			r.setCodigo(1);
			r.setMensaje("cuenta actualizada");
			return r;
		} catch (Exception e) {
			e.printStackTrace();
			r.setCodigo(0);
			r.setMensaje("error de cuenta actualizada");
			return r;
		}
	}
	

	@POST
	@Path("/cambioContrasena")
	@Produces("application/json")
	@Consumes("application/json")
	public Respuesta cambiarContrasena(Usuario u) {
		System.out.println("Entra a enviar correo");
		Persona p = new Persona();
		Respuesta r = new Respuesta();
		String nuevaPass = "";
		try {
			System.out.println("buscando persona");
			p = personaON.buscarPorCed(u.getPersona().getCedula());
			nuevaPass = generarSerie();
			u.setContrasenia(nuevaPass);
		    usuarioON.actualizarUsuario(u);
			System.out.println("actualizo usuario");
		    correoON.envioMailCambioContrasena(u);
		    r.setCodigo(1);
		    r.setMensaje("Contrasena enviado correctamenta");
		    System.out.println("Correo Enviado contrasenia");
		    return r;
		} catch (Exception e) {
			e.printStackTrace();
			 r.setCodigo(1);
			 r.setMensaje("Contrasena no se a enviado correctamenta");
			System.out.println("Error al enviar correo contrasenia");
			return r;
		}
	}
	
	public String generarSerie() {
		int n = (int) (Math.random() * (1000 - 1)) + 1;
		int res = n * 10000000;
		res = Math.abs(res);
		System.out.println("numero generado " + res);
		if (res >= 1000000000) {
			return String.valueOf("00" + res);
		}
		if (res >= 100000000 && res < 1000000000) {
			return String.valueOf("000" + res);
		}
		if (res >= 10000000 && res < 100000000) {
			return String.valueOf("0000" + res);
		}
		if (res >= 1000000 && res < 10000000) {
			return String.valueOf("00000" + res);
		}
		if (res >= 100000 && res < 1000000) {
			return String.valueOf("000000" + res);
		}
		if (res >= 10000 && res < 100000) {
			return String.valueOf("0000000" + res);
		}
		if (res >= 1000 && res < 10000) {
			return String.valueOf("00000000" + res);
		}
		if (res >= 100 && res < 1000) {
			return String.valueOf("000000000" + res);
		}
		if (res >= 10 && res < 100) {
			return String.valueOf("0000000000" + res);
		}
		if (res >= 1 && res < 10) {
			return String.valueOf("00000000000" + res);
		} else {
			System.out.println("numero cuenta no encontrado");
			return null;
		}
	}
}
