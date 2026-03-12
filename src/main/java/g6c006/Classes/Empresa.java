package g6c006.Classes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@SuppressWarnings("serial")
@Entity
public @Data class Empresa implements Serializable {
	@Id
	private String id;
	private String servicio;
	private String especialidad;

	public Empresa() {
	}

	public Empresa(String id, String service, String specialty) {
		this.setId(id);
		this.setServicio(service);
		this.setEspecialidad(specialty);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
}
