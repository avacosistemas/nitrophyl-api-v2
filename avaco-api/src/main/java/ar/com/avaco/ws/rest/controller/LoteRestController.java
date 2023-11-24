package ar.com.avaco.ws.rest.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.ws.dto.LoteDTO;
import ar.com.avaco.nitrophyl.ws.dto.LoteFilterDTO;
import ar.com.avaco.nitrophyl.ws.service.LoteEPService;
import ar.com.avaco.nitrophyl.ws.service.filter.LoteFilter;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class LoteRestController extends AbstractDTORestController<LoteDTO, Long, LoteEPService> {

	@Resource(name = "loteEPService")
	public void setService(LoteEPService loteEPService) {
		super.service = loteEPService;
	}

	@RequestMapping(value = "/lote", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list(LoteFilterDTO filter) {
		List<LoteDTO> listFilter = super.service.listFilter(new LoteFilter(filter));
		JSONResponse response = new JSONResponse();
		response.setData(listFilter);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/lote", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> create(@RequestBody LoteDTO loteDTO) throws BusinessException {
		loteDTO.setId(null);
		LoteDTO saved = this.service.save(loteDTO);
		JSONResponse response = new JSONResponse();
		response.setData(saved);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

}