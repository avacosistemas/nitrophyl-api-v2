package ar.com.avaco.nitrophyl.repository.material;

import java.util.List;

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

	@Override
	public List<ConfiguracionPrueba> obtenerConfiguracionesMaximaRevisionIndividual(Long idFormula) {

		String query = "select * from conf_prueba cp inner join ( " + 
				"   select id_maquina, max(revision) max_value " + 
				"   from conf_prueba group by id_maquina " + 
				")  t on t.id_maquina = cp.id_maquina and t.max_value = cp.revision " + 
				"and cp.id_formula = " + idFormula;
		
		return this.getCurrentSession().createSQLQuery(query).addEntity(ConfiguracionPrueba.class).list();
	}

}
