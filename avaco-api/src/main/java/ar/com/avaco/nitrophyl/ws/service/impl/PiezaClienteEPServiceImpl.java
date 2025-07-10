package ar.com.avaco.nitrophyl.ws.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;
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
	protected PiezaClienteDTO convertToDto(PiezaCliente entity) {
		PiezaClienteDTO dto = super.convertToDto(entity);
		dto.setIdCliente(entity.getCliente().getId());
		dto.setIdPieza(entity.getPieza().getId());
		dto.setNombreCliente(entity.getCliente().getNombre());
		return dto;
	}

	@Override
	protected PiezaCliente convertToEntity(PiezaClienteDTO dto) {
		PiezaCliente entity = super.convertToEntity(dto);
		entity.setCliente(Cliente.ofId(dto.getIdCliente()));
		entity.setPieza(Pieza.ofId(dto.getIdPieza()));
		return entity;
	}

	@Override
	@Resource(name = "piezaClienteService")
	protected void setService(PiezaClienteService service) {
		this.service = service;
	}

}