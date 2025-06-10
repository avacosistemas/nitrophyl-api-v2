package ar.com.avaco.nitrophyl.domain.entities.pieza;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "BOMBEO")
@SequenceGenerator(name = "BOMBEO_SEQ", sequenceName = "BOMBEO_SEQ", allocationSize = 1)
public class Bombeo extends AuditableEntity<Long> {

	private static final long serialVersionUID = -2376992889027069273L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOMBEO_SEQ")
	@Column(name = "ID_BOMBEO", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_PROCESO")
	private Proceso proceso;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO")
	private TipoBombeo tipo;

	@Column(name = "CANTIDAD")
	private Integer cantidad;

	@Column(name = "PRESION")
	private Double presion;

	public Bombeo clonar(Proceso proceso) {
		Bombeo bombeo = new Bombeo();
		bombeo.setCantidad(cantidad);
		bombeo.setPresion(presion);
		bombeo.setTipo(tipo);
		bombeo.setProceso(proceso);
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

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

}
