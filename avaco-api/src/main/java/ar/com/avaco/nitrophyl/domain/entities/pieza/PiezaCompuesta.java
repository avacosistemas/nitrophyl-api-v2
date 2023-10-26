package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "piezaCompuesta")
@PrimaryKeyJoinColumn(name = "id_pieza")
public class PiezaCompuesta extends Pieza {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7799614671451241100L;

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "piezaDePieza", joinColumns = {
			@JoinColumn(name = "id_pieza_compuesta") }, inverseJoinColumns = { @JoinColumn(name = "id_pieza") })
	private List<Pieza> piezas;

	public List<Pieza> getPiezas() {
		return piezas;
	}

	public void setPiezas(List<Pieza> piezas) {
		this.piezas = piezas;
	}

	public PiezaCompuesta() {
		super.tipo = TipoPieza.COMPUESTA;
	}

}
