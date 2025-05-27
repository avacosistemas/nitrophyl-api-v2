package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.List;

import javax.persistence.Column;
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
@Table(name = "ESQUEMA")
@SequenceGenerator(name = "ESQUEMA_SEQ", sequenceName = "ESQUEMA_SEQ", allocationSize = 1)
public class Esquema extends AuditableEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESQUEMA_SEQ")
	@Column(name = "ID_ESQUEMA", unique = true, nullable = false)
	private Long id;
	
	@ManyToOne
	private Proceso proceso;
	
	private String titulo;
	
	@OneToMany
	private List<EsquemaPaso> pasos;
	
	private byte[] imagen;

	public Esquema clonar(Proceso proceso) {
		Esquema esquema = new Esquema();
		esquema.setImagen(imagen);
		pasos.forEach(paso -> esquema.getPasos().add(paso.clonar(esquema)));
		esquema.setProceso(proceso);
		esquema.setTitulo(titulo);
		return esquema;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public List<EsquemaPaso> getPasos() {
		return pasos;
	}

	public void setPasos(List<EsquemaPaso> pasos) {
		this.pasos = pasos;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
