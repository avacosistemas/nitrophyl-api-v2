package ar.com.avaco.nitrophyl.ws.service.filter;

import java.util.ArrayList;
import java.util.List;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.arc.core.domain.filter.FilterData;
import ar.com.avaco.arc.core.domain.filter.FilterDataType;
import ar.com.avaco.nitrophyl.domain.entities.pieza.TipoPieza;

public class PiezaFilter extends AbstractFilter {

	private TipoPieza tipo;

	private Boolean esProducto;

	@Override
	public List<FilterData> getFilterDatas() {
		List<FilterData> list = new ArrayList<FilterData>();
		if (tipo != null) {
			list.add(new FilterData("tipo", tipo, FilterDataType.EQUALS));
		}
		if (esProducto != null) {
			list.add(new FilterData("esProducto", esProducto, FilterDataType.EQUALS));
		}
		return list;
	}

	public TipoPieza getTipo() {
		return tipo;
	}

	public void setTipo(TipoPieza tipo) {
		this.tipo = tipo;
	}

	public Boolean getEsProducto() {
		return esProducto;
	}

	public void setEsProducto(Boolean esProducto) {
		this.esProducto = esProducto;
	}

}
