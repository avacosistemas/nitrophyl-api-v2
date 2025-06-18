package ar.com.avaco.nitrophyl.ws.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.InsumoTratado;
import ar.com.avaco.nitrophyl.service.pieza.InsumoTratadoService;
import ar.com.avaco.nitrophyl.ws.dto.InsumoTratadoDTO;
import ar.com.avaco.nitrophyl.ws.service.InsumoTratadoEPService;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("insumoTratadoEPService")
public class InsumoTratadoEPServiceImpl extends CRUDAuditableEPBaseService<Long, InsumoTratadoDTO, InsumoTratado, InsumoTratadoService>
		implements InsumoTratadoEPService {

	@Override
	@Resource(name = "insumoTratadoService")
	protected void setService(InsumoTratadoService service) {
		this.service = service;
	}

	@Override
	protected InsumoTratado convertToEntity(InsumoTratadoDTO dto) {
		InsumoTratado insumoTratado = new InsumoTratado();
		// TODO COMPLETAR CAMPOS
		return insumoTratado;
	}

	@Override
	protected InsumoTratadoDTO convertToDto(InsumoTratado entity) {
		InsumoTratadoDTO dto = new InsumoTratadoDTO();
		// TODO COMPLETAR CAMPOS
		return dto;
	}

}