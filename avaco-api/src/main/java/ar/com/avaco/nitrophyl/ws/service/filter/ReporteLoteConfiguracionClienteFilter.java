package ar.com.avaco.nitrophyl.ws.service.filter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.arc.core.domain.filter.FilterData;
import ar.com.avaco.arc.core.domain.filter.FilterDataType;
import ar.com.avaco.nitrophyl.ws.dto.FormulaFilterDTO;

public class ReporteLoteConfiguracionClienteFilter extends AbstractFilter {

	private Long idCliente;

	private Long idFormula;

	private Long idMaquina;

	private Boolean mostrarParametros;

	private Boolean mostrarResultados;

	private Boolean mostrarCondiciones;

	private Boolean mostraObervacionesMaquina;

	@Override
	public List<FilterData> getFilterDatas() {
		List<FilterData> list = new ArrayList<FilterData>();

		if (idCliente != null) {
			list.add(new FilterData("cliente.idCliente", idCliente, FilterDataType.EQUALS));
		}

		if (idFormula != null) {
			list.add(new FilterData("formula.idFormula", idFormula, FilterDataType.EQUALS));
		}

		if (idMaquina != null) {
			list.add(new FilterData("maquina.idMaquina", idMaquina, FilterDataType.EQUALS));
		}

		if (mostrarParametros != null) {
			list.add(new FilterData("mostrarParametros", mostrarParametros, FilterDataType.EQUALS));
		}

		if (mostrarResultados != null) {
			list.add(new FilterData("mostrarResultados", mostrarResultados, FilterDataType.EQUALS));
		}

		if (mostrarCondiciones != null) {
			list.add(new FilterData("mostrarCondiciones", mostrarCondiciones, FilterDataType.EQUALS));
		}

		if (mostraObervacionesMaquina != null) {
			list.add(new FilterData("mostraObervacionesMaquina", mostraObervacionesMaquina, FilterDataType.EQUALS));
		}

		return list;
	}

	public ReporteLoteConfiguracionClienteFilter() {
	}

	public ReporteLoteConfiguracionClienteFilter(FormulaFilterDTO ffdto) {
		super(ffdto.getRows(), ffdto.getFirst(), ffdto.getAsc(), ffdto.getIdx());
	}

}
