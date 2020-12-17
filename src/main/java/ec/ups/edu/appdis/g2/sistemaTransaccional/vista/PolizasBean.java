package ec.ups.edu.appdis.g2.sistemaTransaccional.vista;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Poliza;

@Named
@RequestScoped
public class PolizasBean {
	
	private Poliza newPoliza;
	
	@PostConstruct
	public void init() {
		newPoliza = new Poliza();
	}

	public Poliza getNewPoliza() {
		return newPoliza;
	}

	public void setNewPoliza(Poliza newPoliza) {
		this.newPoliza = newPoliza;
	}

	
}
