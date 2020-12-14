package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import javax.ejb.Remote;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Poliza;

@Remote
public interface GestionPolizaOnRemoto {

	public boolean registrarPoliza(Poliza poliza) throws Exception;
	public boolean actualizarPoliza(Poliza poliza) throws Exception;
	public Poliza buscarPoliza(int id) throws Exception;
	public boolean eliminarPoliza(int id) throws Exception;
	public double calcularInteres(double capital, double tasaInteres, int plazoPoliza);
}
