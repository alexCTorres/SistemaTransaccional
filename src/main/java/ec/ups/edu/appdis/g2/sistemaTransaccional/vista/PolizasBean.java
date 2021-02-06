package ec.ups.edu.appdis.g2.sistemaTransaccional.vista;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Poliza;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.TasaInteres;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionPolizaON;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionTasaInteresON;

@Named
@RequestScoped
public class PolizasBean {
	
	private Poliza newPoliza;
	private TasaInteres newTasaInteres;
	
	@Inject
	private GestionPolizaON polizaON;
	
	@Inject
	private GestionTasaInteresON tasaON;
	
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

	
	public void calcularPoliza() {
		if(newPoliza.getCapital()==0.0||newPoliza.getDiaPago()==0||newPoliza.getFrecuenciaPago().equals("")) {
			
		}else {
			
		}
	}
	
}
