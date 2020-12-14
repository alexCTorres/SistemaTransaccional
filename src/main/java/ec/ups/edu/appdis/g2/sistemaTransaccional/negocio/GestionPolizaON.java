package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.appdis.g2.sistemaTransaccional.dao.PolizaDAO;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Poliza;

@Stateless
public class GestionPolizaON {

	@Inject
	private PolizaDAO daoPoliza;
	
	public boolean registrarPoliza(Poliza poliza) {
		return true;
	}
	
	public boolean actualizarPoliza(Poliza poliza) {
		return true;
	}
	
	public Poliza buscarPoliza(int id) {
		Poliza p = new Poliza();
		try {
			p = daoPoliza.readJPA(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	
	public boolean eliminarPoliza(int id) {
		return true;
	}
	
	public double calcularInteres(double capital, double tasaInteres, int plazoPoliza) {
		return 0;
	}
}
