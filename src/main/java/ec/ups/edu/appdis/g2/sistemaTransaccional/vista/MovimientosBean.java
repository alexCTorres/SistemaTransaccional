package ec.ups.edu.appdis.g2.sistemaTransaccional.vista;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Movimientos;

@Named
@RequestScoped
public class MovimientosBean {
	
	private Movimientos newMovimiento;

	@PostConstruct
	public void init() {
		newMovimiento = new Movimientos();
	}

	public Movimientos getNewMovimiento() {
		return newMovimiento;
	}

	public void setNewMovimiento(Movimientos newMovimiento) {
		this.newMovimiento = newMovimiento;
	}

}
