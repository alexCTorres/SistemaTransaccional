package ec.ups.edu.appdis.g2.sistemaTransaccional.vista;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Registro;

@Named
@RequestScoped
public class RegistroBean {

	private Registro newRegistro;
	
	@PostConstruct
	public void init() {
		newRegistro = new Registro();
	}

	public Registro getNewRegistro() {
		return newRegistro;
	}

	public void setNewRegistro(Registro newRegistro) {
		this.newRegistro = newRegistro;
	}
	
	
}
