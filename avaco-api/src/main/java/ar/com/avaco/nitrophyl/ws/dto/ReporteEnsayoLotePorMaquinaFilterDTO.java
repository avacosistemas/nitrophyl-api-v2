package ar.com.avaco.nitrophyl.ws.dto;

public class ReporteEnsayoLotePorMaquinaFilterDTO extends SortPageDTO {

	public Long idMaquina;

	public String fechaDesde;

	public String fechaHasta;

	public Long idFormula;

	public String nroLote;

	public String estadoLote;

	public String getEstadoLote() {
		return estadoLote;
	}

	public void setEstadoLote(String estadoLote) {
		this.estadoLote = estadoLote;
	}

	public String getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Long getIdFormula() {
		return idFormula;
	}

	public void setIdFormula(Long idFormula) {
		this.idFormula = idFormula;
	}

	public String getNroLote() {
		return nroLote;
	}

	public void setNroLote(String nroLote) {
		this.nroLote = nroLote;
	}

	public Long getIdMaquina() {
		return idMaquina;
	}

	public void setIdMaquina(Long idMaquina) {
		this.idMaquina = idMaquina;
	}

}
