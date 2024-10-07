package ar.com.avaco.nitrophyl.domain.entities.reporte;

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

import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.maquina.Maquina;

@Entity
@Table(name = "REPORTE_LOTE_CONF_CLIENTE")
@SequenceGenerator(name = "REPORTE_LOTE_CONF_CLIENTE_SEQ", sequenceName = "REPORTE_LOTE_CONF_CLIENTE_SEQ", allocationSize = 1)
public class ReporteLoteConfiguracionCliente extends ar.com.avaco.arc.core.domain.Entity<Long> {

	private static final long serialVersionUID = -8915059528176139461L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPORTE_LOTE_CONF_CLIENTE_SEQ")
	@Column(name = "ID_REPORTE_LOTE_CONF_CLIENTE")
	private Long id;

	@JoinColumn(name = "ID_CLIENTE", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;

	@JoinColumn(name = "ID_FORMULA", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Formula formula;

	@JoinColumn(name = "ID_MAQUINA", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Maquina maquina;

	@Column(name = "MOSTRAR_PARAMETROS")
	private boolean mostrarParametros;

	@Column(name = "MOSTRAR_RESULTADOS")
	private boolean mostrarResultados;

	@Column(name = "MOSTRAR_CONDICIONES")
	private boolean mostrarCondiciones;

	@Column(name = "MOSTRAR_OBS_PARAM")
	private boolean mostrarObservacionesParametro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Formula getFormula() {
		return formula;
	}

	public void setFormula(Formula formula) {
		this.formula = formula;
	}

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public boolean isMostrarParametros() {
		return mostrarParametros;
	}

	public void setMostrarParametros(boolean mostrarParametros) {
		this.mostrarParametros = mostrarParametros;
	}

	public boolean isMostrarResultados() {
		return mostrarResultados;
	}

	public void setMostrarResultados(boolean mostrarResultados) {
		this.mostrarResultados = mostrarResultados;
	}

	public boolean isMostrarCondiciones() {
		return mostrarCondiciones;
	}

	public void setMostrarCondiciones(boolean mostrarCondiciones) {
		this.mostrarCondiciones = mostrarCondiciones;
	}

	public boolean isMostrarObservacionesParametro() {
		return mostrarObservacionesParametro;
	}

	public void setMostrarObservacionesParametro(boolean mostrarObservacionesParametro) {
		this.mostrarObservacionesParametro = mostrarObservacionesParametro;
	}

}