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
@Service("maquinaService")
public class MaquinaServiceImpl extends NJBaseService<Long, Maquina, MaquinaRepository> implements MaquinaService {

	private MaquinaPruebaRepository maquinaPruebaRepository;

	@Override
	public void updatePruebasByMaquina(Long idMaquina, List<String> pruebas) {
		List<MaquinaPrueba> list = new ArrayList<MaquinaPrueba>();
		pruebas.forEach(x -> list.add(new MaquinaPrueba(x, idMaquina)));
		Maquina maquina = this.repository.getOne(idMaquina);
		maquina.getPruebas().clear();
		maquina.getPruebas().addAll(list);
		this.repository.save(maquina);
	}

	@Override
	public List<MaquinaPrueba> listPruebasByMaquina(Long idMaquina) {
		return this.maquinaPruebaRepository.findByIdMaquina(idMaquina);
	}

	@Resource(name = "maquinaRepository")
	public void setMaquinaRepository(MaquinaRepository maquinaRepository) {
		this.repository = maquinaRepository;
	}

	@Resource(name = "maquinaPruebaRepository")
	public void setMaquinaPruebaRepository(MaquinaPruebaRepository maquinaPruebaRepository) {
		this.maquinaPruebaRepository = maquinaPruebaRepository;
	}

}
