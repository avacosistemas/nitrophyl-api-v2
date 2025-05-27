package ar.com.avaco.nitrophyl.domain.entities.pieza;

import javax.persistence.ManyToOne;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

public class TipoInsumo extends AuditableEntity<Long> {

	private Long id;
	private String nombre;

	@ManyToOne
	private TipoInsumo padre;

	public TipoInsumo getPadre() {
		return padre;
	}

	public void setPadre(TipoInsumo padre) {
		this.padre = padre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
