package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.List;

public class InsumoTratado {

	private Pieza pieza;

	private Insumo insumo;

	private Tratamiento tratamiento;

	private List<Adhesivo> adhesivos;

	public Pieza getPieza() {
		return pieza;
	}

	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}

	public Insumo getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}

	public Tratamiento getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(Tratamiento tratamiento) {
		this.tratamiento = tratamiento;
	}

	public List<Adhesivo> getAdhesivos() {
		return adhesivos;
	}

	public void setAdhesivos(List<Adhesivo> adhesivos) {
		this.adhesivos = adhesivos;
	}
}
