package g6c006.Classes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@SuppressWarnings("serial")
@Entity
public @Data class Tecnicos implements Serializable {
	@Id
	private Integer identificacion;
	private String NyA;
	private String estimatedT;
	private String mail;
	private Integer telefono;

	public Tecnicos() {
	}

	public Tecnicos(Integer identification, String NyLn, String estimated, String email, Integer phone) {
		this.setIdentificacion(identification);
		this.setNyA(NyLn);
		this.setEstimatedT(estimated);
		this.setMail(email);
		this.setTelefono(phone);
	}

	public Integer getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(Integer identificacion) {
		this.identificacion = identificacion;
	}

	public String getNyA() {
		return NyA;
	}

	public void setNyA(String nyA) {
		NyA = nyA;
	}

	public String getEstimatedT() {
		return estimatedT;
	}

	public void setEstimatedT(String estimatedT) {
		this.estimatedT = estimatedT;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}
}
