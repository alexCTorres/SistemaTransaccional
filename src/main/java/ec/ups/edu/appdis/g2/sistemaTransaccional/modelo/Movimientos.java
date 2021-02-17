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
@Table(name = "ef_movimientos")
public class Movimientos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mov_id")
	private int id;
	@Column(name = "mov_tipo")
	private String tipo;
	@Column(name = "mov_monto")
	private double monto;
	@Column(name = "mov_cuenta_enviada")
	private String cuentaSale;
	@Column(name = "mov_fecha")
	private Date fecha;
	@OneToOne
	@JoinColumn(name = "cuentas_id_fk")
	private Cuenta cuenta;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getCuentaSale() {
		return cuentaSale;
	}

	public void setCuentaSale(String cuentaSale) {
		this.cuentaSale = cuentaSale;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	@Override
	public String toString() {
		return "Movimientos [id=" + id + ", tipo=" + tipo + ", monto=" + monto + ", cuentaSale=" + cuentaSale
				+ ", fecha=" + fecha + ", cuenta=" + cuenta + "]";
	}
	
	

}
