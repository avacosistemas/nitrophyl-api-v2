package ar.com.avaco.nitrophyl.service.maquina;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPrueba;
import ar.com.avaco.nitrophyl.repository.material.ConfiguracionPruebaRepository;
import ar.com.avaco.nitrophyl.ws.dto.ConfiguracionPruebaDTO;
import ar.com.avaco.nitrophyl.ws.dto.ConfiguracionPruebaParametroDTO;

@Transactional
@Service("configuracionPruebaService")
public class ConfiguracionPruebaServiceImpl extends
		NJBaseService<Long, ConfiguracionPrueba, ConfiguracionPruebaRepository> implements ConfiguracionPruebaService {

	@Override
	public List<ConfiguracionPrueba> listByIdFormula(Long idFormula) {
		return this.repository.findAllByFormulaIdOrderByMaquinaNombre(idFormula);
	}

	@Override
	public ConfiguracionPrueba save(ConfiguracionPrueba entity) {
		ConfiguracionPrueba saveAndFlush = this.repository.saveAndFlush(entity);
		return saveAndFlush;
	}
	
	@Resource(name = "configuracionPruebaRepository")
	void setMoldeRepository(ConfiguracionPruebaRepository configuracionPruebaRepository) {
		this.repository = configuracionPruebaRepository;
	}
	
}
