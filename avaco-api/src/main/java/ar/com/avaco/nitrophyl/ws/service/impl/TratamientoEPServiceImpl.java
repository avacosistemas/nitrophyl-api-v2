package ar.com.avaco.nitrophyl.ws.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.Tratamiento;
import ar.com.avaco.nitrophyl.service.pieza.TratamientoService;
import ar.com.avaco.nitrophyl.ws.dto.TratamientoDTO;
import ar.com.avaco.nitrophyl.ws.service.TratamientoEPService;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("tratamientoEPService")
public class TratamientoEPServiceImpl extends CRUDAuditableEPBaseService<Long, TratamientoDTO, Tratamiento, TratamientoService>
		implements TratamientoEPService {

	@Override
	@Resource(name = "tratamientoService")
	protected void setService(TratamientoService service) {
		this.service = service;
	}

	@Override
	protected Tratamiento convertToEntity(TratamientoDTO dto) {
		Tratamiento tratamiento = new Tratamiento();
		// TODO COMPLETAR CAMPOS
		return tratamiento;
	}

	@Override
	protected TratamientoDTO convertToDto(Tratamiento entity) {
		TratamientoDTO dto = new TratamientoDTO();
		// TODO COMPLETAR CAMPOS
		return dto;
	}

}