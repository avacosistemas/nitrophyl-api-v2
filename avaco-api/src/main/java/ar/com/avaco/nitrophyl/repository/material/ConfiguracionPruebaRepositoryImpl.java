package ar.com.avaco.nitrophyl.repository.material;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPrueba;

@Repository("configuracionPruebaRepository")
public class ConfiguracionPruebaRepositoryImpl extends NJBaseRepository<Long, ConfiguracionPrueba>
		implements ConfiguracionPruebaRepositoryCustom {

	public ConfiguracionPruebaRepositoryImpl(EntityManager entityManager) {
		super(ConfiguracionPrueba.class, entityManager);
	}

}
