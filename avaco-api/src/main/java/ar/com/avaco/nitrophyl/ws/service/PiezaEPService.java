
package ar.com.avaco.nitrophyl.ws.service;

import ar.com.avaco.nitrophyl.ws.dto.PageDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaCreacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaGrillaDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface PiezaEPService extends CRUDEPService<Long, PiezaDTO> {

	void create(PiezaCreacionDTO dto);

	void marcarVigente(Long piezaId);

	void nuevaRevision(Long piezaId);

	PageDTO<PiezaGrillaDTO> listGrilla(PiezaFilterDTO filter);

}
