package ec.ups.edu.appdis.g2.sistemaTransaccional.vista;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Persona;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionPersonaON;

@Named
@RequestScoped
public class PersonasBean {

	private Persona newPersona;
	
	@Inject
	private GestionPersonaON gestionPersona;

	@PostConstruct
	public void init() {
		newPersona = new Persona();
	}
	
	public Persona getNewPersona() {
		return newPersona;
	}

	public void setNewPersona(Persona newPersona) {
		this.newPersona = newPersona;
	}

	public String doSaluda() {
		System.out.println("Hola " + newPersona);
		return null;
	}
}
