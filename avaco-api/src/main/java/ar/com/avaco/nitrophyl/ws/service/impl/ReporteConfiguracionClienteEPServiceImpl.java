package ar.com.avaco.nitrophyl.ws.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.maquina.Maquina;
import ar.com.avaco.nitrophyl.domain.entities.reporte.ReporteConfiguracionCliente;
import ar.com.avaco.nitrophyl.service.reporte.ReporteConfiguracionClienteService;
import ar.com.avaco.nitrophyl.ws.dto.ReporteLoteConfiguracionClienteDTO;
import ar.com.avaco.nitrophyl.ws.service.ReporteConfiguracionClienteEPService;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("reporteConfiguracionClienteEPService")
public class ReporteConfiguracionClienteEPServiceImpl extends
		CRUDEPBaseService<Long, ReporteLoteConfiguracionClienteDTO, ReporteConfiguracionCliente, ReporteConfiguracionClienteService>
		implements ReporteConfiguracionClienteEPService {

	@Override
	@Resource(name = "reporteConfiguracionClienteService")
	protected void setService(ReporteConfiguracionClienteService service) {
		this.service = service;
	}

	@Override
	protected ReporteConfiguracionCliente convertToEntity(ReporteLoteConfiguracionClienteDTO dto) {
		ReporteConfiguracionCliente entity = new ReporteConfiguracionCliente();
		entity.setId(dto.getId());
		Cliente cliente = new Cliente();
		cliente.setId(dto.getIdCliente());
		entity.setCliente(cliente);
		Formula formula = new Formula();
		formula.setId(dto.getIdFormula());
		entity.setFormula(formula);
		Maquina maquina = new Maquina();
		maquina.setId(dto.getIdMaquina());
		entity.setMaquina(maquina);
		entity.setMostraObervacionesMaquina(dto.isMostraObervacionesMaquina());
		entity.setMostrarCondiciones(dto.isMostrarCondiciones());
		entity.setMostrarParametros(dto.isMostrarParametros());
		entity.setMostrarResultados(dto.isMostrarResultados());
		return entity;
	}

	@Override
	protected ReporteLoteConfiguracionClienteDTO convertToDto(ReporteConfiguracionCliente entity) {
		ReporteLoteConfiguracionClienteDTO dto = new ReporteLoteConfiguracionClienteDTO();
		dto.setId(entity.getId());
		dto.setCliente(entity.getCliente().getNombre());
		dto.setFormula(entity.getFormula().toString());
		dto.setIdCliente(entity.getCliente().getId());
		dto.setIdFormula(entity.getFormula().getId());
		dto.setIdMaquina(entity.getMaquina().getId());
		dto.setMaquina(entity.getMaquina().getNombre());
		dto.setMostraObervacionesMaquina(entity.isMostraObervacionesMaquina());
		dto.setMostrarCondiciones(entity.isMostrarCondiciones());
		dto.setMostrarParametros(entity.isMostrarParametros());
		dto.setMostrarResultados(entity.isMostrarResultados());
		return dto;
	}

}
