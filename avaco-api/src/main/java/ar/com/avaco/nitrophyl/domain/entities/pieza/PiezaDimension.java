package ar.com.avaco.nitrophyl.domain.entities.pieza;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.avaco.nitrophyl.domain.entities.moldes.TipoDimension;

@Entity
@Table(name = "PIEZA_DIMENSION")
@SequenceGenerator(name = "PIEZA_DIMENSION_SEQ", sequenceName = "PIEZA_DIMENSION_SEQ", allocationSize = 1)
public class PiezaDimension extends ar.com.avaco.arc.core.domain.Entity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PIEZA_DIMENSION_SEQ")
	@Column(name = "ID_PIEZA_DIMENSION", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	private Pieza pieza;

	private TipoDimension tipodimension;

	private Integer valordimension;

	private String observaciones;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pieza getPieza() {
		return pieza;
	}

	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}

	public TipoDimension getTipodimension() {
		return tipodimension;
	}

	public void setTipodimension(TipoDimension tipodimension) {
		this.tipodimension = tipodimension;
	}

	public Integer getValordimension() {
		return valordimension;
	}

	public void setValordimension(Integer valordimension) {
		this.valordimension = valordimension;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public PiezaDimension clonar(Pieza pieza) {
		PiezaDimension p = new PiezaDimension();
		p.setPieza(pieza);
		p.setTipodimension(this.tipodimension);
		p.setValordimension(this.valordimension);
		p.setObservaciones(this.observaciones);
		return p;
	}

}
