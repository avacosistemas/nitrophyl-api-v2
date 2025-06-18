package ar.com.avaco.nitrophyl.ws.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.fabrica.Prensa;
import ar.com.avaco.nitrophyl.service.pieza.PrensaService;
import ar.com.avaco.nitrophyl.ws.dto.PrensaDTO;
import ar.com.avaco.nitrophyl.ws.service.PrensaEPService;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("prensaEPService")
public class PrensaEPServiceImpl extends CRUDAuditableEPBaseService<Long, PrensaDTO, Prensa, PrensaService>
		implements PrensaEPService {

	@Override
	@Resource(name = "prensaService")
	protected void setService(PrensaService service) {
		this.service = service;
	}

	@Override
	protected Prensa convertToEntity(PrensaDTO dto) {
		Prensa prensa = new Prensa();
		prensa.setId(dto.getId());
		prensa.setNombre(dto.getNombre());
		return prensa;
	}

	@Override
	protected PrensaDTO convertToDto(Prensa entity) {
		PrensaDTO dto = new PrensaDTO();
		dto.setId(entity.getId());
		dto.setNombre(entity.getNombre());
		return dto;
	}

}