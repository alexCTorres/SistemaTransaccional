package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import javax.ejb.Remote;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Movimientos;

@Remote
public interface GestionMovimientosOnRemoto {

	public boolean registrarMoviemientos(Movimientos movimientos) throws Exception;
	public boolean actualizarMovimientos(Movimientos movimientos) throws Exception;
	public Movimientos buscarCuenta(int id) throws Exception;
	public boolean eliminarMovimientos(int id) throws Exception;
}
