package ar.com.avaco.nitrophyl.domain.entities.moldes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;

@Entity
@Table(name = "LOTE_GRAFICO")
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "LOTE_GRAFICO_SEQ", sequenceName = "LOTE_GRAFICO_SEQ", allocationSize = 1)

public class LoteGrafico extends ar.com.avaco.arc.core.domain.Entity<Long> {

	private static final long serialVersionUID = -1452187713424215163L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOTE_GRAFICO_SEQ")
	@Column(name = "ID_LOTE_GRAFICO", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_LOTE", insertable = false, updatable = false)
	private Lote lote;

	@Column(name = "ID_LOTE")
	private Long idLote;

	@Column(name = "FECHA")
	private Date fecha;

	@Column(name = "ARCHIVO", nullable = true)
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] archivo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public Long getIdLote() {
		return idLote;
	}

	public void setIdLote(Long idLote) {
		this.idLote = idLote;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

}
