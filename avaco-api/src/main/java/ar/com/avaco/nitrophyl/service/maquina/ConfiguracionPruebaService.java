package ar.com.avaco.nitrophyl.service.maquina;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPrueba;

public interface ConfiguracionPruebaService extends NJService<Long, ConfiguracionPrueba> {

	List<ConfiguracionPrueba> listByIdFormula(Long idFormula);

}
