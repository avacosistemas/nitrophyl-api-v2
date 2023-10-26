package ar.com.avaco.nitrophyl.domain.entities.formula;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.avaco.nitrophyl.domain.entities.maquina.Maquina;

@Entity
@Table(name = "CONF_PRUEBA")
@SequenceGenerator(name = "CONF_PRUEBA_SEQ", sequenceName = "CONF_PRUEBA_SEQ", allocationSize = 1)
public class ConfiguracionPrueba extends ar.com.avaco.arc.core.domain.Entity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2296428532611007942L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONF_PRUEBA_SEQ")
	@Column(name = "ID_CONF_PRUEBA", unique = true, nullable = false)
	private Long id;

	@Column(name = "FECHA")
	private Date fecha;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_MAQUINA")
	private Maquina maquina;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_FORMULA")
	private Formula formula;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "configuracionPrueba")
	private Set<ConfiguracionPruebaParametro> parametros = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "configuracionPrueba")
	private Set<ConfiguracionPruebaCondicion> condiciones = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public Formula getFormula() {
		return formula;
	}

	public void setFormula(Formula formula) {
		this.formula = formula;
	}

	public Set<ConfiguracionPruebaParametro> getParametros() {
		return parametros;
	}

	public void setParametros(Set<ConfiguracionPruebaParametro> parametros) {
		this.parametros = parametros;
	}

	public Set<ConfiguracionPruebaCondicion> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(Set<ConfiguracionPruebaCondicion> condiciones) {
		this.condiciones = condiciones;
	}

}
