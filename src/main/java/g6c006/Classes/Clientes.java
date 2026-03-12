package g6c006.Classes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@SuppressWarnings("serial")
@Entity
public @Data class Clientes implements Serializable {
	@Id
	private Integer identificacion;
	private String NyA;
	private String rsocial;
	private String mail;
	private Integer telefono;

	public Clientes() {
	}

	public Clientes(Integer identification, String NyLn, String bname, String email, Integer phone) {
		this.setIdentificacion(identification);
		this.setNyA(NyLn);
		this.setRsocial(bname);
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

	public String getRsocial() {
		return rsocial;
	}

	public void setRsocial(String rsocial) {
		this.rsocial = rsocial;
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
