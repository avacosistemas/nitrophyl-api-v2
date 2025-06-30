package ar.com.avaco.nitrophyl.ws.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaDimension;
import ar.com.avaco.nitrophyl.service.pieza.PiezaDimensionService;
import ar.com.avaco.nitrophyl.ws.dto.PiezaDimensionDTO;
import ar.com.avaco.nitrophyl.ws.service.PiezaDimensionEPService;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("piezaDimensionEPService")
public class PiezaDimensionEPServiceImpl
		extends CRUDAuditableEPBaseService<Long, PiezaDimensionDTO, PiezaDimension, PiezaDimensionService>
		implements PiezaDimensionEPService {

	public PiezaDimensionEPServiceImpl() {
		super(PiezaDimension.class, PiezaDimensionDTO.class);
	}

	@Override
	@Resource(name = "piezaDimensionService")
	protected void setService(PiezaDimensionService service) {
		this.service = service;
	}

}