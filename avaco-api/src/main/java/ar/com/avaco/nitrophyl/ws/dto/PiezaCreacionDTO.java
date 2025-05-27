/**
 * 
 */
package ar.com.avaco.nitrophyl.ws.dto;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ar.com.avaco.nitrophyl.domain.entities.moldes.PlanoClasificacion;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class PiezaCreacionDTO extends DTOEntity<Long> {

	private Long id;

	private String denominacion;

	private Long idFormula;

	private Long idTipoPieza;

	private Long idMolde;

	private Long idCliente;

	private String nombrePiezaCliente;

	private Long dureza;

	private Long espesorMinimo;

	private Long espesorMaximo;

	private Long pesoCrudo;

	private String observacionesPesoCrudo;

	private byte[] planoArchivo;

	private String planoCodigo;

	private Integer planoRevision;

	private String planoClasificacion;

	private String planoObservaciones;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public Long getIdFormula() {
		return idFormula;
	}

	public void setIdFormula(Long idFormula) {
		this.idFormula = idFormula;
	}

	public Long getIdTipoPieza() {
		return idTipoPieza;
	}

	public void setIdTipoPieza(Long idTipoPieza) {
		this.idTipoPieza = idTipoPieza;
	}

	public Long getIdMolde() {
		return idMolde;
	}

	public void setIdMolde(Long idMolde) {
		this.idMolde = idMolde;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombrePiezaCliente() {
		return nombrePiezaCliente;
	}

	public void setNombrePiezaCliente(String nombrePiezaCliente) {
		this.nombrePiezaCliente = nombrePiezaCliente;
	}

	public Long getDureza() {
		return dureza;
	}

	public void setDureza(Long dureza) {
		this.dureza = dureza;
	}

	public Long getEspesorMinimo() {
		return espesorMinimo;
	}

	public void setEspesorMinimo(Long espesorMinimo) {
		this.espesorMinimo = espesorMinimo;
	}

	public Long getEspesorMaximo() {
		return espesorMaximo;
	}

	public void setEspesorMaximo(Long espesorMaximo) {
		this.espesorMaximo = espesorMaximo;
	}

	public Long getPesoCrudo() {
		return pesoCrudo;
	}

	public void setPesoCrudo(Long pesoCrudo) {
		this.pesoCrudo = pesoCrudo;
	}

	public String getObservacionesPesoCrudo() {
		return observacionesPesoCrudo;
	}

	public void setObservacionesPesoCrudo(String observacionesPesoCrudo) {
		this.observacionesPesoCrudo = observacionesPesoCrudo;
	}

	public byte[] getPlanoArchivo() {
		return planoArchivo;
	}

	public void setPlanoArchivo(byte[] planoArchivo) {
		this.planoArchivo = planoArchivo;
	}

	public String getPlanoCodigo() {
		return planoCodigo;
	}

	public void setPlanoCodigo(String planoCodigo) {
		this.planoCodigo = planoCodigo;
	}

	public Integer getPlanoRevision() {
		return planoRevision;
	}

	public void setPlanoRevision(Integer planoRevision) {
		this.planoRevision = planoRevision;
	}

	public String getPlanoClasificacion() {
		return planoClasificacion;
	}

	public void setPlanoClasificacion(String planoClasificacion) {
		this.planoClasificacion = planoClasificacion;
	}

	public String getPlanoObservaciones() {
		return planoObservaciones;
	}

	public void setPlanoObservaciones(String planoObservaciones) {
		this.planoObservaciones = planoObservaciones;
	}

}
