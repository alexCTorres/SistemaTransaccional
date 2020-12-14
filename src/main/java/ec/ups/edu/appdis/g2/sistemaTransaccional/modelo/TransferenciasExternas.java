package ec.ups.edu.appdis.g2.sistemaTransaccional.modelo;

import java.io.Serializable;
import java.security.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class TransferenciasExternas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@JoinColumn(name = "tra_id")
	private int id;
	private String entidadFinanciera;
	private String cuentaSale;
	private double monto;
	private Timestamp fecha;
	private Cuenta Cuenta;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEntidadFinanciera() {
		return entidadFinanciera;
	}

	public void setEntidadFinanciera(String entidadFinanciera) {
		this.entidadFinanciera = entidadFinanciera;
	}

	public String getCuentaSale() {
		return cuentaSale;
	}

	public void setCuentaSale(String cuentaSale) {
		this.cuentaSale = cuentaSale;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Cuenta getCuenta() {
		return Cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		Cuenta = cuenta;
	}

}
