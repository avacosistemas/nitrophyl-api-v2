package ar.com.avaco.nitrophyl.domain.entities.lote;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPruebaParametro;

@Entity
@Table(name = "ENSAYO_RESULTADO")
@SequenceGenerator(name = "ENSAYO_RESULTADO_SEQ", sequenceName = "ENSAYO_RESULTADO_SEQ", allocationSize = 1)
public class EnsayoResultado extends ar.com.avaco.arc.core.domain.Entity<Long> {

	private static final long serialVersionUID = 7613782310720480769L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENSAYO_RESULTADO_SEQ")
	@Column(name = "ID_ENSAYO_RESULTADO", unique = true, nullable = false)
	private Long id;

	@Column(name = "resultado")
	private Double resultado;

	@Column(name = "redondeo")
	private Double redondeo;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_ENSAYO")
	private Ensayo ensayo;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_CONF_PRUEBA_PARAM")
	private ConfiguracionPruebaParametro configuracionPruebaParametro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getResultado() {
		return resultado;
	}

	public void setResultado(Double resultado) {
		this.resultado = resultado;
	}

	public Double getRedondeo() {
		return redondeo;
	}

	public void setRedondeo(Double redondeo) {
		this.redondeo = redondeo;
	}

	public Ensayo getEnsayo() {
		return ensayo;
	}

	public void setEnsayo(Ensayo ensayo) {
		this.ensayo = ensayo;
	}

	public ConfiguracionPruebaParametro getConfiguracionPruebaParametro() {
		return configuracionPruebaParametro;
	}

	public void setConfiguracionPruebaParametro(ConfiguracionPruebaParametro configuracionPruebaParametro) {
		this.configuracionPruebaParametro = configuracionPruebaParametro;
	}

}
