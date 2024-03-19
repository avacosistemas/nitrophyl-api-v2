package ar.com.avaco.nitrophyl.ws.service.filter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.arc.core.domain.filter.FilterData;
import ar.com.avaco.arc.core.domain.filter.FilterDataType;
import ar.com.avaco.nitrophyl.domain.entities.lote.EstadoLote;
import ar.com.avaco.nitrophyl.ws.dto.LoteFilterDTO;

public class LoteFilter extends AbstractFilter {

	private String nroLote;

	private Long idFormula;

	private String fechaDesde;

	private String fechaHasta;
	
	private boolean excluirPendientes = false;

	private String estado;	public LoteFilter() {
	}

	public LoteFilter(LoteFilterDTO filter) {
		super(filter.getRows(), filter.getFirst(), filter.getAsc(), filter.getIdx());
		this.nroLote = filter.getNroLote();
		this.idFormula = filter.getIdFormula();
		this.fechaDesde = filter.getFechaDesde();
		this.fechaHasta = filter.getFechaHasta();
		this.excluirPendientes = filter.isExcluirPendientes();
		this.estado = filter.getEstado();
	}

	@Override
	public List<FilterData> getFilterDatas() {
		List<FilterData> list = new ArrayList<FilterData>();
		if (StringUtils.isNotBlank(this.nroLote)) {
			list.add(new FilterData("nroLote", this.nroLote, FilterDataType.LIKE));
		}
		if (idFormula != null && idFormula > 0) {
			list.add(new FilterData("formula.id", idFormula, FilterDataType.EQUALS));
		}
		if (StringUtils.isNotBlank(fechaDesde)) {
				list.add(new FilterData("fecha", fechaDesde, FilterDataType.EQUALS_MORE_THAN));

		}
		if (StringUtils.isNotBlank(fechaHasta)) {
				list.add(new FilterData("fecha", fechaHasta, FilterDataType.EQUALS_LESS_THAN));
		}
		
		if (StringUtils.isNotBlank(estado)) {
			list.add(new FilterData("estado", EstadoLote.valueOf(estado), FilterDataType.EQUALS));
		}
		
		if (excluirPendientes) {
			list.add(new FilterData("fechaEstado", null, FilterDataType.IS_NOT_NULL));
		}
		
		return list;
	}

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

	public boolean isIncluirPendientes() {
		return excluirPendientes;
}

	public void setIncluirPendientes(boolean incluirPendientes) {
		this.excluirPendientes = incluirPendientes;
	}

}
