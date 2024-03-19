package ar.com.avaco.nitrophyl.ws.service.filter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.arc.core.domain.filter.FilterData;
import ar.com.avaco.arc.core.domain.filter.FilterDataType;
import ar.com.avaco.nitrophyl.ws.dto.LoteFilterDTO;
import ar.com.avaco.utils.DateUtils;

public class LoteFilter extends AbstractFilter {

	private String nroLote;

	private Long idFormula;

	private String fechaDesde;

	private String fechaHasta;

	private boolean excluirPendientes = false;

	public LoteFilter() {
	}

	public LoteFilter(LoteFilterDTO filter) {
		super(filter.getRows(), filter.getFirst(), filter.getAsc(), filter.getIdx());
		this.nroLote = filter.getNroLote();
		this.idFormula = filter.getIdFormula();
		this.fechaDesde = filter.getFechaDesde();
		this.fechaHasta = filter.getFechaHasta();
		this.excluirPendientes = filter.isExcluirPendientes();
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
			try {
				Date desde = DateUtils.toDate(fechaDesde);
				list.add(new FilterData("fecha", desde, FilterDataType.EQUALS_MORE_THAN));
			} catch (ParseException e) {
			}

		}
		if (StringUtils.isNotBlank(fechaHasta)) {
			try {
				Date hasta = DateUtils.toDate(fechaHasta);
				list.add(new FilterData("fecha", hasta, FilterDataType.EQUALS_LESS_THAN));
			} catch (ParseException e) {
			}

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
