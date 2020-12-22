package ec.ups.edu.appdis.g2.sistemaTransaccional.vista;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Registro;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Usuario;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionCorreoElectronico;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionRegistroON;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionUsuarioON;

@Named
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario newUsuario;

	@Inject
	private GestionUsuarioON usuarioON;

	@Inject
	private GestionRegistroON registroON;
	
	@Inject
	private GestionCorreoElectronico envioCorreoON;

	@PostConstruct
	public void init() {
		newUsuario = new Usuario();
	}

	public Usuario getNewUsuario() {
		return newUsuario;
	}

	public void setNewUsuario(Usuario newUsuario) {
		this.newUsuario = newUsuario;
	}
	

	public String doIniciarSesion() {
		Registro reg = new Registro();
		Usuario u = new Usuario();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"El Usuario "+ " <"+newUsuario.getNombreUsuario() +">" +" esta Bloqueado a fallado 3 intentos.",
				"Usuario o contrasena incorrecto"	);
		FacesMessage msgI = new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuario o contrasena incorrecto",
				"Usuario Inactivo"	);
		try {
			u = usuarioON.buscarUsuario(newUsuario.getNombreUsuario());
				if(u.getContrasenia().equals(newUsuario.getContrasenia())&&u.getIntentosLogin()>0) {
						reg.setFechaIngreso(new Date());
						reg.setDescripcion("Ingreso Válido");
						reg.setUsuario(u);
						u.setIntentosLogin(3);
						usuarioON.actualizarUsuario(u);
						registroON.registrarRegistro(reg);
						if(u.getRol().equals("ADMINISTRATIVO")) {
							envioCorreoON.envioMailIngresoValido(u);
							return "vistaAdministrador?faces-redirect=true";
						}else if(u.getRol().equals("CAJERO")) {
							envioCorreoON.envioMailIngresoValido(u);
							return "vistaCajero?faces-redirect=true";
						}else if(u.getRol().equals("ASISTENTE DE CAPTACIONES")) {
							envioCorreoON.envioMailIngresoValido(u);
							return "vistaAsistenteCaptaciones?faces-redirect=true";
						}else {
							envioCorreoON.envioMailIngresoValido(u);
							return "vistaUsuariosSistema?faces-redirect=true";
						}			
				}else{
					reg.setFechaIngreso(new Date());
					reg.setDescripcion("Ingreso Inválido");
					reg.setUsuario(u);
					if(u.getIntentosLogin()==0) {
						u.setEstado("INACTIVO");
						usuarioON.actualizarUsuario(u);
						FacesContext.getCurrentInstance().addMessage(null, msg);
						return null;
					}else {
					u.setIntentosLogin(u.getIntentosLogin()-1);
					usuarioON.actualizarUsuario(u);
					registroON.registrarRegistro(reg);
					FacesContext.getCurrentInstance().addMessage(null, msgI);
					envioCorreoON.envioMailIngresoInValido(u);
					return null;
				 }
				}
		}catch (Exception e) {
			System.out.println("Error al iniciar sesion " +e.getMessage() +"localize" + e.getLocalizedMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgI);
			e.printStackTrace();
			return null;
		}
	}

	public String cerrarSesion() {
			HttpSession sesion = (HttpSession) FacesContext.getCurrentInstance()
					.getExternalContext().getSession(false);
			sesion.invalidate();
			return "login?faces-redirect=true";
	}
	
}
