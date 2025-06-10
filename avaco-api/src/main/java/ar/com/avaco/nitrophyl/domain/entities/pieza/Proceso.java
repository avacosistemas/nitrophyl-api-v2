package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;
import ar.com.avaco.nitrophyl.domain.entities.fabrica.Prensa;

@Entity
@Table(name = "PROCESO")
@SequenceGenerator(name = "PROCESO_SEQ", sequenceName = "PROCESO_SEQ", allocationSize = 1)
public class Proceso extends AuditableEntity<Long> {

	private static final long serialVersionUID = -9161533934017681173L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROCESO_SEQ")
	@Column(name = "ID_PROCESO", unique = true, nullable = false)
	private Long id;

	@Embedded
	private Precalentamiento precalentamiento;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "PROCESO_PRENSA", joinColumns = @JoinColumn(name = "ID_PROCESO", referencedColumnName = "ID_PROCESO"), inverseJoinColumns = @JoinColumn(name = "ID_PRENSA", referencedColumnName = "ID_PRENSA"))
	@Fetch(FetchMode.SELECT)
	private Set<Prensa> prensas = new HashSet<>();

	@Embedded
	private Vulcanizacion vulcanizacion;

	@OneToMany
	private Set<Bombeo> bombeos;

	@Column(name = "DESMOLDANTE")
	private String desmoldante;

	@Column(name = "POSTCURA")
	private String postCura;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_TERMINACION", referencedColumnName = "ID_TERMINACION", unique = true)
	private Terminacion terminacion;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "proceso")
	private List<Esquema> esquema;

	public Proceso clonar(Pieza pieza) {
		Proceso proceso = new Proceso();
		proceso.setDesmoldante(this.desmoldante);
		this.esquema.forEach(esquema -> this.esquema.add(esquema.clonar(proceso)));
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

	public Precalentamiento getPrecalentamiento() {
		return precalentamiento;
	}

	public void setPrecalentamiento(Precalentamiento precalentamiento) {
		this.precalentamiento = precalentamiento;
	}

	public Set<Prensa> getPrensas() {
		return prensas;
	}

	public void setPrensas(Set<Prensa> prensas) {
		this.prensas = prensas;
	}

	public Vulcanizacion getVulcanizacion() {
		return vulcanizacion;
	}

	public void setVulcanizacion(Vulcanizacion vulcanizacion) {
		this.vulcanizacion = vulcanizacion;
	}

	public Set<Bombeo> getBombeos() {
		return bombeos;
	}

	public void setBombeos(Set<Bombeo> bombeos) {
		this.bombeos = bombeos;
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
