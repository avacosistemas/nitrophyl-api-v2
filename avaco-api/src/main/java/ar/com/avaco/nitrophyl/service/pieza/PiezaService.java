package ar.com.avaco.nitrophyl.service.pieza;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;

public interface PiezaService extends NJService<Long, Pieza> {

	Pieza addPiezaToCompuesta(Long id, Long idPieza);

	Pieza removePiezaFromCompuesta(Long id, Long idPieza);

}
