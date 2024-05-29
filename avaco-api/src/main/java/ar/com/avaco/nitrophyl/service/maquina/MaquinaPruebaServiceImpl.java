package ar.com.avaco.nitrophyl.service.maquina;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.maquina.Maquina;
import ar.com.avaco.nitrophyl.domain.entities.maquina.MaquinaPrueba;
import ar.com.avaco.nitrophyl.repository.maquina.MaquinaPruebaRepository;
import ar.com.avaco.nitrophyl.repository.maquina.MaquinaRepository;

@Transactional
@Service("maquinaPruebaService")
public class MaquinaPruebaServiceImpl extends NJBaseService<Long, MaquinaPrueba, MaquinaPruebaRepository>
		implements MaquinaPruebaService {

	private MaquinaRepository maquinaRepository;

	@Override
	public List<MaquinaPrueba> listByMaquina(Long idMaquina) {
		return this.repository.findByIdMaquina(idMaquina);
	}

	@Override
	public void updatePruebasByMaquina(Long idMaquina, List<String> pruebas) {
		List<MaquinaPrueba> list = new ArrayList<MaquinaPrueba>();
		pruebas.forEach(x -> list.add(new MaquinaPrueba(x, idMaquina)));
		Maquina maquina = this.maquinaRepository.getOne(idMaquina);
		maquina.getPruebas().clear();
		maquina.getPruebas().addAll(list);
		this.maquinaRepository.save(maquina);
	}

	@Resource(name = "maquinaPruebaRepository")
	public void setMaquinaPruebaRepository(MaquinaPruebaRepository maquinaPruebaRepository) {
		this.repository = maquinaPruebaRepository;
	}

	@Resource(name = "maquinaRepository")
	public void setMaquinaRepository(MaquinaRepository maquinaRepository) {
		this.maquinaRepository = maquinaRepository;
	}

}
