package ar.com.avaco.nitrophyl.ws.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaTipo;
import ar.com.avaco.nitrophyl.service.pieza.PiezaTipoService;
import ar.com.avaco.nitrophyl.ws.dto.PiezaTipoDTO;
import ar.com.avaco.nitrophyl.ws.service.PiezaTipoEPService;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("piezaTipoEPService")
public class PiezaTipoEPServiceImpl extends CRUDAuditableEPBaseService<Long, PiezaTipoDTO, PiezaTipo, PiezaTipoService>
		implements PiezaTipoEPService {

	@Override
	@Resource(name = "piezaTipoService")
	protected void setService(PiezaTipoService service) {
		this.service = service;
	}

	@Override
	protected PiezaTipo convertToEntity(PiezaTipoDTO dto) {
		PiezaTipo piezaTipo = new PiezaTipo();
		piezaTipo.setNombre(dto.getNombre());
		piezaTipo.setId(dto.getId());
		return piezaTipo;
	}

	@Override
	protected PiezaTipoDTO convertToDto(PiezaTipo entity) {
		PiezaTipoDTO dto = new PiezaTipoDTO();
		dto.setId(entity.getId());
		dto.setNombre(entity.getNombre());
		return dto;
	}

}