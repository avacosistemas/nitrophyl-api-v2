package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class TipoInsumoDTO extends DTOAuditableEntity<Long> {

	private Long id;
	private String nombre;
	private Long parentId;

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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
