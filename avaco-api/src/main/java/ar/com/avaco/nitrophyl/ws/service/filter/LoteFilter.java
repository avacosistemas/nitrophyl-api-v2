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

	private Long idLote;

	private Long idFormula;

	private String fechaDesde;

	private String fechaHasta;

	public LoteFilter() {
	}
	
	public LoteFilter(LoteFilterDTO filter) {
		super(filter.getRows(), filter.getFirst(), filter.getAsc(), filter.getIdx());
		this.idLote = filter.getIdLote();
		this.idFormula = filter.getIdFormula();
		this.fechaDesde = filter.getFechaDesde();
		this.fechaHasta = filter.getFechaHasta();
	}

	@Override
	public List<FilterData> getFilterDatas() {
		List<FilterData> list = new ArrayList<FilterData>();
		if (idLote != null && idLote > 0) {
			list.add(new FilterData("id", idLote, FilterDataType.EQUALS));
		}
		if (idFormula != null && idFormula > 0) {
			list.add(new FilterData("formula.id", idFormula, FilterDataType.EQUALS));
		}
		if (StringUtils.isNotBlank(fechaDesde)) {
			try {
				Date desde = DateUtils.toDate(fechaDesde);
				list.add(new FilterData("fechaDesde", desde, FilterDataType.EQUALS_MORE_THAN));
			} catch (ParseException e) {
			}
			
		}
		if (StringUtils.isNotBlank(fechaHasta)) {
			try {
				Date hasta = DateUtils.toDate(fechaHasta);
				list.add(new FilterData("fechaHasta", hasta, FilterDataType.EQUALS_LESS_THAN));
			} catch (ParseException e) {
			}
			
		}
		return list;
	}

	public Long getIdLote() {
		return idLote;
	}

	public void setIdLote(Long idLote) {
		this.idLote = idLote;
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

}
