package ec.ups.edu.appdis.g2.sistemaTransaccional.vista;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Registro;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Usuario;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionRegistroON;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionUsuarioON;

@Named
@RequestScoped
public class RegistroBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Registro newRegistro;
	
	private Usuario newUsuario;
	
	//String usuario;
	
	@Inject
	private GestionUsuarioON usuarioON;
	
	@Inject
	private LoginBean beanLogin;
	
	@Inject
	private GestionRegistroON registroON;
	
	private List<Registro> listaRegistros;
	
	@PostConstruct
	public void init() {
		//newUsuario = usuarioON.buscarUsuario();
	//	newRegistro = new Registro();
		//listarRegistros();
	}

	public Registro getNewRegistro() {
		return newRegistro;
	}

	public void setNewRegistro(Registro newRegistro) {
		this.newRegistro = newRegistro;
	}

	public List<Registro> getListaRegistros() {
		return listaRegistros;
	}

	public void setListaRegistros(List<Registro> listaRegistros) {
		this.listaRegistros = listaRegistros;
	}
	
	public Usuario getNewUsuario() {
		return newUsuario;
	}

	public void setNewUsuario(Usuario newUsuario) {
		this.newUsuario = newUsuario;
	}

	public void listarRegistros() {
		listaRegistros = registroON.listarRegistros(newRegistro.getUsuario().getNombreUsuario());
	}
	
}
