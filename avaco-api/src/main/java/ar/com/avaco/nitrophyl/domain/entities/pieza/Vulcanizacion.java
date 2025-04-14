package ar.com.avaco.nitrophyl.domain.entities.pieza;

public class Vulcanizacion {

	private Moldeo moldeo;

	public Moldeo getMoldeo() {
		return moldeo;
	}

	public void setMoldeo(Moldeo moldeo) {
		this.moldeo = moldeo;
	}

	public Long getTiempoVulcanizacion() {
		return tiempoVulcanizacion;
	}

	public void setTiempoVulcanizacion(Long tiempoVulcanizacion) {
		this.tiempoVulcanizacion = tiempoVulcanizacion;
	}

	public Double getTemperaturaVulcanizacion() {
		return temperaturaMinVulcanizacion;
	}

	public void setTemperaturaVulcanizacion(Double temperaturaVulcanizacion) {
		this.temperaturaMinVulcanizacion = temperaturaVulcanizacion;
	}

	// Siempre en minutos
	private Long tiempoVulcanizacion;
	private Double temperaturaMinVulcanizacion;
	private Double temperaturaMaxVulcanizacion;

}
