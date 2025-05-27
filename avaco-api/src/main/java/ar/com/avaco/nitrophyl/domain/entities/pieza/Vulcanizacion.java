package ar.com.avaco.nitrophyl.domain.entities.pieza;

import javax.persistence.Embeddable;

@Embeddable
public class Vulcanizacion {

	private Long tiempoVulcanizacion;
	private Double temperaturaMinVulcanizacion;
	private Double temperaturaMaxVulcanizacion;

	public Double getTemperaturaMinVulcanizacion() {
		return temperaturaMinVulcanizacion;
	}

	public void setTemperaturaMinVulcanizacion(Double temperaturaMinVulcanizacion) {
		this.temperaturaMinVulcanizacion = temperaturaMinVulcanizacion;
	}

	public Double getTemperaturaMaxVulcanizacion() {
		return temperaturaMaxVulcanizacion;
	}

	public void setTemperaturaMaxVulcanizacion(Double temperaturaMaxVulcanizacion) {
		this.temperaturaMaxVulcanizacion = temperaturaMaxVulcanizacion;
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

}
