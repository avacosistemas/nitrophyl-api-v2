package ar.com.avaco.nitrophyl.service.maquina;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.maquina.MaquinaPrueba;
import ar.com.avaco.nitrophyl.repository.maquina.MaquinaPruebaRepository;

@Transactional
@Service("maquinaPruebaService")
public class MaquinaPruebaServiceImpl extends NJBaseService<Long, MaquinaPrueba, MaquinaPruebaRepository>
		implements MaquinaPruebaService {

	@Resource(name = "maquinaPruebaRepository")
	public void setMaquinaRepository(MaquinaPruebaRepository maquinaPruebaRepository) {
		this.repository = maquinaPruebaRepository;
	}

}
