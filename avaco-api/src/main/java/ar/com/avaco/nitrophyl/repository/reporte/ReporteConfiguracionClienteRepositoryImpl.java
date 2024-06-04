package ar.com.avaco.nitrophyl.repository.reporte;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.reporte.ReporteConfiguracionCliente;

@Repository("reporteConfiguracionClienteRepository")
public class ReporteConfiguracionClienteRepositoryImpl extends NJBaseRepository<Long, ReporteConfiguracionCliente>
		implements ReporteConfiguracionClienteRepositoryCustom {

	public ReporteConfiguracionClienteRepositoryImpl(EntityManager entityManager) {
		super(ReporteConfiguracionCliente.class, entityManager);
	}

}
