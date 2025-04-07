package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.List;

import ar.com.avaco.arc.core.domain.Entity;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.moldes.Molde;

public class Pieza extends Entity<Long> {

	private Long id;
	private Formula formula;
	private Molde molde;
	private String denominacion;
	private String tipo;
	private Double alto;
	private Double ancho;
	private Double profundidad;
	private Double diametro;
	List<InsumoTratado> insumos;

	public Formula getFormula() {
		return formula;
	}

	public void setFormula(Formula formula) {
		this.formula = formula;
	}

	public Molde getMolde() {
		return molde;
	}

	public void setMolde(Molde molde) {
		this.molde = molde;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getAlto() {
		return alto;
	}

	public void setAlto(Double alto) {
		this.alto = alto;
	}

	public Double getAncho() {
		return ancho;
	}

	public void setAncho(Double ancho) {
		this.ancho = ancho;
	}

	public Double getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(Double profundidad) {
		this.profundidad = profundidad;
	}

	public Double getDiametro() {
		return diametro;
	}

	public void setDiametro(Double diametro) {
		this.diametro = diametro;
	}

	public List<InsumoTratado> getInsumos() {
		return insumos;
	}

	public void setInsumos(List<InsumoTratado> insumos) {
		this.insumos = insumos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
