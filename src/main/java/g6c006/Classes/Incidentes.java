package g6c006.Classes;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@SuppressWarnings("serial")
@Entity
public @Data class Incidentes implements Serializable {
	@Id
	private String id;
	private Integer cliente;
	private Integer tecnico;
	private String servicio;
	private String type;
	private String descripcion;
	private String complejidad;
	@Temporal(TemporalType.DATE)
	private Date reportDate;

	private LocalTime reportTime;
	private Boolean resuelto;
	private String resolucion;
	@Temporal(TemporalType.DATE)
	private Date solvDate;
	private LocalTime solvTime;

	public Incidentes() {
	}

	public Incidentes(String identification, Integer customer, Integer technical, String service, String tipo,
			String description, String complexity, Date date, LocalTime time, Boolean solved, String resolution,
			Date finishd, LocalTime finisht) {
		this.setId(identification);
		this.setCliente(customer);
		this.setTecnico(technical);
		this.setServicio(service);
		this.setType(tipo);
		this.setDescripcion(description);
		this.setComplejidad(complexity);
		this.setReportDate(date);
		this.setReportTime(time);
		this.setResuelto(solved);
		this.setResolucion(resolution);
		this.setSolvDate(finishd);
		this.setSolvTime(finisht);
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

	public Integer getTecnico() {
		return tecnico;
	}

	public void setTecnico(Integer tecnico) {
		this.tecnico = tecnico;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getComplejidad() {
		return complejidad;
	}

	public void setComplejidad(String complejidad) {
		this.complejidad = complejidad;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public LocalTime getReportTime() {
		return reportTime;
	}

	public void setReportTime(LocalTime reportTime) {
		this.reportTime = reportTime;
	}

	public Boolean getResuelto() {
		return resuelto;
	}

	public void setResuelto(Boolean resuelto) {
		this.resuelto = resuelto;
	}

	public String getResolucion() {
		return resolucion;
	}

	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}

	public Date getSolvDate() {
		return solvDate;
	}

	public void setSolvDate(Date solvDate) {
		this.solvDate = solvDate;
	}

	public LocalTime getSolvTime() {
		return solvTime;
	}

	public void setSolvTime(LocalTime solvTime) {
		this.solvTime = solvTime;
	}
}
