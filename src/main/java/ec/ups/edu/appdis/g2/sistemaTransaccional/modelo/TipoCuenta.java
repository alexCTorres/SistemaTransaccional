package ec.ups.edu.appdis.g2.sistemaTransaccional.modelo;

import java.io.Serializable;

public class TipoCuenta implements Serializable{

	private int id;
	private String tipoCuenta;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

}
