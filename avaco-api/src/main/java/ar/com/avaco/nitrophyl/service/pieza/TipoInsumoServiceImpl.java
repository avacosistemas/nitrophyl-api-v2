package ar.com.avaco.nitrophyl.service.pieza;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.TipoInsumo;
import ar.com.avaco.nitrophyl.repository.pieza.TipoInsumoRepository;

@Transactional
@Service("tipoInsumoService")
public class TipoInsumoServiceImpl extends NJBaseService<Long, TipoInsumo, TipoInsumoRepository>
		implements TipoInsumoService {

	@Resource(name = "tipoInsumoRepository")
	void setClienteRepository(TipoInsumoRepository tipoInsumoRepository) {
		this.repository = tipoInsumoRepository;
	}

}
