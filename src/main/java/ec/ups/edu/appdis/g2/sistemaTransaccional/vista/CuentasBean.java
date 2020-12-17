package ec.ups.edu.appdis.g2.sistemaTransaccional.vista;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Cuenta;

@Named
@RequestScoped
public class CuentasBean {

	private Cuenta newCuenta;

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


}
