/**
 * 
 */
package ar.com.avaco.nitrophyl.ws.dto;

import java.util.List;

import ar.com.avaco.nitrophyl.domain.entities.pieza.TipoPieza;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class PiezaDTO extends DTOEntity<Long> {

	private Long id;
	private String codigoPieza;
	private String nombre;
	private String codigoInterno;
	private TipoPieza tipo;
	private Boolean esProducto;
	
	private List<PiezaDTO> piezas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoPieza() {
		return codigoPieza;
	}

	public void setCodigoPieza(String codigoPieza) {
		this.codigoPieza = codigoPieza;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigoInterno() {
		return codigoInterno;
	}

	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = codigoInterno;
	}

	public TipoPieza getTipo() {
		return tipo;
	}

	public void setTipo(TipoPieza tipo) {
		this.tipo = tipo;
	}

	public Boolean getEsProducto() {
		return esProducto;
	}

	public void setEsProducto(Boolean esProducto) {
		this.esProducto = esProducto;
	}

	public List<PiezaDTO> getPiezas() {
		return piezas;
	}

	public void setPiezas(List<PiezaDTO> piezas) {
		this.piezas = piezas;
	}
	
	

}
