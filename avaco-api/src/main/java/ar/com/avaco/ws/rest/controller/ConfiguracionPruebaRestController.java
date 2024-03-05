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
import ar.com.avaco.nitrophyl.ws.dto.ConfiguracionPruebaDTO;
import ar.com.avaco.nitrophyl.ws.service.ConfiguracionPruebaEPService;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class ConfiguracionPruebaRestController
		extends AbstractDTORestController<ConfiguracionPruebaDTO, Long, ConfiguracionPruebaEPService> {

	@Resource(name = "configuracionPruebaEPService")
	public void setService(ConfiguracionPruebaEPService configuracionPruebaEPService) {
		super.service = configuracionPruebaEPService;
	}

	@RequestMapping(value = "/configuracionPrueba/formula/{idFormula}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list(@PathVariable("idFormula") Long idFormula) {
		List<ConfiguracionPruebaDTO> listFilter = super.service.list(idFormula);
		JSONResponse response = new JSONResponse();
		response.setData(listFilter);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/configuracionPrueba/{idConfiguracionPrueba}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listPruebasCondiciones(@PathVariable("idConfiguracionPrueba") Long idConfiguracionPrueba) {
		ConfiguracionPruebaDTO configuracionPruebaDTO = super.service.get(idConfiguracionPrueba);
		JSONResponse response = new JSONResponse();
		response.setData(configuracionPruebaDTO);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/configuracionPrueba", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> create(@RequestBody ConfiguracionPruebaDTO confPruebaDTO)
			throws BusinessException {
		ConfiguracionPruebaDTO saved = this.service.save(confPruebaDTO);
		JSONResponse response = new JSONResponse();
		response.setData(saved);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

}