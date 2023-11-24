package ar.com.avaco.nitrophyl.service.lote;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;
import ar.com.avaco.nitrophyl.repository.lote.LoteRepository;
import ar.com.avaco.utils.DateUtils;

@Transactional
@Service("loteService")
public class LoteServiceImpl extends NJBaseService<Long, Lote, LoteRepository> implements LoteService {


	@Resource(name = "loteRepository")
	public void setLoteRepository(LoteRepository loteRepository) {
		this.repository = loteRepository;
	}

	@Override
	public Lote save(Lote entity) {
		entity.setFecha(DateUtils.getFechaYHoraActual());
		return super.save(entity);
	}
	
}
