package ec.ups.edu.appdis.g2.sistemaTransaccional.vista;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Movimientos;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Persona;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.TipoCuenta;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionCuentaON;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionMovimientosON;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionPersonaON;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionTipoCuentaON;

@Named
@RequestScoped
public class MovimientosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Movimientos newMovimiento;

	private Cuenta newCuenta;

	private Persona newPersona;

	private TipoCuenta newTipoCuenta;
	
	private List<Movimientos> listaMoviemientos;

	@Inject
	private GestionCuentaON cuentaON;
	@Inject
	private GestionPersonaON personaON;
	@Inject
	private GestionMovimientosON movimientisON;
	@Inject
	private GestionTipoCuentaON tipoCuentaON;

	@PostConstruct
	public void init() {
		newMovimiento = new Movimientos();
		newCuenta = new Cuenta();
		newPersona = new Persona();
		newTipoCuenta = new TipoCuenta();
		reloadMovimientos();
	}

	public Movimientos getNewMovimiento() {
		return newMovimiento;
	}

	public void setNewMovimiento(Movimientos newMovimiento) {
		this.newMovimiento = newMovimiento;
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

	public List<Movimientos> getListaMoviemientos() {
		return listaMoviemientos;
	}

	public void setListaMoviemientos(List<Movimientos> listaMoviemientos) {
		this.listaMoviemientos = listaMoviemientos;
	}

	public String doBuscarCuenta() {
		Cuenta c = new Cuenta();
		try {
			c = cuentaON.buscarCuenta(newCuenta.getNumeroCuenta());
			if (c == null) {
				System.out.println("Numero de cuenta no encontrado");
				return null;
			} else {
				System.out.println("Cuenta encontrada " + c.getNumeroCuenta());
				newPersona.setNombres(c.getPersona().getNombres());
				newPersona.setApellidos(c.getPersona().getApellidos());
				newPersona.setCedula(c.getPersona().getCedula());
				newPersona.setCorreo(c.getPersona().getCorreo());
				newPersona.setFechaNacimiento(c.getPersona().getFechaNacimiento());
				newPersona.setDireccion(c.getPersona().getDireccion());
				newPersona.setTefefono(c.getPersona().getTefefono());
				newCuenta.setNumeroCuenta(c.getNumeroCuenta());
				newCuenta.setEstado(c.getEstado());
				newCuenta.setSaldo(c.getSaldo());
				newTipoCuenta.setTipoCuenta(c.getTipoCuenta().getTipoCuenta());
				return "";
			}
		} catch (Exception e) {
			System.out.println("Error al buscar Cuenta");
			e.printStackTrace();
			return null;
		}
	}

	public String guardarMovimiento() {
		Cuenta c = new Cuenta();
		Movimientos m = new Movimientos();
		double saldoActual=0;
		double monto = newMovimiento.getMonto();
		double saldoNuevo = 0;
		try {
			m.setCuentaSale(newCuenta.getNumeroCuenta());
			m.setFecha(new Date());
			m.setTipo(newMovimiento.getTipo());
			m.setCuenta(newCuenta);
			m.setMonto(monto);
			c = cuentaON.buscarCuenta(newCuenta.getNumeroCuenta());
			saldoActual = c.getSaldo();
			c.setNumeroCuenta(c.getNumeroCuenta());
			c.setEstado(c.getEstado());
			c.setPersona(c.getPersona());
			c.setTipoCuenta(c.getTipoCuenta());
			c.setFechaApertura(c.getFechaApertura());
		if(monto>0 && monto<=saldoActual && newMovimiento.getTipo().equals("RETIRO")) {
			saldoNuevo = saldoActual - monto;
			System.out.println("retiro-nuevo saldo  " +saldoNuevo);
			movimientisON.registrarMoviemientos(m);
			c.setSaldo(saldoNuevo);
			cuentaON.actualizarCuenta(c);
			return "listarMovimientos?faces-redirect=true";
		}else if(monto>0 && newMovimiento.getTipo().equals("DEPÓSITO")) {
			saldoNuevo = saldoActual + monto;
			System.out.println("deposito-nuevo saldo  " +saldoNuevo);
			movimientisON.registrarMoviemientos(m);
			c.setSaldo(saldoNuevo);
			cuentaON.actualizarCuenta(c);
			return "listarMovimientos?faces-redirect=true";
		}else {
			System.out.println("Movimiento no registrado no cumple cumple resticciones");
			return null;
		}
			
		}catch (Exception e) {
			System.out.println("Error al crear movimiento");
			e.printStackTrace();
			return null;
		}	
		}
	
	public void reloadMovimientos() {
		listaMoviemientos = movimientisON.getMovimientis();
	}
	}

