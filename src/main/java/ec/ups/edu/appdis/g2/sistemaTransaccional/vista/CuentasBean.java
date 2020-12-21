package ec.ups.edu.appdis.g2.sistemaTransaccional.vista;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Persona;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionCuentaON;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionPersonaON;

@Named
@RequestScoped
public class CuentasBean {

	private Cuenta newCuenta;
	
	private Persona newPersona;
	
	@Inject
	private GestionCuentaON cuentaON;
	
	@Inject
	private GestionPersonaON personaON;

	@PostConstruct
	public void init() {
		newCuenta = new Cuenta();
	}
	
	public Cuenta getNewCuenta() {
		return newCuenta;
	}

	public void setNewCuenta(Cuenta newCuenta) {
		this.newCuenta = newCuenta;
	}

	public Persona getNewPersona() {
		return newPersona;
	}

	public void setNewPersona(Persona newPersona) {
		this.newPersona = newPersona;
	}

	public String doGuardarCuenta() {
		return null;
	}

}
