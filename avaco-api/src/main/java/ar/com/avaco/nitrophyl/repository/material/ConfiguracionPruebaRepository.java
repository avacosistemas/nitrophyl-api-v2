package ar.com.avaco.nitrophyl.repository.material;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPrueba;

public interface ConfiguracionPruebaRepository
		extends NJRepository<Long, ConfiguracionPrueba>, ConfiguracionPruebaRepositoryCustom {

	List<ConfiguracionPrueba> findAllByFormulaIdOrderByMaquinaNombre(Long idFormula);

}
