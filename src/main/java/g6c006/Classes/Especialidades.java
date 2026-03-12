package g6c006.Classes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@SuppressWarnings("serial")
@Entity
public @Data class Especialidades implements Serializable {
	@Id
	private String id;
	private Integer tecnico;
	private String especialidad;

	public Especialidades() {
	}

	public Especialidades(String id, Integer technical, String specialty) {
		this.setId(id);
		this.setTecnico(technical);
		this.setEspecialidad(specialty);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getTecnico() {
		return tecnico;
	}

	public void setTecnico(Integer tecnico) {
		this.tecnico = tecnico;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
}
