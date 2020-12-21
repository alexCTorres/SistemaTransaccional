package ec.ups.edu.appdis.g2.sistemaTransaccional.modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ef_tasa_interes")
public class TasaInteres implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tas_id")
	private int id;
	@Column(name = "tas_dia_desde")
	private int diaDesde;
	@Column(name = "tas_dia_hasta")
	private int diaHasta;
	@Column(name = "tas_tasa_interes")
	private double tasaInteres;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tipocuentas_id_fk")
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
