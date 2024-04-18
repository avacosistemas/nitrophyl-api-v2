package ar.com.avaco.nitrophyl.domain.entities.lote;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "ENSAYO")
@SequenceGenerator(name = "ENSAYO_SEQ", sequenceName = "ENSAYO_SEQ", allocationSize = 1)
public class Ensayo extends ar.com.avaco.arc.core.domain.Entity<Long> {

	private static final long serialVersionUID = 3379140593827986759L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENSAYO_SEQ")
	@Column(name = "ID_ENSAYO", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_LOTE")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Lote lote;

	@Column(name = "fecha")
	private Date fecha;

	@Column(name = "observaciones")
	private String observaciones;

	@Column(name = "maquina")
	private String maquina;

	@Column(name = "condiciones")
	private String condiciones;

	@Column(name = "estado")
	@Enumerated(EnumType.STRING)
	private EstadoEnsayo estado;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "ensayo")
	private Set<EnsayoResultado> resultados = new HashSet<>();

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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getMaquina() {
		return maquina;
	}

	public void setMaquina(String maquina) {
		this.maquina = maquina;
	}

	public Set<EnsayoResultado> getResultados() {
		return resultados;
	}

	public void setResultados(Set<EnsayoResultado> resultados) {
		this.resultados = resultados;
	}

	public String getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(String condiciones) {
		this.condiciones = condiciones;
	}

	public EstadoEnsayo getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnsayo estado) {
		this.estado = estado;
	}

}
