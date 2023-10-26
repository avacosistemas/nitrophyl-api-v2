package ar.com.avaco.nitrophyl.ws.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class ConfiguracionPruebaDTO extends DTOEntity<Long> {

	private Long id;

	private Long idMaquina;

	private Long idFormula;

	private String fecha;

	private String maquina;

	private List<ConfiguracionPruebaCondicionDTO> condiciones = new ArrayList<ConfiguracionPruebaCondicionDTO>();

	private List<ConfiguracionPruebaParametroDTO> parametros = new ArrayList<ConfiguracionPruebaParametroDTO>();

	public Long getIdMaquina() {
		return idMaquina;
	}

	public void setIdMaquina(Long idMaquina) {
		this.idMaquina = idMaquina;
	}

	public Long getIdFormula() {
		return idFormula;
	}

	public void setIdFormula(Long idFormula) {
		this.idFormula = idFormula;
	}

	public List<ConfiguracionPruebaCondicionDTO> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<ConfiguracionPruebaCondicionDTO> condiciones) {
		this.condiciones = condiciones;
	}

	public List<ConfiguracionPruebaParametroDTO> getParametros() {
		return parametros;
	}

	public void setParametros(List<ConfiguracionPruebaParametroDTO> parametros) {
		this.parametros = parametros;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getMaquina() {
		return maquina;
	}

	public void setMaquina(String maquina) {
		this.maquina = maquina;
	}

}
