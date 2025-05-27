package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.moldes.Molde;
import ar.com.avaco.utils.DateUtils;

@Entity
@Table(name = "PIEZA")
@SequenceGenerator(name = "PIEZA_SEQ", sequenceName = "PIEZA_SEQ", allocationSize = 1)
public class Pieza extends AuditableEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PIEZA_SEQ")
	@Column(name = "ID_PIEZA", unique = true, nullable = false)
	private Long id;
	
	private Formula formula;
	private String denominacion;
	private PiezaTipo tipo;
	
	@OneToMany
	private Set<PiezaDimension> dimensiones = new HashSet<>();

	// Empleado para agrupar la misma pieza/proceso con diferentes revisiones
	private String codigoInterno;

	@ManyToMany
	private Set<PiezaPlano> planos = new HashSet<>();

	@OneToMany
	private List<InsumoTratado> insumos;

	@ManyToMany
	private List<Molde> moldes;

	@OneToOne
	private Proceso proceso;

	private Long revision;
	private Date fechaRevision;
	private Boolean vigente;
	private Date fechaCreacionPiezaProceso;
	private String observacionesRevision;

	private Long dureza;
	private Long espesorMinimo;
	private Long espesorMaximo;
	private Long pesoCrudo;
	private String observacionesPesoCrudo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Formula getFormula() {
		return formula;
	}

	public void setFormula(Formula formula) {
		this.formula = formula;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public PiezaTipo getTipo() {
		return tipo;
	}

	public void setTipo(PiezaTipo tipo) {
		this.tipo = tipo;
	}

	public String getObservacionesRevision() {
		return observacionesRevision;
	}

	public void setObservacionesRevision(String observacionesRevision) {
		this.observacionesRevision = observacionesRevision;
	}

	public Set<PiezaDimension> getDimensiones() {
		return dimensiones;
	}

	public Set<PiezaPlano> getPlanos() {
		return planos;
	}

	public void setPlanos(Set<PiezaPlano> planos) {
		this.planos = planos;
	}

	public Boolean getVigente() {
		return vigente;
	}

	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}

	public void setDimensiones(Set<PiezaDimension> dimensiones) {
		this.dimensiones = dimensiones;
	}

	public List<InsumoTratado> getInsumos() {
		return insumos;
	}

	public void setInsumos(List<InsumoTratado> insumos) {
		this.insumos = insumos;
	}

	public List<Molde> getMoldes() {
		return moldes;
	}

	public void setMoldes(List<Molde> moldes) {
		this.moldes = moldes;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Long getRevision() {
		return revision;
	}

	public void setRevision(Long revision) {
		this.revision = revision;
	}

	public Date getFechaRevision() {
		return fechaRevision;
	}

	public void setFechaRevision(Date fechaRevision) {
		this.fechaRevision = fechaRevision;
	}

	public Date getFechaCreacionPiezaProceso() {
		return fechaCreacionPiezaProceso;
	}

	public void setFechaCreacionPiezaProceso(Date fechaCreacionPiezaProceso) {
		this.fechaCreacionPiezaProceso = fechaCreacionPiezaProceso;
	}

	public Long getDureza() {
		return dureza;
	}

	public void setDureza(Long dureza) {
		this.dureza = dureza;
	}

	public Long getEspesorMinimo() {
		return espesorMinimo;
	}

	public void setEspesorMinimo(Long espesorMinimo) {
		this.espesorMinimo = espesorMinimo;
	}

	public Long getEspesorMaximo() {
		return espesorMaximo;
	}

	public void setEspesorMaximo(Long espesorMaximo) {
		this.espesorMaximo = espesorMaximo;
	}

	public Long getPesoCrudo() {
		return pesoCrudo;
	}

	public void setPesoCrudo(Long pesoCrudo) {
		this.pesoCrudo = pesoCrudo;
	}

	public String getObservacionesPesoCrudo() {
		return observacionesPesoCrudo;
	}

	public void setObservacionesPesoCrudo(String observacionesPesoCrudo) {
		this.observacionesPesoCrudo = observacionesPesoCrudo;
	}

	public String getCodigoInterno() {
		return codigoInterno;
	}

	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = codigoInterno;
	}

	public Pieza clonar(String username) {
		Pieza pieza = new Pieza();
		pieza.setDenominacion(this.denominacion);
		this.dimensiones.forEach(dimension -> pieza.getDimensiones().add(dimension.clonar(pieza)));
		pieza.setFormula(this.formula);
		this.insumos.forEach(insumo -> pieza.getInsumos().add(insumo.clonar(pieza)));
		pieza.setMoldes(this.moldes);
		pieza.setPlanos(this.planos);
		pieza.setProceso(this.proceso.clonar(pieza));

		pieza.setTipo(this.tipo);

		// Incremento la revisión y seteo la fecha actual
		pieza.setRevision(revision + 1);
		pieza.setFechaRevision(DateUtils.getFechaYHoraActual());
		// No es la vigente, debera setearse a demanda
		pieza.setVigente(false);

		// Usuario y fecha de creación de la nueva revision
		pieza.setUsuarioCreacion(username);
		pieza.setFechaCreacion(DateUtils.getFechaYHoraActual());

		// Usuario y fecha de creación de la nueva revision igual a la creacion
		pieza.setUsuarioActualizacion(username);
		pieza.setFechaActualizacion(DateUtils.getFechaYHoraActual());

		pieza.setDureza(this.dureza);
		pieza.setEspesorMaximo(this.espesorMaximo);
		pieza.setEspesorMinimo(this.espesorMinimo);
		pieza.setObservacionesPesoCrudo(this.observacionesPesoCrudo);
		pieza.setPesoCrudo(this.pesoCrudo);
		
		pieza.setCodigoInterno(this.codigoInterno);

		return pieza;
	}

}
