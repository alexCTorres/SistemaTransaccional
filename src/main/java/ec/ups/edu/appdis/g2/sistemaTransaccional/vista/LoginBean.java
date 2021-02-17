package ec.ups.edu.appdis.g2.sistemaTransaccional.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Movimientos;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Persona;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Poliza;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Registro;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.TasaInteres;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Usuario;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionCorreoElectronico;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionCuentaON;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionMovimientosON;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionPersonaON;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionPolizaON;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionRegistroON;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionTasaInteresON;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionUsuarioON;

@Named
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario newUsuario;
	private List<Cuenta> listaCuentas;
	private Persona newPersona;
	private List<Poliza> listaPolizas;
	private List<Movimientos> listaMovimientos;
	private List<TasaInteres> listaTasa;
	private Poliza newPoliza;
	private Poliza resultPoliza;
	
	@Inject
	private GestionTasaInteresON tasaON;

	@Inject
	private GestionUsuarioON usuarioON;

	@Inject
	private GestionRegistroON registroON;

	@Inject
	private GestionCorreoElectronico envioCorreoON;

	@Inject
	private GestionPersonaON personaON;

	@Inject
	private GestionCuentaON cuantaON;
	
	@Inject
	private GestionMovimientosON movimientosON;
	
	@Inject
	private GestionPolizaON polizaON;
	
	private List<Registro> listaRegistros;
	
	private String numeroCuenta;
	

	@PostConstruct
	public void init() {
	newUsuario = new Usuario();
	newPoliza = new Poliza();
	resultPoliza = new Poliza();
	}
	
   
	public Poliza getResultPoliza() {
		return resultPoliza;
	}

	public void setResultPoliza(Poliza resultPoliza) {
		this.resultPoliza = resultPoliza;
	}

	public List<TasaInteres> getListaTasa() {
		return listaTasa;
	}


	public void setListaTasa(List<TasaInteres> listaTasa) {
		this.listaTasa = listaTasa;
	}


	public Usuario getNewUsuario() {
		return newUsuario;
	}

	public void setNewUsuario(Usuario newUsuario) {
		this.newUsuario = newUsuario;
	}
	
	
	public List<Cuenta> getListaCuentas() {
		return listaCuentas;
	}

	public void setListaCuentas(List<Cuenta> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

	public Persona getNewPersona() {
		return newPersona;
	}

	public void setNewPersona(Persona newPersona) {
		this.newPersona = newPersona;
	}

	public List<Registro> getListaRegistros() {
		return listaRegistros;
	}

	public void setListaRegistros(List<Registro> listaRegistros) {
		this.listaRegistros = listaRegistros;
	}
	
	public List<Poliza> getListaPolizas() {
		return listaPolizas;
	}

	public void setListaPolizas(List<Poliza> listaPolizas) {
		this.listaPolizas = listaPolizas;
	}

	public List<Movimientos> getListaMovimientos() {
		return listaMovimientos;
	}

	public void setListaMovimientos(List<Movimientos> listaMovimientos) {
		this.listaMovimientos = listaMovimientos;
	}
	public String getNumCuenta() {
		return numeroCuenta;
	}

	public void setNumCuenta(String numCuenta) {
		this.numeroCuenta = numCuenta;
	}
	public Poliza getNewPoliza() {
		return newPoliza;
	}

	public void setNewPoliza(Poliza newPoliza) {
		this.newPoliza = newPoliza;
	}

	public String doIniciarSesion() {
		Registro reg = new Registro();
		Usuario u = new Usuario();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"El Usuario " + " <" + newUsuario.getNombreUsuario() + ">" + " esta Bloqueado a fallado 3 intentos.",
				"Usuario o contrasena incorrecto");
		FacesMessage msgI = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario o contrasena incorrecto",
				"Usuario Inactivo");
		try {
			u = usuarioON.buscarUsuario(newUsuario.getNombreUsuario());
			if (u.getContrasenia().equals(newUsuario.getContrasenia()) && u.getIntentosLogin() > 0) {
				reg.setFechaIngreso(new Date());
				reg.setDescripcion("Ingreso Válido");
				reg.setUsuario(u);
				u.setIntentosLogin(3);
				usuarioON.actualizarUsuario(u);
				registroON.registrarRegistro(reg);
				if (u.getRol().equals("ADMINISTRATIVO")) {
				    //envioCorreoON.envioMailIngresoValido(u);
					return "vistaAdministrador?faces-redirect=true";
				} else if (u.getRol().equals("CAJERO")) {
					//envioCorreoON.envioMailIngresoValido(u);
					return "vistaCajero?faces-redirect=true";
				} else if (u.getRol().equals("ASISTENTE DE CAPTACIONES")) {
					//envioCorreoON.envioMailIngresoValido(u);
					return "vistaAsistenteCaptaciones?faces-redirect=true";
				} else {
				    //envioCorreoON.envioMailIngresoValido(u);
					listaCuentas =  cuantaON.listaCuentas(u.getPersona().getId());
					listaRegistros = registroON.listarRegistros(u.getNombreUsuario());
					listaPolizas = polizaON.listarPorPersona(u.getPersona().getId());
					return "templateCliente?faces-redirect=true";
				}
			} else {
				reg.setFechaIngreso(new Date());
				reg.setDescripcion("Ingreso Inválido");
				reg.setUsuario(u);
				if (u.getIntentosLogin() == 0) {
					u.setEstado("INACTIVO");
					usuarioON.actualizarUsuario(u);
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return null;
				} else {
					u.setIntentosLogin(u.getIntentosLogin() - 1);
					usuarioON.actualizarUsuario(u);
					registroON.registrarRegistro(reg);
					FacesContext.getCurrentInstance().addMessage(null, msgI);
					//envioCorreoON.envioMailIngresoInValido(u);
					return null;
				}
			}
		} catch (Exception e) {
			System.out.println("Error al iniciar sesion " + e.getMessage() + "localize" + e.getLocalizedMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgI);
			e.printStackTrace();
			return null;
		}
	}

	public String cerrarSesion() {
		HttpSession sesion = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		sesion.invalidate();
		return "login?faces-redirect=true";
	}
	
	public String llamarVerMovimientos(String cuenta) {
		System.out.println("entro al metodoooo num cuenta " +cuenta);
		listaMovimientos = movimientosON.getMovimientosCuenta(cuenta);
		System.out.println("movimientos" +listaMovimientos.toString());
		byte f = 0;
		return "movimientosCuenta?faces-redirect=true";
	}
	
	public String SolicitarPoliza(String numCuenta) {
		numeroCuenta = numCuenta;
		return "solicitarPoliza?faces-redirect=true";
	}
	
	public void solicitar() {
		Calendar calendar = Calendar.getInstance();
		Cuenta cuent = new Cuenta();
		try {
			Poliza pol = new Poliza();
			pol.setCapital(newPoliza.getCapital());
			pol.setEstado("SOLICITADO");
			pol.setPlazo(newPoliza.getPlazo());
			pol.setFrecuenciaPago(newPoliza.getFrecuenciaPago());
			calendar.add(Calendar.DATE, newPoliza.getPlazo());
			int mes = (calendar.get(Calendar.MONTH)+1);
			int dia = calendar.get(Calendar.DATE);
			pol.setFechaVencimiento("" +calendar.get(Calendar.DATE) +"/" +mes +"/" +calendar.get(Calendar.YEAR));
			double capital = newPoliza.getCapital();
			int plazo = newPoliza.getPlazo();
			double tasa = 0;
			listaTasa = tasaON.getTasaInteres();
			for (TasaInteres tInt : listaTasa) {
				if (plazo >= tInt.getDiaDesde() && plazo <= tInt.getDiaHasta()) {
					tasa = tInt.getTasaInteres();
					break;
				}
			}
			double interes = capital * (tasa / 36000) * plazo;
			pol.setInteres(interes);
			pol.setDiaPago(dia);
			byte[] foto = {0,0,0,0,0,0,0,0};
			pol.setFotoCedula(foto);
			pol.setFotoServBasico(foto);
			cuent = cuantaON.buscarCuenta(numeroCuenta);
			pol.setCuenta(cuent);
			polizaON.registrarPoliza(pol);
			resultPoliza.setCapital(pol.getCapital());
			resultPoliza.setPlazo(pol.getPlazo());
			resultPoliza.setFrecuenciaPago(pol.getFrecuenciaPago());
			resultPoliza.setFechaVencimiento(pol.getFechaVencimiento());
			resultPoliza.setInteres(pol.getInteres());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
