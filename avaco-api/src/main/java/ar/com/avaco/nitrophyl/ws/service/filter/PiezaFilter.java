package ar.com.avaco.nitrophyl.ws.service.filter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.arc.core.domain.filter.FilterData;
import ar.com.avaco.arc.core.domain.filter.FilterDataType;
import ar.com.avaco.nitrophyl.ws.dto.PiezaFilterDTO;

public class PiezaFilter extends AbstractFilter {

	private String nombre;

	private Long idFormula;

	private Long idMaterial;

	private Boolean soloVigentes;

	public PiezaFilter() {
	}

	public PiezaFilter(PiezaFilterDTO filter) {
		super(filter.getRows(), filter.getFirst(), filter.getAsc(), filter.getIdx());
		this.nombre = filter.getNombre();
		this.idFormula = filter.getIdFormula();
		this.idMaterial = filter.getIdMaterial();
		this.soloVigentes = filter.getSoloVigentes();
	}

	@Override
	public List<FilterData> getFilterDatas() {
		List<FilterData> filters = new ArrayList<FilterData>();

		if (StringUtils.isNotBlank(nombre))
			filters.add(new FilterData("denominacion", nombre, FilterDataType.LIKE));

		if (idFormula != null)
			filters.add(new FilterData("detalleFormula.formula.id", idFormula, FilterDataType.EQUALS));

		if (idMaterial != null)
			filters.add(new FilterData("detalleFormula.formula.material.id", idMaterial, FilterDataType.EQUALS));

		if (soloVigentes != null && soloVigentes.booleanValue())
			filters.add(new FilterData("vigente", true, FilterDataType.EQUALS));

		return filters;
	}

}
