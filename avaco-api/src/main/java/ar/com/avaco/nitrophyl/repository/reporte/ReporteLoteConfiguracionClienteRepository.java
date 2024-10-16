package ar.com.avaco.nitrophyl.repository.reporte;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.reporte.ReporteLoteConfiguracionCliente;

public interface ReporteLoteConfiguracionClienteRepository
		extends NJRepository<Long, ReporteLoteConfiguracionCliente>, ReporteLoteConfiguracionClienteRepositoryCustom {

}
