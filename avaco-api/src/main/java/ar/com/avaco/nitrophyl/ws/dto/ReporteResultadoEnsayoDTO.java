package ar.com.avaco.nitrophyl.ws.dto;

public class ReporteResultadoEnsayoDTO {

	private Long idMaquinaPrueba;

	private Double redondeo;

	private Double resultado;

	private String estadoEnsayo;

	public Long getIdMaquinaPrueba() {
		return idMaquinaPrueba;
	}

	public void setIdMaquinaPrueba(Long idMaquinaPrueba) {
		this.idMaquinaPrueba = idMaquinaPrueba;
	}

	public Double getRedondeo() {
		return redondeo;
	}

	public void setRedondeo(Double redondeo) {
		this.redondeo = redondeo;
	}

	public Double getResultado() {
		return resultado;
	}

	public void setResultado(Double resultado) {
		this.resultado = resultado;
	}

	public String getEstadoEnsayo() {
		return estadoEnsayo;
	}

	public void setEstadoEnsayo(String estadoEnsayo) {
		this.estadoEnsayo = estadoEnsayo;
	}

}
