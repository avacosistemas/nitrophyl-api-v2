package ar.com.avaco.nitrophyl.domain.entities.pieza;

public class Bombeo {

	private Moldeo moldeo;

	private TipoBombeo tipo;

	private Integer cantidad;
	
	private Double presion;

	public Moldeo getMoldeo() {
		return moldeo;
	}

	public void setMoldeo(Moldeo moldeo) {
		this.moldeo = moldeo;
	}

	public TipoBombeo getTipo() {
		return tipo;
	}

	public void setTipo(TipoBombeo tipo) {
		this.tipo = tipo;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}
	
}
