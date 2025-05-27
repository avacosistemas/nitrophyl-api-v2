package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.OneToMany;

import ar.com.avaco.nitrophyl.domain.entities.fabrica.Prensa;

@Embeddable
public class Moldeo {

	private Integer precalentamiento;

	@OneToMany
	private List<Prensa> prensas;

	private String unidadPrecalentamiento;

	@Embedded
	private Vulcanizacion vulcanizacion;

	@OneToMany
	private List<Bombeo> bombeos;

	public Moldeo clonar(Proceso proceso) {
		Moldeo moldeo = new Moldeo();
		this.bombeos.forEach(bombeo -> moldeo.getBombeos().add(bombeo.clonar(moldeo)));
		moldeo.setPrecalentamiento(precalentamiento);
		moldeo.setPrensa(prensas);
		moldeo.setUnidadPrecalentamiento(unidadPrecalentamiento);
		moldeo.setVulcanizacion(vulcanizacion);
		return moldeo;
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

	public List<Prensa> getPrensa() {
		return prensas;
	}

	public void setPrensa(List<Prensa> prensa) {
		this.prensas = prensa;
	}

	public String getUnidadPrecalentamiento() {
		return unidadPrecalentamiento;
	}

	public void setUnidadPrecalentamiento(String unidadPrecalentamiento) {
		this.unidadPrecalentamiento = unidadPrecalentamiento;
	}

}
