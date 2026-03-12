package g6c006.Classes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@SuppressWarnings("serial")
@Entity
public @Data class Servicios implements Serializable {
	@Id
	private String id;
	private Integer cliente;
	private String servicio;

	public Servicios() {
	}

	public Servicios(String identification, Integer customer, String service) {
		this.setId(identification);
		this.setCliente(customer);
		this.setServicio(service);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
}
