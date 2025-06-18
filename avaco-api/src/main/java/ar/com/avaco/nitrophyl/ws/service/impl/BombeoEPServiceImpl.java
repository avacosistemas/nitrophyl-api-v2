package ar.com.avaco.nitrophyl.ws.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.Bombeo;
import ar.com.avaco.nitrophyl.service.pieza.BombeoService;
import ar.com.avaco.nitrophyl.ws.dto.BombeoDTO;
import ar.com.avaco.nitrophyl.ws.service.BombeoEPService;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("bombeoEPService")
public class BombeoEPServiceImpl extends CRUDAuditableEPBaseService<Long, BombeoDTO, Bombeo, BombeoService>
		implements BombeoEPService {

	@Override
	@Resource(name = "bombeoService")
	protected void setService(BombeoService service) {
		this.service = service;
	}

	@Override
	protected Bombeo convertToEntity(BombeoDTO dto) {
		Bombeo bombeo = new Bombeo();
		// TODO COMPLETAR CAMPOS
		return bombeo;
	}

	@Override
	protected BombeoDTO convertToDto(Bombeo entity) {
		BombeoDTO dto = new BombeoDTO();
		// TODO COMPLETAR CAMPOS
		return dto;
	}

}