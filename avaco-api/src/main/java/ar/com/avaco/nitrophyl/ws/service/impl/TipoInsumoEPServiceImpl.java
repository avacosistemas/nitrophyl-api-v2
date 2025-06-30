package ar.com.avaco.nitrophyl.ws.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.pieza.TipoInsumo;
import ar.com.avaco.nitrophyl.service.pieza.TipoInsumoService;
import ar.com.avaco.nitrophyl.ws.dto.TipoInsumoDTO;
import ar.com.avaco.nitrophyl.ws.service.TipoInsumoEPService;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Service("tipoInsumoEPService")
public class TipoInsumoEPServiceImpl extends
		CRUDAuditableEPBaseService<Long, TipoInsumoDTO, TipoInsumo, TipoInsumoService> implements TipoInsumoEPService {

	public TipoInsumoEPServiceImpl() {
		super(TipoInsumo.class, TipoInsumoDTO.class);
	}

	@Override
	@Resource(name = "tipoInsumoService")
	protected void setService(TipoInsumoService service) {
		this.service = service;
	}

	@Override
	protected TipoInsumo convertToEntity(TipoInsumoDTO dto) {
		TipoInsumo tipoInsumo = new TipoInsumo();
		tipoInsumo.setId(dto.getId());
		tipoInsumo.setNombre(dto.getNombre());
		if (dto.getParentId() != null) {
			TipoInsumo padre = this.service.get(dto.getParentId());
			tipoInsumo.setPadre(padre);
		}
		return tipoInsumo;
	}

	@Override
	protected TipoInsumoDTO convertToDto(TipoInsumo entity) {
		TipoInsumoDTO dto = new TipoInsumoDTO();
		dto.setId(entity.getId());
		dto.setNombre(entity.getNombre());
		if (entity.getPadre() != null)
			dto.setParentId(entity.getPadre().getId());
		return dto;
	}

}
