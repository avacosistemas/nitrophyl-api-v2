package ar.com.avaco.nitrophyl.ws.service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.ws.dto.ArchivoDTO;
import ar.com.avaco.nitrophyl.ws.dto.LoteDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface LoteEPService extends CRUDEPService<Long, LoteDTO> {

	void aprobar(Long idLote, String estado, String observaciones);

	void rechazar(Long idLote, String observaciones);

	void borrar(Long idLote) throws BusinessException;
	
	ArchivoDTO generarReporteLoteCliente(Long idLote, Long idCliente) throws BusinessException;
}
