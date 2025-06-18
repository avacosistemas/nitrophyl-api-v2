package ar.com.avaco.nitrophyl.ws.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaMolde;
import ar.com.avaco.nitrophyl.service.pieza.PiezaMoldeService;
import ar.com.avaco.nitrophyl.ws.dto.PiezaMoldeDTO;
import ar.com.avaco.nitrophyl.ws.service.PiezaMoldeEPService;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("piezaMoldeEPService")
public class PiezaMoldeEPServiceImpl extends CRUDAuditableEPBaseService<Long, PiezaMoldeDTO, PiezaMolde, PiezaMoldeService>
		implements PiezaMoldeEPService {

	@Override
	@Resource(name = "piezaMoldeService")
	protected void setService(PiezaMoldeService service) {
		this.service = service;
	}

	@Override
	protected PiezaMolde convertToEntity(PiezaMoldeDTO dto) {
		PiezaMolde piezaMolde = new PiezaMolde();
		// TODO COMPLETAR CAMPOS
		return piezaMolde;
	}

	@Override
	protected PiezaMoldeDTO convertToDto(PiezaMolde entity) {
		PiezaMoldeDTO dto = new PiezaMoldeDTO();
		// TODO COMPLETAR CAMPOS
		return dto;
	}

}