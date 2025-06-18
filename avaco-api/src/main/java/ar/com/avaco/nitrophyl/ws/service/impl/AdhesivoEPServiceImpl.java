package ar.com.avaco.nitrophyl.ws.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.Adhesivo;
import ar.com.avaco.nitrophyl.service.pieza.AdhesivoService;
import ar.com.avaco.nitrophyl.ws.dto.AdhesivoDTO;
import ar.com.avaco.nitrophyl.ws.service.AdhesivoEPService;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("adhesivoEPService")
public class AdhesivoEPServiceImpl extends CRUDAuditableEPBaseService<Long, AdhesivoDTO, Adhesivo, AdhesivoService>
		implements AdhesivoEPService {

	@Override
	@Resource(name = "adhesivoService")
	protected void setService(AdhesivoService service) {
		this.service = service;
	}

	@Override
	protected Adhesivo convertToEntity(AdhesivoDTO dto) {
		Adhesivo adhesivo = new Adhesivo();
		adhesivo.setId(dto.getId());
		adhesivo.setNombre(dto.getNombre());
		return adhesivo;
	}

	@Override
	protected AdhesivoDTO convertToDto(Adhesivo entity) {
		AdhesivoDTO dto = new AdhesivoDTO();
		dto.setId(entity.getId());
		dto.setNombre(entity.getNombre());
		return dto;
	}

}
