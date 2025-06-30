package ar.com.avaco.nitrophyl.ws.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.Terminacion;
import ar.com.avaco.nitrophyl.service.pieza.TerminacionService;
import ar.com.avaco.nitrophyl.ws.dto.TerminacionDTO;
import ar.com.avaco.nitrophyl.ws.service.TerminacionEPService;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("terminacionEPService")
public class TerminacionEPServiceImpl extends CRUDAuditableEPBaseService<Long, TerminacionDTO, Terminacion, TerminacionService>
		implements TerminacionEPService {

	public TerminacionEPServiceImpl() {
		super(Terminacion.class, TerminacionDTO.class);
	}

	@Override
	@Resource(name = "terminacionService")
	protected void setService(TerminacionService service) {
		this.service = service;
	}

}