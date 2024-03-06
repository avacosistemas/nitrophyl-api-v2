package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class MaquinaDTO extends DTOEntity<Long> {

	private Long id;
	private String nombre;
	private String estado;
	private String observacionesReporte;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getObservacionesReporte() {
		return observacionesReporte;
	}

	public void setObservacionesReporte(String observacionesReporte) {
		this.observacionesReporte = observacionesReporte;
	}

}
