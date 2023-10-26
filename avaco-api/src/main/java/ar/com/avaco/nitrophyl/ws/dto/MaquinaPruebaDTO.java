package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class MaquinaPruebaDTO extends DTOEntity<Long> {

	private Long id;
	private Long idMaquina;
	private String Maquina;
	private String nombre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdMaquina() {
		return idMaquina;
	}

	public void setIdMaquina(Long idMaquina) {
		this.idMaquina = idMaquina;
	}

	public String getMaquina() {
		return Maquina;
	}

	public void setMaquina(String maquina) {
		Maquina = maquina;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
