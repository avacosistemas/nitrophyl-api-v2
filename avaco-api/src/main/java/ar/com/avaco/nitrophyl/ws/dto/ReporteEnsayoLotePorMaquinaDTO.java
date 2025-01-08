package ar.com.avaco.nitrophyl.ws.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class ReporteEnsayoLotePorMaquinaDTO extends DTOEntity<Long> {

	private Long row;

	private Long idLote;

	private String nroLote;

	private String fecha;

	private String observaciones;

	private Long idFormula;

	private String nombreFormula;

	private String estadoLote;

	public List<ReporteResultadoEnsayoDTO> resultados = new ArrayList<ReporteResultadoEnsayoDTO>();

	public Long getIdLote() {
		return idLote;
	}

	public void setIdLote(Long idLote) {
		this.idLote = idLote;
	}

	public String getNroLote() {
		return nroLote;
	}

	public void setNroLote(String nroLote) {
		this.nroLote = nroLote;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Long getIdFormula() {
		return idFormula;
	}

	public void setIdFormula(Long idFormula) {
		this.idFormula = idFormula;
	}

	public String getNombreFormula() {
		return nombreFormula;
	}

	public void setNombreFormula(String nombreFormula) {
		this.nombreFormula = nombreFormula;
	}

	public List<ReporteResultadoEnsayoDTO> getResultados() {
		return resultados;
	}

	public void setResultados(List<ReporteResultadoEnsayoDTO> resultados) {
		this.resultados = resultados;
	}

	@Override
	public void setId(Long id) {
		row = id;
	}

	@Override
	public Long getId() {
		return row;
	}

	public Long getRow() {
		return row;
	}

	public void setRow(Long row) {
		this.row = row;
	}

	public String getEstadoLote() {
		return estadoLote;
	}

	public void setEstadoLote(String estadoLote) {
		this.estadoLote = estadoLote;
	}

}
