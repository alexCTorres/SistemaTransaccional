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
	
	public boolean registrarPoliza(Poliza poliza) throws Exception {
		try {
			daoPoliza.insertJPA(poliza);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return true;
	}
	
	public boolean actualizarPoliza(Poliza poliza) throws Exception {
		try {
			daoPoliza.updateJPA(poliza);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return true;
	}
	
	public Poliza buscarPoliza(int id) throws Exception {
		Poliza p = new Poliza();
		try {
			p = daoPoliza.readJPA(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return p;
	}
	
	public boolean eliminarPoliza(int id) throws Exception {
		try {
			Poliza p = daoPoliza.readJPA(id);
			if(p==null) {
				System.out.println("Poliza no encotrada");
			}else {
				daoPoliza.deleteJPA(p.getId());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return true;
	}
	
	public double calcularInteres(double capital, double tasaInteres, int plazoPoliza) {
		double tasa= tasaInteres/36000;
		double interes = capital*tasa*plazoPoliza;
		return interes;
	}
}
