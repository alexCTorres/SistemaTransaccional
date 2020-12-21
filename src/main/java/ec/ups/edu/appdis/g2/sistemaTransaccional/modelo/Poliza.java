package ec.ups.edu.appdis.g2.sistemaTransaccional.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ef_polizas")
public class Poliza implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pol_id")
	private int id;
	@Column(name = "pol_capital")
	private double capital;
	@Column(name = "pol_plazo")
	private int plazo;
	@Column(name = "pol_frecuencia_pago")
	private String frecuenciaPago;
	@Column(name = "pol_fecha_vencimiento")
	private Date fechaVencimiento;
	@Column(name = "pol_dia_pago")
	private int diaPago;
	@Column(name = "pol_interes")
	private double interes;
	@Column(name = "pol_estado")
	private String estado;
	@Column(name = "pol_imagen_cedula")
	private byte[] fotoCedula;
	@Column(name = "pol_imagen_servbasico")
	private byte[] fotoServBasico;
	@OneToOne
	@JoinColumn(name = "cuentas_id_fk")
	private Cuenta cuenta;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}

	public int getPlazo() {
		return plazo;
	}

	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}

	public String getFrecuenciaPago() {
		return frecuenciaPago;
	}

	public void setFrecuenciaPago(String frecuenciaPago) {
		this.frecuenciaPago = frecuenciaPago;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public int getDiaPago() {
		return diaPago;
	}

	public void setDiaPago(int diaPago) {
		this.diaPago = diaPago;
	}

	public double getInteres() {
		return interes;
	}

	public void setInteres(double interes) {
		this.interes = interes;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public byte[] getFotoCedula() {
		return fotoCedula;
	}

	public void setFotoCedula(byte[] fotoCedula) {
		this.fotoCedula = fotoCedula;
	}

	public byte[] getFotoServBasico() {
		return fotoServBasico;
	}

	public void setFotoServBasico(byte[] fotoServBasico) {
		this.fotoServBasico = fotoServBasico;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

}
