package ar.com.avaco.nitrophyl.service.molde;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaTipo;
import ar.com.avaco.nitrophyl.repository.pieza.PiezaTipoRepository;

@Service("piezaTipoService")
public class PiezaTipoServiceImpl extends NJBaseService<Long, PiezaTipo, PiezaTipoRepository> implements PiezaTipoService {

	@Resource(name = "piezaTipoRepository")
	public void setPiezaTipoRepository(PiezaTipoRepository repository) {
		this.repository = repository;
	}

}
