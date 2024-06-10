package ar.com.avaco.nitrophyl.service.reporte;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.reporte.ReporteLoteConfiguracionCliente;
import ar.com.avaco.nitrophyl.repository.reporte.ReporteLoteConfiguracionClienteRepository;

@Transactional
@Service("reporteLoteConfiguracionClienteService")
public class ReporteLoteConfiguracionClienteServiceImpl
		extends NJBaseService<Long, ReporteLoteConfiguracionCliente, ReporteLoteConfiguracionClienteRepository>
		implements ReporteLoteConfiguracionClienteService {

	@Resource(name = "reporteLoteConfiguracionClienteRepository")
	public void setReporteLoteConfiguracionClienteRepository(
			ReporteLoteConfiguracionClienteRepository reporteLoteConfiguracionClienteRepository) {
		this.repository = reporteLoteConfiguracionClienteRepository;
	}

}
