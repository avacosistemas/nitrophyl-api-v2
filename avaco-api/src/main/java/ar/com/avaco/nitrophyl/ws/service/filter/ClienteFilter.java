package ar.com.avaco.nitrophyl.ws.service.filter;

import java.util.ArrayList;
import java.util.List;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.arc.core.domain.filter.FilterData;
import ar.com.avaco.arc.core.domain.filter.FilterDataType;
import ar.com.avaco.nitrophyl.domain.entities.pieza.TipoPieza;

public class ClienteFilter extends AbstractFilter {

	private String nombre;

	private String razonSocial;

	private String cuit;

	@Override
	public List<FilterData> getFilterDatas() {
		List<FilterData> list = new ArrayList<FilterData>();
		if (razonSocial != null) {
			list.add(new FilterData("razonSocial", razonSocial, FilterDataType.LIKE));
		}
		if (nombre != null) {
			list.add(new FilterData("nombre", nombre, FilterDataType.LIKE));
		}
		if (cuit != null) {
			list.add(new FilterData("cuit", cuit, FilterDataType.LIKE));
		}
		return list;
	}

	public ClienteFilter() {
	}

	public ClienteFilter(String razonSocial, String cuit, String nombre) {
		this.razonSocial = razonSocial;
		this.cuit = cuit;
		this.nombre = nombre;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
