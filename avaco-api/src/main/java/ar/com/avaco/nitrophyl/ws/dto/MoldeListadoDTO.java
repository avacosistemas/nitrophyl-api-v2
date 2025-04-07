/**
 * 
 */
package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class MoldeListadoDTO extends DTOEntity<Long> {

	private Long id;
	private String codigo;
	private String estado;
	private String nombre;
	private String ubicacion;
	private Integer alto;
	private Integer ancho;
	private Integer diametro;
	private Integer profundidad;

	public MoldeListadoDTO() {
		// TODO Auto-generated constructor stub
	}

	public MoldeListadoDTO(Integer id, String codigo, String estado, String nombre, String ubicacion, Integer alto,
			Integer ancho, Integer diametro, Integer profundidad) {
		this.id = new Long(id);
		this.codigo = codigo;
		this.estado = estado;
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.alto = alto;
		this.ancho = ancho;
		this.diametro = diametro;
		this.profundidad = profundidad;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAlto() {
		return alto;
	}

	public void setAlto(Integer alto) {
		this.alto = alto;
	}

	public Integer getAncho() {
		return ancho;
	}

	public void setAncho(Integer ancho) {
		this.ancho = ancho;
	}

	public Integer getDiametro() {
		return diametro;
	}

	public void setDiametro(Integer diametro) {
		this.diametro = diametro;
	}

	public Integer getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(Integer profundidad) {
		this.profundidad = profundidad;
	}

}
