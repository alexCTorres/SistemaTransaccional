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
@Table(name = "ef_transferencias_externas")
public class TransferenciasExternas implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tra_id")
	private int id;
	@Column(name = "tra_entidad_financiera")
	private String entidadFinanciera;
	@Column(name = "tra_num_cuenta")
	private String cuentaSale;
	@Column(name = "tra_monto")
	private double monto;
	@Column(name = "tra_fecha")
	private Date fecha;
	@OneToOne
	@JoinColumn(name = "cuentas_id_fk")
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cuenta getCuenta() {
		return Cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		Cuenta = cuenta;
	}

}
