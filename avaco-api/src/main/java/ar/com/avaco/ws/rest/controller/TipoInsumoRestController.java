package ar.com.avaco.ws.rest.controller;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.ws.dto.TipoInsumoDTO;
import ar.com.avaco.nitrophyl.ws.service.TipoInsumoEPService;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class TipoInsumoRestController
		extends AbstractAuditableDTORestController<TipoInsumoDTO, Long, TipoInsumoEPService> {

	@Override
	@RequestMapping(value = "/tipoInsumo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list() {
		return super.list();
	}

	@Override
	@RequestMapping(value = "/tipoInsumo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> create(@RequestBody TipoInsumoDTO dto) throws BusinessException {
		return super.create(dto);
	}

	@Override
	@RequestMapping(value = "/tipoInsumo/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> update(@PathVariable Long id, @RequestBody TipoInsumoDTO dto)
			throws BusinessException {
		return super.update(id, dto);
	}

	@Override
	@RequestMapping(value = "/tipoInsumo/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> delete(@PathVariable Long id) throws BusinessException {
		return super.delete(id);
	}

	@Resource(name = "tipoInsumoEPService")
	public void setService(TipoInsumoEPService tipoInsumoEPService) {
		super.service = tipoInsumoEPService;
	}

}