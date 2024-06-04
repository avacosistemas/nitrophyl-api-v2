package ar.com.avaco.nitrophyl.service.reporte;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.reporte.ReporteConfiguracionCliente;
import ar.com.avaco.nitrophyl.repository.reporte.ReporteConfiguracionClienteRepository;

@Transactional
@Service("reporteConfiguracionClienteService")
public class ReporteConfiguracionClienteServiceImpl
		extends NJBaseService<Long, ReporteConfiguracionCliente, ReporteConfiguracionClienteRepository>
		implements ReporteConfiguracionClienteService {

	@Resource(name = "ReporteConfiguracionClienteRepository")
	public void setFormulaRepository(ReporteConfiguracionClienteRepository reporteConfiguracionClienteRepository) {
		this.repository = reporteConfiguracionClienteRepository;
	}

}
