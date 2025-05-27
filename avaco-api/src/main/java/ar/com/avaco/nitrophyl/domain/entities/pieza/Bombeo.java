package ar.com.avaco.nitrophyl.domain.entities.pieza;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "BOMBEO")
@SequenceGenerator(name = "BOMBEO_SEQ", sequenceName = "BOMBEO_SEQ", allocationSize = 1)
public class Bombeo extends AuditableEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOMBEO_SEQ")
	@Column(name = "ID_BOMBEO", unique = true, nullable = false)
	private Long id;

	private Moldeo moldeo;

	private TipoBombeo tipo;

	private Integer cantidad;

	private Double presion;

	public Bombeo clonar(Moldeo moldeo) {
		Bombeo bombeo = new Bombeo();
		bombeo.setCantidad(cantidad);
		bombeo.setMoldeo(moldeo);
		bombeo.setPresion(presion);
		bombeo.setTipo(tipo);
		return bombeo;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPresion() {
		return presion;
	}

	public void setPresion(Double presion) {
		this.presion = presion;
	}

	public Moldeo getMoldeo() {
		return moldeo;
	}

	public void setMoldeo(Moldeo moldeo) {
		this.moldeo = moldeo;
	}

	public TipoBombeo getTipo() {
		return tipo;
	}

	public void setTipo(TipoBombeo tipo) {
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
