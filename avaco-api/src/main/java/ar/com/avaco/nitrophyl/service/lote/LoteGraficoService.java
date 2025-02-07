package ar.com.avaco.nitrophyl.service.lote;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.nitrophyl.domain.entities.moldes.LoteGrafico;
import ar.com.avaco.nitrophyl.ws.dto.ArchivoDTO;

public interface LoteGraficoService extends NJService<Long, LoteGrafico> {

	ArchivoDTO getGraficoByIdLote(Long idLote);

}
