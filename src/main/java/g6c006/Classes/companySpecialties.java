package g6c006.Classes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@SuppressWarnings("serial")
@Entity
public @Data class companySpecialties implements Serializable {
	@Id
	private String especialidad;

	public companySpecialties() {
	}

	public companySpecialties(String specialty) {
		this.setEspecialidad(specialty);
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
}
