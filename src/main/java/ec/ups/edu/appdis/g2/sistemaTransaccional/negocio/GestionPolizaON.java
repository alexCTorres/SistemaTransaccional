package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.appdis.g2.sistemaTransaccional.dao.PolizaDAO;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Poliza;

@Stateless
public class GestionPolizaON {

	@Inject
	private PolizaDAO daoPoliza;
	
	/**
	 * 
	 * @param poliza
	 * @return
	 * @throws Exception
	 */
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
	
	/**
	 * 
	 * @param poliza
	 * @return
	 * @throws Exception
	 */
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
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
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
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
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
	
	/**
	 * 
	 * @param capital
	 * @param tasaInteres
	 * @param plazoPoliza
	 * @return
	 */
	public double calcularInteres(double capital, double tasaInteres, int plazoPoliza) {
		double tasa= tasaInteres/36000;
		double interes = capital*tasa*plazoPoliza;
		return interes;
	}
	
	/**
	 * 
	 * @param numCuenta
	 * @return
	 */
	public List<Poliza> listarPorNumCuenta(String numCuenta){
		return daoPoliza.listaPolizas(numCuenta);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<Poliza> listarPorPersona(int id){
		return daoPoliza.listaPolizasPorPersona(id);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Poliza> listarPplozasSolicitadas(){
		return daoPoliza.listaPolizasSolicitadas();
	}
}
