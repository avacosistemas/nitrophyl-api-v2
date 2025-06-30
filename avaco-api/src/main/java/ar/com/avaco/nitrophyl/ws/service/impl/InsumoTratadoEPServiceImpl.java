package ar.com.avaco.nitrophyl.ws.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.InsumoTratado;
import ar.com.avaco.nitrophyl.service.pieza.InsumoTratadoService;
import ar.com.avaco.nitrophyl.ws.dto.InsumoTratadoDTO;
import ar.com.avaco.nitrophyl.ws.service.InsumoTratadoEPService;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("insumoTratadoEPService")
public class InsumoTratadoEPServiceImpl
		extends CRUDAuditableEPBaseService<Long, InsumoTratadoDTO, InsumoTratado, InsumoTratadoService>
		implements InsumoTratadoEPService {

	public InsumoTratadoEPServiceImpl() {
		super(InsumoTratado.class, InsumoTratadoDTO.class);
	}

	@Override
	@Resource(name = "insumoTratadoService")
	protected void setService(InsumoTratadoService service) {
		this.service = service;
	}

}