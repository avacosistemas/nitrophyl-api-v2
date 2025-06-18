package ar.com.avaco.nitrophyl.ws.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaCliente;
import ar.com.avaco.nitrophyl.service.pieza.PiezaClienteService;
import ar.com.avaco.nitrophyl.ws.dto.PiezaClienteDTO;
import ar.com.avaco.nitrophyl.ws.service.PiezaClienteEPService;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("piezaClienteEPService")
public class PiezaClienteEPServiceImpl extends CRUDAuditableEPBaseService<Long, PiezaClienteDTO, PiezaCliente, PiezaClienteService>
		implements PiezaClienteEPService {

	@Override
	@Resource(name = "piezaClienteService")
	protected void setService(PiezaClienteService service) {
		this.service = service;
	}

	@Override
	protected PiezaCliente convertToEntity(PiezaClienteDTO dto) {
		PiezaCliente piezaCliente = new PiezaCliente();
		// TODO COMPLETAR CAMPOS
		return piezaCliente;
	}

	@Override
	protected PiezaClienteDTO convertToDto(PiezaCliente entity) {
		PiezaClienteDTO dto = new PiezaClienteDTO();
		// TODO COMPLETAR CAMPOS
		return dto;
	}

}