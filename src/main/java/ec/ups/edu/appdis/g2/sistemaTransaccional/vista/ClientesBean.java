package ec.ups.edu.appdis.g2.sistemaTransaccional.vista;

import java.io.Serializable;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Persona;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Usuario;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionCuentaON;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionPersonaON;

@Named
@RequestScoped
public class ClientesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Cuenta> listaCuentas;

	private Usuario newUsuario;
	
	private Persona newPersona;

	@Inject
	private GestionCuentaON cuantaON;

	@Inject
	private GestionPersonaON personaON;

	private LoginBean loginBean;

	public List<Cuenta> getListaCuentas() {
		return listaCuentas;
	}

	public void setListaCuentas(List<Cuenta> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public Usuario getNewUsuario() {
		return newUsuario;
	}

	public void setNewUsuario(Usuario newUsuario) {
		this.newUsuario = newUsuario;
	}

	public Persona getNewPersona() {
		return newPersona;
	}

	public void setNewPersona(Persona newPersona) {
		this.newPersona = newPersona;
	}

	@PostConstruct
	public void init() {
		newUsuario = new Usuario();
		newPersona = new Persona(); 
	}

	public String reloadCuentas() {
		Persona p = new Persona();
		try {
			p = personaON.buscarPorCed(newPersona.getCedula());
			System.out.println("persona encontrarda " +p.getNombres());
			listaCuentas = cuantaON.listaCuentas(p.getId());
			return "";

		} catch (Exception e) {
			System.out.println("Error al listar" + e.getMessage());
			e.printStackTrace();
			return null;

		}

	}
}
