package ar.com.avaco.nitrophyl.domain.entities.pieza;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;
import ar.com.avaco.nitrophyl.domain.entities.moldes.PlanoClasificacion;

@Entity
@Table(name = "PIEZA_PLANO")
@SequenceGenerator(name = "PIEZA_PLANO_SEQ", sequenceName = "PIEZA_PLANO_SEQ", allocationSize = 1)
public class PiezaPlano extends AuditableEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PIEZA_PLANO_SEQ")
	@Column(name = "ID_PIEZA_PLANO", unique = true, nullable = false)
	private Long id;

	@Column(name = "ARCHIVO", nullable = true)
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] archivo;

	private String codigo;

	@Column(name = "REVISION")
	private Integer revision;

	@Enumerated(EnumType.STRING)
	private PlanoClasificacion clasificacion;

	@Column(name = "OBSERVACIONES")
	private String observaciones;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getRevision() {
		return revision;
	}

	public void setRevision(Integer revision) {
		this.revision = revision;
	}

	public PlanoClasificacion getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(PlanoClasificacion clasificacion) {
		this.clasificacion = clasificacion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
