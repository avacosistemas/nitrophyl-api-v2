package ar.com.avaco.nitrophyl.ws.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.EsquemaPaso;
import ar.com.avaco.nitrophyl.service.pieza.EsquemaPasoService;
import ar.com.avaco.nitrophyl.ws.dto.EsquemaPasoDTO;
import ar.com.avaco.nitrophyl.ws.service.EsquemaPasoEPService;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("esquemaPasoEPService")
public class EsquemaPasoEPServiceImpl extends CRUDAuditableEPBaseService<Long, EsquemaPasoDTO, EsquemaPaso, EsquemaPasoService>
		implements EsquemaPasoEPService {

	@Override
	@Resource(name = "esquemaPasoService")
	protected void setService(EsquemaPasoService service) {
		this.service = service;
	}

	@Override
	protected EsquemaPaso convertToEntity(EsquemaPasoDTO dto) {
		EsquemaPaso esquemaPaso = new EsquemaPaso();
		// TODO COMPLETAR CAMPOS
		return esquemaPaso;
	}

	@Override
	protected EsquemaPasoDTO convertToDto(EsquemaPaso entity) {
		EsquemaPasoDTO dto = new EsquemaPasoDTO();
		// TODO COMPLETAR CAMPOS
		return dto;
	}

}