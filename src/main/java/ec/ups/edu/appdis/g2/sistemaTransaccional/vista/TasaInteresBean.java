package ec.ups.edu.appdis.g2.sistemaTransaccional.vista;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.TasaInteres;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.TipoCuenta;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionTasaInteresON;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionTipoCuentaON;

@Named
@RequestScoped
public class TasaInteresBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private TasaInteres newTasaInteres;

	private TipoCuenta newTipoCuenta;

	private int id; // parametro de carga desde lista de interes/editar

	private List<TasaInteres> listaTasaInteres;

	@Inject
	private GestionTasaInteresON tasaInteresON;

	@Inject
	private GestionTipoCuentaON tipoCuentaON;

	@PostConstruct
	public void init() {
		newTasaInteres = new TasaInteres();
		newTipoCuenta = new TipoCuenta();
		listarTasaInteres();
	}

	public TasaInteres getNewTasaInteres() {
		return newTasaInteres;
	}

	public void setNewTasaInteres(TasaInteres newTasaInteres) {
		this.newTasaInteres = newTasaInteres;
	}

	public TipoCuenta getNewTipoCuenta() {
		return newTipoCuenta;
	}

	public void setNewTipoCuenta(TipoCuenta newTipoCuenta) {
		this.newTipoCuenta = newTipoCuenta;
	}

	public List<TasaInteres> getListaTasaInteres() {
		return listaTasaInteres;
	}

	public void setListaTasaInteres(List<TasaInteres> listaTasaInteres) {
		this.listaTasaInteres = listaTasaInteres;
	}

	public void listarTasaInteres() {
		listaTasaInteres = tasaInteresON.getTasaInteres();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * guardar una tasa de interes
	 * @return
	 */
	public String doGuardarTasaInteres() {
		TasaInteres tasa = new TasaInteres();
		TipoCuenta tipoC = new TipoCuenta();
		try {
			tipoC = tipoCuentaON.buscarPoNombre(newTipoCuenta.getTipoCuenta());
			if (tipoC == null) {
				System.out.println("tipo cuenta no neconrado bean");
				return null;
			}
			System.out.println("tipo cuenta encontrad bean " + tipoC.getTipoCuenta());
			tasa.setDiaDesde(newTasaInteres.getDiaDesde());
			tasa.setDiaHasta(newTasaInteres.getDiaHasta());
			tasa.setTasaInteres(newTasaInteres.getTasaInteres());
			tasa.setTipoCuenta(tipoC);
			tasaInteresON.registrarTasaInteres(tasa);
			return "listarTasaInteres?faces-redirect=true";

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al registrar Tasa interes" + e.getMessage());
			return null;
		}
	}
}
