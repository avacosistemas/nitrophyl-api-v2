package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class MaquinaPruebaDTO extends DTOEntity<Long> {

	private Long id;
	private Long idMaquina;
	private String nombre;
	private int posicion;

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

}
