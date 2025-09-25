package ar.com.avaco.nitrophyl.service.pieza;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Cotizacion;
import ar.com.avaco.nitrophyl.repository.pieza.CotizacionRepository;
import ar.com.avaco.nitrophyl.ws.dto.CotizacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.CotizacionFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;

@Transactional
@Service("cotizacionService")
public class CotizacionServiceImpl extends NJBaseService<Long, Cotizacion, CotizacionRepository>
		implements CotizacionService {

	@Resource(name = "cotizacionRepository")
	void setRepository(CotizacionRepository cotizacionRepository) {
		this.repository = cotizacionRepository;
	}

	@Override
	public Cotizacion getCotizacionVigente(Long idPiezaCliente) {
		return this.repository.findFirstByPiezaClienteIdOrderByFechaDesc(idPiezaCliente).orElse(null);
	}

	@Override
	public PageDTO<CotizacionDTO> listCotizaciones(CotizacionFilterDTO filter) {
		return this.repository.list(filter);
	}

}
