package ar.com.avaco.nitrophyl.service.lote;

import java.util.Date;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;

public interface LoteService extends NJService<Long, Lote> {

	void aprobar(Long idLote, String estado, String observaciones, Date fecha);

	void rechazar(Long idLote, String observaciones, Date fecha);

	void borrar(Long idLote);

	boolean hasEnsayos(Long idLote);

}
