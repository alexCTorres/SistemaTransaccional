package ec.ups.edu.appdis.g2.sistemaTransaccional.vista;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.TasaInteres;

@Named
@RequestScoped
public class TasaInteresBean {

	private TasaInteres newTasaInteres;
	
	@PostConstruct
	public void init() {
		newTasaInteres = new TasaInteres();
	}

	public TasaInteres getNewTasaInteres() {
		return newTasaInteres;
	}

	public void setNewTasaInteres(TasaInteres newTasaInteres) {
		this.newTasaInteres = newTasaInteres;
	}
	
	
}
