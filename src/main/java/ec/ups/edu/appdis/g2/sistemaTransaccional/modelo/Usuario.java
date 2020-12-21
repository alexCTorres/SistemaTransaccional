package ec.ups.edu.appdis.g2.sistemaTransaccional.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ef_usuarios")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "usu_nombre_usuario")
	private String nombreUsuario;
	@Column(name = "usu_contrasenia")
	private String contrasenia;
	@Column(name = "usu_rol")
	private String rol;
	@Column(name = "usu_estado")
	private String estado;
	@Column(name = "usu_intentos_logeo")
	private int intentosLogin;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "personas_id_fk")
	private Persona persona;

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getIntentosLogin() {
		return intentosLogin;
	}

	public void setIntentosLogin(int intentosLogin) {
		this.intentosLogin = intentosLogin;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}
