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

	public PiezaMoldeEPServiceImpl() {
		super(PiezaMolde.class, PiezaMoldeDTO.class);
	}

	@Override
	@Resource(name = "piezaMoldeService")
	protected void setService(PiezaMoldeService service) {
		this.service = service;
	}

}