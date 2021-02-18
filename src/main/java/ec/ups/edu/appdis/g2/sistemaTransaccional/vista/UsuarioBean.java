package ec.ups.edu.appdis.g2.sistemaTransaccional.vista;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Persona;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Usuario;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionPersonaON;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionUsuarioON;

@Named
@RequestScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario newUsuario;

	private Persona newPersona;

	private List<Usuario> listaUsuarios;

	private List<Usuario> listaUsuariosBloqueados;

	private String usuario;

	@Inject
	private GestionUsuarioON usuarioON;

	@Inject
	private GestionPersonaON personaON;

	@PostConstruct
	public void init() {
		newPersona = new Persona();
		newUsuario = new Usuario();
		reloadUsuarios();
		reloadUsuariosBloq();
	}

	public Usuario getNewUsuario() {
		return newUsuario;
	}

	public Persona getNewPersona() {
		return newPersona;
	}

	public void setNewPersona(Persona newPersona) {
		this.newPersona = newPersona;
	}

	public void setNewUsuario(Usuario newUsuario) {
		this.newUsuario = newUsuario;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public List<Usuario> getListaUsuariosBloqueados() {
		return listaUsuariosBloqueados;
	}

	public void setListaUsuariosBloqueados(List<Usuario> listaUsuariosBloqueados) {
		this.listaUsuariosBloqueados = listaUsuariosBloqueados;
	}

	/**
	 * guardar unn usuario
	 * 
	 * @return
	 */
	public String doGuardarUsuario() {
		Usuario u = new Usuario();
		Persona p = new Persona();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"El Usuario " + " <" + newUsuario.getNombreUsuario() + ">" + " ya a sido registrado", "registrado");
		try {
			p.setApellidos(newPersona.getApellidos());
			p.setCedula(newPersona.getCedula());
			p.setCorreo(newPersona.getCorreo());
			p.setDireccion(newPersona.getDireccion());
			p.setFechaNacimiento(newPersona.getFechaNacimiento());
			p.setNombres(newPersona.getNombres());
			p.setTefefono(newPersona.getTefefono());
			if (usuarioON.buscarUserNombre(newUsuario.getNombreUsuario()) == null) {
				u.setNombreUsuario(newUsuario.getNombreUsuario());
				u.setContrasenia(newUsuario.getContrasenia());
				u.setEstado("ACTIVO");
				u.setIntentosLogin(3);
				u.setRol(newUsuario.getRol());
				u.setPersona(p);
				personaON.registrarPersona(p);
				usuarioON.registrarUsuario(u);
				return "vistaAdministrador?faces-redirect=true";
			}
			System.out.println("Usuario no Registrado");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;

		} catch (Exception e) {
			e.getStackTrace();
			return null;
		}

	}

	/**
	 * buscar un usuario
	 * 
	 * @return
	 */
	public String doBuscarUsuario() {
		Usuario u = new Usuario();
		try {
			u = usuarioON.buscarUserNombre(newUsuario.getNombreUsuario());
			if (u == null) {
				System.out.println("usuario no encontrado para editar");
				return null;
			} else {
				newUsuario.setNombreUsuario(u.getNombreUsuario());
				newUsuario.setContrasenia(u.getContrasenia());
				newUsuario.setRol(u.getRol());
				newUsuario.setEstado(u.getEstado());
				newUsuario.setIntentosLogin(u.getIntentosLogin());
				newUsuario.setRol(u.getRol());
				newUsuario.setPersona(u.getPersona());
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * actualiza un usuario
	 * 
	 * @return
	 */
	public String doActualizarUsuario() {
		Usuario u = new Usuario();
		try {
			u = usuarioON.buscarUserNombre(newUsuario.getNombreUsuario());
			u.setNombreUsuario(newUsuario.getNombreUsuario());
			u.setContrasenia(newUsuario.getContrasenia());
			u.setRol(u.getRol());
			u.setEstado(u.getEstado());
			u.setIntentosLogin(u.getIntentosLogin());
			u.setPersona(u.getPersona());
			usuarioON.actualizarUsuario(u);
			System.out.println("Usuario actualizado");
			return "buscarUsuarios?faces-redirect=true";
		} catch (Exception e) {
			System.out.println("Error al actualizar");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * eliminar un usuario
	 * 
	 * @return
	 */
	public String doEliminarUsuario() {
		try {
			usuarioON.eliminarUsuario(newUsuario.getNombreUsuario());
			System.out.println("Usuario eliminado");
			return "eliminarUsuarios?faces-redirect=true";
		} catch (Exception e) {
			System.out.println("Error al aliminar");
			e.printStackTrace();
			return null;
		}
	}

	public void reloadUsuarios() {
		listaUsuarios = usuarioON.getListUsuarios();
	}

	public void reloadUsuariosBloq() {
		listaUsuariosBloqueados = usuarioON.getListUsuariosBloq();
	}

	/**
	 * desbloquear un usuario
	 * 
	 * @param nombreUsuario
	 * @return
	 */
	public String desbloquearUsuario(String nombreUsuario) {
		Usuario u = new Usuario();
		try {
			u = usuarioON.buscarUsuario(nombreUsuario);
			u.setEstado("ACTIVO");
			u.setIntentosLogin(3);
			System.out.println("suario " + u.getNombreUsuario());
			usuarioON.actualizarUsuario(u);
			return "listarUsuarios?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
