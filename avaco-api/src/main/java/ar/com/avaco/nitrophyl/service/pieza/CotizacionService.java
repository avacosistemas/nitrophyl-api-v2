package ar.com.avaco.nitrophyl.service.pieza;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Cotizacion;

public interface CotizacionService extends NJService<Long, Cotizacion> {

	Cotizacion getCotizacionVigente(Long idPiezaCliente);
	
}