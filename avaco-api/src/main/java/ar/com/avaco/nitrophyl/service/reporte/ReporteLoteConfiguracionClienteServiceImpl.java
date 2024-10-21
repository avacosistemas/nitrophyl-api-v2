package ar.com.avaco.nitrophyl.service.reporte;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
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

	@Override
	public ReporteLoteConfiguracionCliente save(ReporteLoteConfiguracionCliente entity) {
		ReporteLoteConfiguracionCliente find = this.repository.findByFormulaClienteMaquina(entity.getFormula(), entity.getCliente(), entity.getMaquina());
		if (find != null) {
			throw new ErrorValidationException("Ya existe un registro con esa combinacion de fórmula, cliente y máquina", null);
		}
		return super.save(entity);
	}
	
	@Override
	public ReporteLoteConfiguracionCliente update(ReporteLoteConfiguracionCliente entity) {
		ReporteLoteConfiguracionCliente find = this.repository.findByFormulaClienteMaquina(entity.getFormula(), entity.getCliente(), entity.getMaquina());
		if (find != null && entity.getId() != find.getId()) {
			throw new ErrorValidationException("Ya existe un registro con esa combinacion de fórmula, cliente y máquina", null);
		}
		return super.update(entity);
	}
	
	@Override
	public List<ReporteLoteConfiguracionCliente> findConfiguracionesByClienteFormula(Formula formula, Cliente cliente) {
		return this.repository.findConfiguracionesByClienteFormula(formula, cliente);
	}

}
