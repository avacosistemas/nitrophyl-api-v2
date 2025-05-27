package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "INSUMO_TRATADO")
@SequenceGenerator(name = "INSUMO_TRATADO_SEQ", sequenceName = "INSUMO_TRATADO_SEQ", allocationSize = 1)
public class InsumoTratado extends AuditableEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INSUMO_TRATADO_SEQ")
	@Column(name = "ID_INSUMO_TRATADO", unique = true, nullable = false)
	private Long id;

	private Pieza pieza;

	@ManyToOne
	private Insumo insumo;

	@ManyToOne
	private Tratamiento tratamiento;

	@ManyToMany
	private List<Adhesivo> adhesivos;

	private String observaciones;

	public Pieza getPieza() {
		return pieza;
	}

	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}

	public Insumo getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}

	public Tratamiento getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(Tratamiento tratamiento) {
		this.tratamiento = tratamiento;
	}

	public List<Adhesivo> getAdhesivos() {
		return adhesivos;
	}

	public void setAdhesivos(List<Adhesivo> adhesivos) {
		this.adhesivos = adhesivos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public InsumoTratado clonar(Pieza pieza) {
		InsumoTratado ins = new InsumoTratado();
		ins.setAdhesivos(this.adhesivos);
		ins.setInsumo(this.insumo);
		ins.setPieza(pieza);
		ins.setTratamiento(this.tratamiento);
		ins.setObservaciones(this.observaciones);
		return ins;
	}

}
