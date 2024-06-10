package ar.com.avaco.nitrophyl.repository.reporte;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.reporte.ReporteLoteConfiguracionCliente;

@Repository("reporteLoteConfiguracionClienteRepository")
public class ReporteLoteConfiguracionClienteRepositoryImpl extends NJBaseRepository<Long, ReporteLoteConfiguracionCliente>
		implements ReporteLoteConfiguracionClienteRepositoryCustom {

	public ReporteLoteConfiguracionClienteRepositoryImpl(EntityManager entityManager) {
		super(ReporteLoteConfiguracionCliente.class, entityManager);
	}

}
