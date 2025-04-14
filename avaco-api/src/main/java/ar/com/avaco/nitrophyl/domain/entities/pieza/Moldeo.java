package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.List;

public class Moldeo {

	private Proceso proceso;

	// Cambiar por un id a un ABM de prensas
	private String prensa;

	// Agregar unidad de tiempo (seg o min)
	private Integer precalentamiento;

	private Vulcanizacion vulcanizacion;
	private List<Bombeo> bombeos;

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public String getPrensa() {
		return prensa;
	}

	public void setPrensa(String prensa) {
		this.prensa = prensa;
	}

	public Integer getPrecalentamiento() {
		return precalentamiento;
	}

	public void setPrecalentamiento(Integer precalentamiento) {
		this.precalentamiento = precalentamiento;
	}

	public Vulcanizacion getVulcanizacion() {
		return vulcanizacion;
	}

	public void setVulcanizacion(Vulcanizacion vulcanizacion) {
		this.vulcanizacion = vulcanizacion;
	}

	public List<Bombeo> getBombeos() {
		return bombeos;
	}

	public void setBombeos(List<Bombeo> bombeos) {
		this.bombeos = bombeos;
	}

}
