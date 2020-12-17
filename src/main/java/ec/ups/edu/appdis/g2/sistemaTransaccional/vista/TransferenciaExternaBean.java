package ec.ups.edu.appdis.g2.sistemaTransaccional.vista;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.TransferenciasExternas;

@Named
@RequestScoped
public class TransferenciaExternaBean {

	private TransferenciasExternas newTransefrenciaEx;

	@PostConstruct
	public void init() {
		newTransefrenciaEx = new TransferenciasExternas();
	}
	
	public TransferenciasExternas getNewTransefrenciaEx() {
		return newTransefrenciaEx;
	}

	public void setNewTransefrenciaEx(TransferenciasExternas newTransefrenciaEx) {
		this.newTransefrenciaEx = newTransefrenciaEx;
	}
	
	
}
