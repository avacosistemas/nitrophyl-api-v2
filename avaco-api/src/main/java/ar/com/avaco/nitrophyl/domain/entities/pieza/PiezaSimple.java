package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "PIEZASIMPLE")
@PrimaryKeyJoinColumn(name="id_pieza")
public class PiezaSimple extends Pieza {

	/**
	 * 
	 */
	private static final long serialVersionUID = -232654198835012598L;

	public PiezaSimple() {
		super.tipo = TipoPieza.SIMPLE;
	}
	
	@Transient
	public List<Pieza> getPiezas() {
		throw new RuntimeException("Las piezas simples no contienen otras piezas");
	}

}
