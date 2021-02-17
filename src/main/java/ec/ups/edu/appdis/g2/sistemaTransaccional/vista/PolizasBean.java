package ec.ups.edu.appdis.g2.sistemaTransaccional.vista;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Poliza;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.TasaInteres;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionCuentaON;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionPolizaON;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionTasaInteresON;

@Named
@RequestScoped
public class PolizasBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Poliza newPoliza;
	private double capitalSIm;
	private int plazoSim;
	private String pagoSim;
	private String fechaSim;
	private double interesSim;
	private List<TasaInteres> listaTasaInteres;
	private Poliza buscaPoliza;
	private String imagenCedula;

	@Inject
	private GestionPolizaON polizaON;
	
	@Inject
	private GestionCuentaON cuentaON;

	private List<Poliza> listaPoliza;
	
	private List<Poliza> listaPolizaSolicitadas;

	private List<TasaInteres> listaTasa;

	@Inject
	private GestionTasaInteresON tasaON;

	@PostConstruct
	public void init() {
		newPoliza = new Poliza();
		listarTasaInteres();
		polizasSolicitadas();
	}

	public Poliza getNewPoliza() {
		return newPoliza;
	}

	public void setNewPoliza(Poliza newPoliza) {
		this.newPoliza = newPoliza;
	}

	public List<Poliza> getListaPoliza() {
		return listaPoliza;
	}

	public void setListaPoliza(List<Poliza> listaPoliza) {
		this.listaPoliza = listaPoliza;
	}

	public List<TasaInteres> getListaTasa() {
		return listaTasa;
	}

	public void setListaTasa(List<TasaInteres> listaTasa) {
		this.listaTasa = listaTasa;
	}
	
	public Poliza getBuscaPoliza() {
		return buscaPoliza;
	}

	public void setBuscaPoliza(Poliza buscaPoliza) {
		this.buscaPoliza = buscaPoliza;
	}
	
	public double getCapitalSIm() {
		return capitalSIm;
	}

	public void setCapitalSIm(double capitalSIm) {
		this.capitalSIm = capitalSIm;
	}

	public int getPlazoSim() {
		return plazoSim;
	}

	public void setPlazoSim(int plazoSim) {
		this.plazoSim = plazoSim;
	}

	public String getPagoSim() {
		return pagoSim;
	}

	public void setPagoSim(String pagoSim) {
		this.pagoSim = pagoSim;
	}

	public String getFechaSim() {
		return fechaSim;
	}

	public void setFechaSim(String fechaSim) {
		this.fechaSim = fechaSim;
	}

	public double getInteresSim() {
		return interesSim;
	}

	public void setInteresSim(double interesSim) {
		this.interesSim = interesSim;
	}


	public List<TasaInteres> getListaTasaInteres() {
		return listaTasaInteres;
	}

	public void setListaTasaInteres(List<TasaInteres> listaTasaInteres) {
		this.listaTasaInteres = listaTasaInteres;
	}
	
	

	public String getImagenCedula() {
		return imagenCedula;
	}

	public void setImagenCedula(String imagenCedula) {
		this.imagenCedula = imagenCedula;
	}
	
	
	
	public List<Poliza> getListaPolizaSolicitadas() {
		return listaPolizaSolicitadas;
	}

	public void setListaPolizaSolicitadas(List<Poliza> listaPolizaSolicitadas) {
		this.listaPolizaSolicitadas = listaPolizaSolicitadas;
	}

	public void calcularPoliza() {
		Calendar calendar = Calendar.getInstance();
			Poliza pol = new Poliza();
			pol.setId(1);
			pol.setCapital(newPoliza.getCapital());
			pol.setEstado("SIMULADO");
			pol.setPlazo(newPoliza.getPlazo());
			pol.setFrecuenciaPago(newPoliza.getFrecuenciaPago());
			calendar.add(Calendar.DATE, newPoliza.getPlazo());
			int mes = (calendar.get(Calendar.MONTH)+1);
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
			
			capitalSIm = pol.getCapital();
			plazoSim = pol.getPlazo();
			pagoSim = pol.getFrecuenciaPago();
			fechaSim = pol.getFechaVencimiento();
			interesSim = pol.getInteres();	
	}
	public void listarTasaInteres() {
		listaTasaInteres = tasaON.getTasaInteres();
	}
	
	public void solicitarPoliza() {
		Calendar calendar = Calendar.getInstance();
		try {
			Poliza pol = new Poliza();
			pol.setCapital(newPoliza.getCapital());
			pol.setEstado("SOLITADO");
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
			polizaON.registrarPoliza(pol);
			capitalSIm = pol.getCapital();
			plazoSim = pol.getPlazo();
			pagoSim = pol.getFrecuenciaPago();
			fechaSim = pol.getFechaVencimiento();
			interesSim = pol.getInteres();	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void polizasSolicitadas() {
		listaPolizaSolicitadas = polizaON.listarPplozasSolicitadas();
	}
	
	public void aprobarPoliza(int id, double valor, String numCuenta) {
		Poliza pol = new Poliza();
		Cuenta cue = new Cuenta();
		try {
			pol = polizaON.buscarPoliza(id);
			cue = cuentaON.buscarCuenta(numCuenta);
			if(pol.getCapital()>cue.getSaldo()) {
				System.out.println("No se puede realizar una poliza");
			}else {
			pol.setEstado("ACTIVO");
			polizaON.actualizarPoliza(pol);
			cue.setSaldo(cue.getSaldo()-pol.getCapital());
			cuentaON.actualizarCuenta(cue);
			polizasSolicitadas();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void negarPoliza(int id) {
		Poliza pol = new Poliza();
		try {
			pol = polizaON.buscarPoliza(id);
			pol.setEstado("DENEGADO");
			polizaON.actualizarPoliza(pol);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
