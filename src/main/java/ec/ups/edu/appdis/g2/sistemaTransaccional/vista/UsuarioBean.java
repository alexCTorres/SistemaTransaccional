package ec.ups.edu.appdis.g2.sistemaTransaccional.vista;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Usuario;

@Named
@RequestScoped
public class UsuarioBean {

	private Usuario newUsuario;
	
	
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
	
	
}
