package ar.com.avaco.nitrophyl.ws.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaCliente;
import ar.com.avaco.nitrophyl.service.pieza.PiezaClienteService;
import ar.com.avaco.nitrophyl.ws.dto.PiezaClienteDTO;
import ar.com.avaco.nitrophyl.ws.service.PiezaClienteEPService;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("piezaClienteEPService")
public class PiezaClienteEPServiceImpl
		extends CRUDAuditableEPBaseService<Long, PiezaClienteDTO, PiezaCliente, PiezaClienteService>
		implements PiezaClienteEPService {

	public PiezaClienteEPServiceImpl() {
		super(PiezaCliente.class, PiezaClienteDTO.class);
	}

	@Override
	@Resource(name = "piezaClienteService")
	protected void setService(PiezaClienteService service) {
		this.service = service;
	}

}