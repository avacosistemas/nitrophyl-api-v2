package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.List;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "PIEZA")
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "PIEZA_SEQ", sequenceName = "PIEZA_SEQ", allocationSize = 1)
public abstract class Pieza extends ar.com.avaco.arc.core.domain.Entity<Long> {

	private static final long serialVersionUID = -6870358670680621160L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PIEZA_SEQ")
	@Column(name = "ID_PIEZA", unique = true, nullable = false)
	private Long id;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	@Column(name = "CODIGO", unique = true, nullable = false)
	private String codigoPieza;

	@Column(name = "CODIGO_INTERNO", unique = true, nullable = false)
	private String codigoInterno;

	@Column(name = "DENOMINACION")
	private String denominacion;

	@Column(name = "ESPRODUCTO")
	private Boolean esProducto;

	public abstract List<Pieza> getPiezas();

	@Column(name = "TIPO", nullable = false)
	public TipoPieza tipo;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public boolean isSimple() {
		return tipo.equals(TipoPieza.SIMPLE);
	}

	public boolean isCompuesta() {
		return !isSimple();
	}

	public String getCodigoPieza() {
		return codigoPieza;
	}

	public void setCodigoPieza(String codigoPieza) {
		this.codigoPieza = codigoPieza;
	}

	public String getCodigoInterno() {
		return codigoInterno;
	}

	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = codigoInterno;
	}

	public TipoPieza getTipo() {
		return tipo;
	}

	public void setTipo(TipoPieza tipo) {
		this.tipo = tipo;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public Boolean getEsProducto() {
		return esProducto;
	}

	public void setEsProducto(Boolean esProducto) {
		this.esProducto = esProducto;
	}

}
