package ec.ups.edu.appdis.g2.sistemaTransaccional.vista;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.TasaInteres;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.TipoCuenta;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionTasaInteresON;

@Named
@RequestScoped
public class EditarTasaInteresBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id; // parametro de carga desde lista de interes/editar
	private int diaDesde;
	private int diaHasta;
	private double tasaInteres;
	private TipoCuenta tipoCuenta;

	@Inject
	private GestionTasaInteresON tasaInteresON;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDiaDesde() {
		return diaDesde;
	}

	public void setDiaDesde(int diaDesde) {
		this.diaDesde = diaDesde;
	}

	public int getDiaHasta() {
		return diaHasta;
	}

	public void setDiaHasta(int diaHasta) {
		this.diaHasta = diaHasta;
	}

	public double getTasaInteres() {
		return tasaInteres;
	}

	public void setTasaInteres(double tasaInteres) {
		this.tasaInteres = tasaInteres;
	}

	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public GestionTasaInteresON getTasaInteresON() {
		return tasaInteresON;
	}

	public void setTasaInteresON(GestionTasaInteresON tasaInteresON) {
		this.tasaInteresON = tasaInteresON;
	}

	public String irPaginaEditar(int id) {
		TasaInteres newTasaInteres = new TasaInteres();
		System.out.println("id que pasa de la pagina listar" + id);
		try {
			newTasaInteres = tasaInteresON.buscarTasaInteres(id);
			System.out.println("Tasa encontrada dia desde mismo metodo" + newTasaInteres.getDiaDesde());

		} catch (Exception e) {
			System.out.println("No encuentra id mismo metodo " + id + " no buscar los adtos");
			e.printStackTrace();
		}
		return "editarTasaInteres?faces-redirect=true&id=" + id;
	}

	public String cargarDatosTasa() {
		TasaInteres newTasaInteres = new TasaInteres();
		try {
			if (id != 0) {
				newTasaInteres = tasaInteresON.buscarTasaInteres(id);
				System.out.println("Tasa encontrada dia desde" + newTasaInteres.getDiaDesde());
			}
		} catch (Exception e) {
			System.out.println("No encuentra id " + id + " no buscar los adtos");
			e.printStackTrace();
		}
		return null;
	}

	public String doBuscarTasa() {
		TasaInteres tasa = new TasaInteres();
		try {
			tasa = tasaInteresON.buscarTasaInteres(id);
			if (tasa == null) {
				System.out.println("Id de tasa de interes no encontrada");
				return null;
			} else {
				diaDesde = tasa.getDiaDesde();
				diaHasta = tasa.getDiaHasta();
				tasaInteres = tasa.getTasaInteres();
				tipoCuenta = tasa.getTipoCuenta();
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String actualizarTasa() {
		TasaInteres tasa = new TasaInteres();
		try {
			tasa = tasaInteresON.buscarTasaInteres(id);
			tasa.setId(tasa.getId());
			tasa.setDiaDesde(diaDesde);
			tasa.setDiaHasta(diaHasta);
			tasa.setTasaInteres(tasaInteres);
			tasa.setTipoCuenta(tasa.getTipoCuenta());
			tasaInteresON.actualizarTasaInteres(tasa);
			System.out.println("Tasa actualizado correctamente");
			return "listarTasaInteres?faces-redirect=true";
		} catch (Exception e) {
			System.out.println("Error al actualizar tasa");
			e.printStackTrace();
			return null;
		}
	}
	
	public String eliminarTasa() {
		TasaInteres tasa = new TasaInteres();
		try {
			tasa = tasaInteresON.buscarTasaInteres(id);
			tasaInteresON.eliminarTasaInteres(tasa.getId());
			System.out.println("Tasa eliminado correctamente");
			return "listarTasaInteres?faces-redirect=true";
		} catch (Exception e) {
			System.out.println("Error al eliminar tasa");
			e.printStackTrace();
			return null;
		}
	}
}
