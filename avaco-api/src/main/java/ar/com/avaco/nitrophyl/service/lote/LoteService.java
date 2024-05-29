package ar.com.avaco.nitrophyl.service.lote;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;

public interface LoteService extends NJService<Long, Lote> {

	void aprobar(Long idLote, String estado, String observaciones);

	void rechazar(Long idLote, String observaciones);

	Lote getLoteCompleto(Long idLote);

}
