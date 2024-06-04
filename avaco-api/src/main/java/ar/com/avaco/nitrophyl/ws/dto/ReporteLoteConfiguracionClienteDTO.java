package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class ReporteLoteConfiguracionClienteDTO extends DTOEntity<Long> {

	private Long id;

	private Long idCliente;

	private String cliente;

	private Long idFormula;

	private String formula;

	private Long idMaquina;

	private String maquina;

	private boolean mostrarParametros;

	private boolean mostrarResultados;

	private boolean mostrarCondiciones;

	private boolean mostraObervacionesMaquina;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Long getIdFormula() {
		return idFormula;
	}

	public void setIdFormula(Long idFormula) {
		this.idFormula = idFormula;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public Long getIdMaquina() {
		return idMaquina;
	}

	public void setIdMaquina(Long idMaquina) {
		this.idMaquina = idMaquina;
	}

	public String getMaquina() {
		return maquina;
	}

	public void setMaquina(String maquina) {
		this.maquina = maquina;
	}

	public boolean isMostrarParametros() {
		return mostrarParametros;
	}

	public void setMostrarParametros(boolean mostrarParametros) {
		this.mostrarParametros = mostrarParametros;
	}

	public boolean isMostrarResultados() {
		return mostrarResultados;
	}

	public void setMostrarResultados(boolean mostrarResultados) {
		this.mostrarResultados = mostrarResultados;
	}

	public boolean isMostrarCondiciones() {
		return mostrarCondiciones;
	}

	public void setMostrarCondiciones(boolean mostrarCondiciones) {
		this.mostrarCondiciones = mostrarCondiciones;
	}

	public boolean isMostraObervacionesMaquina() {
		return mostraObervacionesMaquina;
	}

	public void setMostraObervacionesMaquina(boolean mostraObervacionesMaquina) {
		this.mostraObervacionesMaquina = mostraObervacionesMaquina;
	}

}
