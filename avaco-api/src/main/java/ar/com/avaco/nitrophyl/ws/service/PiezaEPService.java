
package ar.com.avaco.nitrophyl.ws.service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.ws.dto.PiezaCreacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface PiezaEPService extends CRUDEPService<Long, PiezaDTO> {

	void create(PiezaCreacionDTO dto);

	void marcarVigente(Long piezaId);

	void nuevaRevision(Long piezaId);

}
