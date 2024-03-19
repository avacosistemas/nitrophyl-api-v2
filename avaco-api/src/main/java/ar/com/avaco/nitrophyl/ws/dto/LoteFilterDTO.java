package ar.com.avaco.nitrophyl.ws.dto;

public class LoteFilterDTO extends SortPageDTO {

	private String nroLote;

	private Long idFormula;

	private String fechaDesde;

	private String fechaHasta;

	private boolean excluirPendientes = false;

	public String getNroLote() {
		return nroLote;
	}

	public void setNroLote(String nroLote) {
		this.nroLote = nroLote;
	}

	public Long getIdFormula() {
		return idFormula;
	}

	public void setIdFormula(Long idFormula) {
		this.idFormula = idFormula;
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

	public boolean isExcluirPendientes() {
		return excluirPendientes;
	}

	public void setExcluirPendientes(boolean excluirPendientes) {
		this.excluirPendientes = excluirPendientes;
	}

}
