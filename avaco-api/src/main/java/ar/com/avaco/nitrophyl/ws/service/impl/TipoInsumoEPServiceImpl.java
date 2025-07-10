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

}
