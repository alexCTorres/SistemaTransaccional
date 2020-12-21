package ec.ups.edu.appdis.g2.sistemaTransaccional.vista;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Persona;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.TipoCuenta;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Usuario;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionCorreoElectronico;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionCuentaON;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionPersonaON;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionTipoCuentaON;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionUsuarioON;

@Named
@RequestScoped
public class CuentasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cuenta newCuenta;

	private Persona newPersona;

	private TipoCuenta newTipoCuenta;

	private Usuario newUsuario;

	@Inject
	private GestionCuentaON cuentaON;

	@Inject
	private GestionPersonaON personaON;

	@Inject
	private GestionTipoCuentaON tipoCuentON;

	@Inject
	private GestionCorreoElectronico envioCorreoON;

	@Inject
	private GestionUsuarioON usuarioON;

	@PostConstruct
	public void init() {
		newCuenta = new Cuenta();
		newPersona = new Persona();
		newTipoCuenta = new TipoCuenta();
		newUsuario = new Usuario();
	}

	public Cuenta getNewCuenta() {
		return newCuenta;
	}

	public void setNewCuenta(Cuenta newCuenta) {
		this.newCuenta = newCuenta;
	}

	public Persona getNewPersona() {
		return newPersona;
	}

	public void setNewPersona(Persona newPersona) {
		this.newPersona = newPersona;
	}

	public TipoCuenta getNewTipoCuenta() {
		return newTipoCuenta;
	}

	public void setNewTipoCuenta(TipoCuenta newTipoCuenta) {
		this.newTipoCuenta = newTipoCuenta;
	}

	public String doGuardarCuenta() {
		Cuenta c = new Cuenta();
		Usuario u = new Usuario();
		try {
			if (newPersona.getNombres().equals("") || newPersona.getApellidos().equals("")
					|| newPersona.getCedula().equals("") || newPersona.getCorreo().equals("")
					|| newPersona.getDireccion().equals("") || newPersona.getFechaNacimiento().equals("")
					|| newPersona.getTefefono().equals("")) {
				System.out.println("Rellene todos los datos");
				return null;
			} else {
				c.setNumeroCuenta(generarSerie());
				c.setEstado("ACTIVO");
				c.setFechaApertura(new Date());
				c.setSaldo(newCuenta.getSaldo());
				personaON.registrarPersona(newPersona);
				c.setPersona(newPersona);
				c.setTipoCuenta(tipoCuentON.buscarPoNombre(newTipoCuenta.getTipoCuenta()));
				System.out.println("cuenta creada");
				cuentaON.registrarCuenta(c);
				u.setNombreUsuario(generarNombreUsuario(newPersona.getNombres()));
				u.setContrasenia(generarSerie());
				u.setEstado("ACTIVO");
				u.setIntentosLogin(3);
				u.setRol("CLIENTE");
				u.setPersona(newPersona);
				usuarioON.registrarUsuario(u);
				envioCorreoON.envioMailRegistroCuenta(u, c);
				return "listarCuentas?faces-redirect=true";
			}
		} catch (Exception e) {
			System.out.println("Error al guardar cuenta");
			e.getStackTrace();
		}
		return null;
	}

	public String doBuscarPersona() {
		Persona p = new Persona();
		try {
			p = personaON.buscarPorCed(newPersona.getCedula());
			if (p == null) {
				System.out.println("Persona no encontrada");
				return null;
			} else {
				System.out.println("Persona encontrada " + p.getNombres());
				newPersona.setNombres(p.getNombres());
				newPersona.setApellidos(p.getApellidos());
				newPersona.setCedula(p.getCedula());
				newPersona.setCorreo(p.getCorreo());
				newPersona.setFechaNacimiento(p.getFechaNacimiento());
				newPersona.setDireccion(p.getDireccion());
				newPersona.setTefefono(p.getTefefono());
				return "";
			}
		} catch (Exception e) {
			return null;
		}
	}

	public String doAgregarCuenta() {
		Cuenta c = new Cuenta();
		Persona p = new Persona();
		try {
			c.setNumeroCuenta(generarSerie());
			c.setEstado("ACTIVO");
			c.setFechaApertura(new Date());
			c.setSaldo(newCuenta.getSaldo());
			p = personaON.buscarPorCed(newPersona.getCedula());
			c.setPersona(p);
			c.setTipoCuenta(tipoCuentON.buscarPoNombre(newTipoCuenta.getTipoCuenta()));
			cuentaON.registrarCuenta(c);
			envioCorreoON.envioMailAgregarCuenta(p, c);
			return "listarCuentas?faces-redirect=true";
		} catch (Exception e) {
			System.out.println("Error al agregar cuenta");
			e.getStackTrace();
			return null;
		}
	}

	public String generarSerie() {
		int n = (int) (Math.random() * (1000 - 1)) + 1;
		int res = n * 10000000;
		res = Math.abs(res);
		System.out.println("numero generado " + res);
		if (res >= 1000000000) {
			return String.valueOf("00" + res);
		}
		if (res >= 100000000 && res < 1000000000) {
			return String.valueOf("000" + res);
		}
		if (res >= 10000000 && res < 100000000) {
			return String.valueOf("0000" + res);
		}
		if (res >= 1000000 && res < 10000000) {
			return String.valueOf("00000" + res);
		}
		if (res >= 100000 && res < 1000000) {
			return String.valueOf("000000" + res);
		}
		if (res >= 10000 && res < 100000) {
			return String.valueOf("0000000" + res);
		}
		if (res >= 1000 && res < 10000) {
			return String.valueOf("00000000" + res);
		}
		if (res >= 100 && res < 1000) {
			return String.valueOf("000000000" + res);
		}
		if (res >= 10 && res < 100) {
			return String.valueOf("0000000000" + res);
		}
		if (res >= 1 && res < 10) {
			return String.valueOf("00000000000" + res);
		} else {
			System.out.println("numero cuenta no encontrado");
			return null;
		}
	}

	public String generarNombreUsuario(String nombre) {
		int n = (int) (Math.random() * (100 - 1)) + 1;
		int res = n * 100;

		return nombre + res;
	}
	
}
