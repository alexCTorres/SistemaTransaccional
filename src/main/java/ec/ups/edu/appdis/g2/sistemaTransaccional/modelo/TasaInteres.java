package ec.ups.edu.appdis.g2.sistemaTransaccional.modelo;

import java.io.Serializable;

public class TasaInteres implements Serializable{

	private int id;
	private int diaDesde;
	private int diaHasta;
	private double tasaInteres;
	private TipoCuenta tipoCuenta;

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

}
