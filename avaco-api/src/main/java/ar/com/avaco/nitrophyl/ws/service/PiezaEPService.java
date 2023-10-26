
package ar.com.avaco.nitrophyl.ws.service;


import ar.com.avaco.nitrophyl.ws.dto.PiezaDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface PiezaEPService extends CRUDEPService<Long, PiezaDTO> {

	PiezaDTO addPiezaToCompuesta(Long id, Long idPieza);

	PiezaDTO removePiezaFromCompuesta(Long id, Long idPieza);

	PiezaDTO addPiezaToProductoCompuesto(Long idProductoCompuesto, Long idPieza);

	PiezaDTO removePiezaFromProductoCompuesto(Long idProductoCompuesto, Long idPieza);
	
}
