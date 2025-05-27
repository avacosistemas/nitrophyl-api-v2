package ar.com.avaco.nitrophyl.domain.entities.pieza;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;

@Entity
@Table(name = "ADHESIVO")
@SequenceGenerator(name = "ADHESIVO_SEQ", sequenceName = "ADHESIVO_SEQ", allocationSize = 1)
public class Adhesivo extends AuditableEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADHESIVO_SEQ")
	@Column(name = "ID_ADHESIVO", unique = true, nullable = false)
	private Long id;

	private String nombre;

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
