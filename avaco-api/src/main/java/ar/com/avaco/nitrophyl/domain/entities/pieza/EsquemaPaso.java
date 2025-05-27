package ar.com.avaco.nitrophyl.domain.entities.pieza;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "ESQUEMA_PASO")
@SequenceGenerator(name = "ESQUEMA_PASO_SEQ", sequenceName = "ESQUEMA_PASO_SEQ", allocationSize = 1)
public class EsquemaPaso extends AuditableEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESQUEMA_PASO_SEQ")
	@Column(name = "ID_ESQUEMA_PASO", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	private Esquema esquema;
	private Long paso;
	private String descripcion;

	public EsquemaPaso clonar(Esquema esquema) {
		EsquemaPaso pasoEsquema = new EsquemaPaso();
		pasoEsquema.setDescripcion(descripcion);
		pasoEsquema.setPaso(paso);
		pasoEsquema.setEsquema(esquema);
		return pasoEsquema;
	}

	public Esquema getEsquema() {
		return esquema;
	}

	public void setEsquema(Esquema esquema) {
		this.esquema = esquema;
	}

	public Long getPaso() {
		return paso;
	}

	public void setPaso(Long paso) {
		this.paso = paso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
