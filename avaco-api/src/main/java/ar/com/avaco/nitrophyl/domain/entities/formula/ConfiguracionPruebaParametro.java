package ar.com.avaco.nitrophyl.domain.entities.formula;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CONF_PRUEBA_PARAM")
@SequenceGenerator(name = "CONF_PRUEBA_PARAM_SEQ", sequenceName = "CONF_PRUEBA_PARAM_SEQ", allocationSize = 1)
public class ConfiguracionPruebaParametro extends ar.com.avaco.arc.core.domain.Entity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2296428532611007942L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONF_PRUEBA_PARAM_SEQ")
	@Column(name = "ID_CONF_PRUEBA_PARAM", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_CONF_PRUEBA")
	private ConfiguracionPrueba configuracionPrueba;

//	@Column(name = "ID_CONF_PRUEBA")
//	private Long idConfiguracionPrueba;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "VALOR_MINIMO")
	private Double minimo;

	@Column(name = "VALOR_MAXIMO")
	private Double maximo;

	public ConfiguracionPruebaParametro() {
	}

	public ConfiguracionPruebaParametro(ConfiguracionPrueba configuracionPrueba, String nombre, Double minimo,
			Double maximo) {
		super();
		this.nombre = nombre;
		this.minimo = minimo;
		this.maximo = maximo;
		this.configuracionPrueba = configuracionPrueba;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ConfiguracionPrueba getConfiguracionPrueba() {
		return configuracionPrueba;
	}

	public void setConfiguracionPrueba(ConfiguracionPrueba configuracionPrueba) {
		this.configuracionPrueba = configuracionPrueba;
	}

//	public Long getIdConfiguracionPrueba() {
//		return idConfiguracionPrueba;
//	}
//
//	public void setIdConfiguracionPrueba(Long idConfiguracionPrueba) {
//		this.idConfiguracionPrueba = idConfiguracionPrueba;
//	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getMinimo() {
		return minimo;
	}

	public void setMinimo(Double minimo) {
		this.minimo = minimo;
	}

	public Double getMaximo() {
		return maximo;
	}

	public void setMaximo(Double maximo) {
		this.maximo = maximo;
	}

}
