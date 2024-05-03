package ar.com.avaco.nitrophyl.ws.service;

import ar.com.avaco.nitrophyl.ws.dto.LoteDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface LoteEPService extends CRUDEPService<Long, LoteDTO> {

	void aprobar(Long idLote, String estado, String observaciones, String fecha);

	void rechazar(Long idLote, String observaciones, String fecha);

	void borrar(Long idLote) throws Exception;

}
