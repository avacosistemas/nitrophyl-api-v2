package ar.com.avaco.nitrophyl.ws.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaPlano;
import ar.com.avaco.nitrophyl.service.pieza.PiezaPlanoService;
import ar.com.avaco.nitrophyl.ws.dto.PiezaPlanoDTO;
import ar.com.avaco.nitrophyl.ws.service.PiezaPlanoEPService;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("piezaPlanoEPService")
public class PiezaPlanoEPServiceImpl extends CRUDAuditableEPBaseService<Long, PiezaPlanoDTO, PiezaPlano, PiezaPlanoService>
		implements PiezaPlanoEPService {

	public PiezaPlanoEPServiceImpl() {
		super(PiezaPlano.class, PiezaPlanoDTO.class);
	}

	@Override
	@Resource(name = "piezaPlanoService")
	protected void setService(PiezaPlanoService service) {
		this.service = service;
	}

}