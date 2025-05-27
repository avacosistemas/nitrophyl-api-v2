package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "PROCESO")
@SequenceGenerator(name = "PROCESO_SEQ", sequenceName = "PROCESO_SEQ", allocationSize = 1)
public class Proceso extends AuditableEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROCESO_SEQ")
	@Column(name = "ID_PROCESO", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	private Pieza pieza;

	@Embedded
	private Moldeo moldeo;

	private String desmoldante;

	private String postCura;

	@Embedded
	private Terminacion terminacion;

	@OneToMany
	private List<Esquema> esquema;

	public Proceso clonar(Pieza pieza) {
		Proceso proceso = new Proceso();
		proceso.setDesmoldante(this.desmoldante);
		this.esquema.forEach(esquema -> this.esquema.add(esquema.clonar(proceso)));
		proceso.setMoldeo(moldeo.clonar(proceso));
		proceso.setPieza(pieza);
		proceso.setPostCura(postCura);
		proceso.setTerminacion(terminacion);
		return proceso;
	}

	public Terminacion getTerminacion() {
		return terminacion;
	}

	public void setTerminacion(Terminacion terminacion) {
		this.terminacion = terminacion;
	}

	public Pieza getPieza() {
		return pieza;
	}

	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}

	public Moldeo getMoldeo() {
		return moldeo;
	}

	public void setMoldeo(Moldeo moldeo) {
		this.moldeo = moldeo;
	}

	public String getDesmoldante() {
		return desmoldante;
	}

	public void setDesmoldante(String desmoldante) {
		this.desmoldante = desmoldante;
	}

	public String getPostCura() {
		return postCura;
	}

	public void setPostCura(String postCura) {
		this.postCura = postCura;
	}

	public List<Esquema> getEsquema() {
		return esquema;
	}

	public void setEsquema(List<Esquema> esquema) {
		this.esquema = esquema;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
