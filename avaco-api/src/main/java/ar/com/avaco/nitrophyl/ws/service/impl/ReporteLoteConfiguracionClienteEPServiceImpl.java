package ar.com.avaco.nitrophyl.ws.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.maquina.Maquina;
import ar.com.avaco.nitrophyl.domain.entities.reporte.ReporteLoteConfiguracionCliente;
import ar.com.avaco.nitrophyl.service.reporte.ReporteLoteConfiguracionClienteService;
import ar.com.avaco.nitrophyl.ws.dto.ReporteLoteConfiguracionClienteDTO;
import ar.com.avaco.nitrophyl.ws.service.ReporteLoteConfiguracionClienteEPService;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("reporteLoteConfiguracionClienteEPService")
public class ReporteLoteConfiguracionClienteEPServiceImpl extends
		CRUDEPBaseService<Long, ReporteLoteConfiguracionClienteDTO, ReporteLoteConfiguracionCliente, ReporteLoteConfiguracionClienteService>
		implements ReporteLoteConfiguracionClienteEPService {

	@Override
	@Resource(name = "reporteLoteConfiguracionClienteService")
	protected void setService(ReporteLoteConfiguracionClienteService service) {
		this.service = service;
	}

	@Override
	protected ReporteLoteConfiguracionCliente convertToEntity(ReporteLoteConfiguracionClienteDTO dto) {
		ReporteLoteConfiguracionCliente entity = new ReporteLoteConfiguracionCliente();
		entity.setId(dto.getId());
		
		if (dto.getIdCliente() != null && dto.getIdCliente() != 0) {
			Cliente cliente = new Cliente();
			cliente.setId(dto.getIdCliente());
			entity.setCliente(cliente);
		}
		
		Formula formula = new Formula();
		formula.setId(dto.getIdFormula());
		entity.setFormula(formula);
		
		if (dto.getIdMaquina() != null && dto.getIdMaquina() != 0) {
			Maquina maquina = new Maquina();
			maquina.setId(dto.getIdMaquina());
			entity.setMaquina(maquina);
		}
		
		entity.setMostrarObservacionesParametro(dto.isMostrarObservacionesParametro());
		entity.setMostrarCondiciones(dto.isMostrarCondiciones());
		entity.setMostrarParametros(dto.isMostrarParametros());
		entity.setMostrarResultados(dto.isMostrarResultados());
		
		return entity;
	}

	@Override
	protected ReporteLoteConfiguracionClienteDTO convertToDto(ReporteLoteConfiguracionCliente entity) {
		ReporteLoteConfiguracionClienteDTO dto = new ReporteLoteConfiguracionClienteDTO();
		
		dto.setId(entity.getId());
		
		if (entity.getCliente() != null) {
			dto.setCliente(entity.getCliente().getNombre() );
			dto.setIdCliente(entity.getCliente().getId());
		}
		
		dto.setFormula(entity.getFormula().toString());
		dto.setIdFormula(entity.getFormula().getId());

		if (entity.getMaquina() != null) {
			dto.setIdMaquina(entity.getMaquina().getId());
			dto.setMaquina(entity.getMaquina().getNombre());
		}
		
		dto.setMostrarObservacionesParametro(entity.isMostrarObservacionesParametro());
		dto.setMostrarCondiciones(entity.isMostrarCondiciones());
		dto.setMostrarParametros(entity.isMostrarParametros());
		dto.setMostrarResultados(entity.isMostrarResultados());
		return dto;
	}

}
