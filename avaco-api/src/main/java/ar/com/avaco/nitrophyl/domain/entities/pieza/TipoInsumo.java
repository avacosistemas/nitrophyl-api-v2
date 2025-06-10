package ar.com.avaco.nitrophyl.domain.entities.pieza;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "TIPO_INSUMO")
@SequenceGenerator(name = "TIPO_INSUMO_SEQ", sequenceName = "TIPO_INSUMO_SEQ", allocationSize = 1)
public class TipoInsumo extends AuditableEntity<Long> {

	private static final long serialVersionUID = 303448339978364112L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPO_INSUMO_SEQ")
	@Column(name = "ID_TIPO_INSUMO", unique = true, nullable = false)
	private Long id;

	@Column(name = "NOMBRE")
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "ID_TIPO_PADRE")
	private TipoInsumo padre;

	public TipoInsumo getPadre() {
		return padre;
	}

	public void setPadre(TipoInsumo padre) {
		this.padre = padre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
