package ar.com.avaco.nitrophyl.service.pieza;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;
import ar.com.avaco.nitrophyl.repository.pieza.PiezaRepository;

@Transactional
@Service("piezaService")
public class PiezaServiceImpl extends NJBaseService<Long, Pieza, PiezaRepository> implements PiezaService {

	private Logger logger = Logger.getLogger(getClass());

	@Resource(name = "piezaRepository")
	void setClienteRepository(PiezaRepository piezaRepository) {
		this.repository = piezaRepository;
	}

	@Override
	public Pieza getVigenteByCodigoInterno(String codigoInterno) {
		return this.repository.findByCodigoInternoAndVigente(codigoInterno, true);
	}

}
