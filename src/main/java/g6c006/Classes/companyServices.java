package g6c006.Classes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@SuppressWarnings("serial")
@Entity
public @Data class companyServices implements Serializable {
	@Id
	private String servicio;

	public companyServices() {
	}

	public companyServices(String service) {
		this.setServicio(service);
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
}
