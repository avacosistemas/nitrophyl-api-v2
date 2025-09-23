package ar.com.avaco.nitrophyl.service.pieza;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Cotizacion;
import ar.com.avaco.nitrophyl.repository.pieza.CotizacionRepository;

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

}
