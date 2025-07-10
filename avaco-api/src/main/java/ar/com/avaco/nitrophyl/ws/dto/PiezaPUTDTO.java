/**
 * 
 */
package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.nitrophyl.domain.entities.pieza.UnidadDureza;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class PiezaPUTDTO extends DTOEntity<Long> {

	private Long id;

	private Double durezaMinima;

	private Double durezaMaxima;

	private UnidadDureza unidadDureza;

	private Double espesorMinimo;

	private Double espesorMaximo;

	private Double pesoCrudo;

	private String observacionesPesoCrudo;

	private String observacionesRevision;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getDurezaMinima() {
		return durezaMinima;
	}

	public void setDurezaMinima(Double durezaMinima) {
		this.durezaMinima = durezaMinima;
	}

	public Double getDurezaMaxima() {
		return durezaMaxima;
	}

	public void setDurezaMaxima(Double durezaMaxima) {
		this.durezaMaxima = durezaMaxima;
	}

	public UnidadDureza getUnidadDureza() {
		return unidadDureza;
	}

	public void setUnidadDureza(UnidadDureza unidadDureza) {
		this.unidadDureza = unidadDureza;
	}

	public Double getEspesorMinimo() {
		return espesorMinimo;
	}

	public void setEspesorMinimo(Double espesorMinimo) {
		this.espesorMinimo = espesorMinimo;
	}

	public Double getEspesorMaximo() {
		return espesorMaximo;
	}

	public void setEspesorMaximo(Double espesorMaximo) {
		this.espesorMaximo = espesorMaximo;
	}

	public Double getPesoCrudo() {
		return pesoCrudo;
	}

	public void setPesoCrudo(Double pesoCrudo) {
		this.pesoCrudo = pesoCrudo;
	}

	public String getObservacionesPesoCrudo() {
		return observacionesPesoCrudo;
	}

	public void setObservacionesPesoCrudo(String observacionesPesoCrudo) {
		this.observacionesPesoCrudo = observacionesPesoCrudo;
	}

	public String getObservacionesRevision() {
		return observacionesRevision;
	}

	public void setObservacionesRevision(String observacionesRevision) {
		this.observacionesRevision = observacionesRevision;
	}

}
