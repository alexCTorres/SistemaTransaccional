package ec.ups.edu.appdis.g2.sistemaTransaccional.vista;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Movimientos;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Persona;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Usuario;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionCuentaON;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionMovimientosON;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionPersonaON;

@Named
@RequestScoped
public class ClientesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Cuenta> listaCuentas;
	
	private List<Movimientos> listaMovimientos;

	private Usuario newUsuario;
	
	private Persona newPersona;
	
	private String numCUe;
	
	private Cuenta newCuenta;

	
	@Inject
	private GestionMovimientosON movimientosON;
	
	@Inject
	private GestionCuentaON cuantaON;

	@Inject
	private GestionPersonaON personaON;

	private LoginBean loginBean;
	

	public List<Cuenta> getListaCuentas() {
		return listaCuentas;
	}

	public void setListaCuentas(List<Cuenta> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public Usuario getNewUsuario() {
		return newUsuario;
	}

	public void setNewUsuario(Usuario newUsuario) {
		this.newUsuario = newUsuario;
	}

	public Persona getNewPersona() {
		return newPersona;
	}

	public void setNewPersona(Persona newPersona) {
		this.newPersona = newPersona;
	}
	

	public String getNumCUenta() {
		return numCUe;
	}

	public void setNumCUenta(String numCUe) {
		this.numCUe = numCUe;
	}

	
	public List<Movimientos> getListaMovimientos() {
		return listaMovimientos;
	}

	public void setListaMovimientos(List<Movimientos> listaMovimientos) {
		this.listaMovimientos = listaMovimientos;
	}

	public Cuenta getNewCuenta() {
		return newCuenta;
	}

	public void setNewCuenta(Cuenta newCuenta) {
		this.newCuenta = newCuenta;
	}

	@PostConstruct
	public void init() {
		newUsuario = new Usuario();
		newPersona = new Persona(); 
		newCuenta = new Cuenta();
	}

	/**
	 * listar cuentas
	 */
	public void reloadCuentas() {
		Persona p = new Persona();
		try {
			p = personaON.buscarPorCed(newPersona.getCedula());
			System.out.println("persona encontrarda " +p.getNombres());
			listaCuentas = cuantaON.listaCuentas(p.getId());

		} catch (Exception e) {
			System.out.println("Error al listar" + e.getMessage());
			e.printStackTrace();
	
		}

	}
	
	/**
	 * movimientos de una cuenta
	 * @param cuenta
	 * @return
	 */
	public String llamarVerMovimientos(String cuenta) {
		System.out.println("entro al metodoooo num cuenta " +cuenta);
		newCuenta.setNumeroCuenta(cuenta);
		listaMovimientos = movimientosON.getMovimientosCuenta(cuenta);
	//	System.out.println("movimientos" +listaMovimientos.toString());
		return "movimientosCuenta?faces-redirect=true";
	}
	
	/**
	 * listar movimientos de una cuenta
	 */
	public void listarMovimientosCuenta() {
		//listaMovimientos = movimientosON.getMovimientosCuenta(cuenta);
		System.out.println("entro al metodoooo listaaar num cuenta " +newCuenta.getNumeroCuenta());
		listaMovimientos = movimientosON.getMovimientosCuenta(newCuenta.getNumeroCuenta());
	}
}
