/**
 * 
 */
package ar.com.avaco.nitrophyl.ws.dto;

public class MoldeBocaListadoDTO {

	private Integer nroBoca;
	private String estado;
	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getNroBoca() {
		return nroBoca;
	}

	public void setNroBoca(Integer nroBoca) {
		this.nroBoca = nroBoca;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
