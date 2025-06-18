package ar.com.avaco.nitrophyl.ws.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.Esquema;
import ar.com.avaco.nitrophyl.service.pieza.EsquemaService;
import ar.com.avaco.nitrophyl.ws.dto.EsquemaDTO;
import ar.com.avaco.nitrophyl.ws.service.EsquemaEPService;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("esquemaEPService")
public class EsquemaEPServiceImpl extends CRUDAuditableEPBaseService<Long, EsquemaDTO, Esquema, EsquemaService>
		implements EsquemaEPService {

	@Override
	@Resource(name = "esquemaService")
	protected void setService(EsquemaService service) {
		this.service = service;
	}

	@Override
	protected Esquema convertToEntity(EsquemaDTO dto) {
		Esquema esquema = new Esquema();
		// TODO COMPLETAR CAMPOS
		return esquema;
	}

	@Override
	protected EsquemaDTO convertToDto(Esquema entity) {
		EsquemaDTO dto = new EsquemaDTO();
		// TODO COMPLETAR CAMPOS
		return dto;
	}

}