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
import ar.com.avaco.nitrophyl.domain.entities.lote.EstadoEnsayo;
import ar.com.avaco.nitrophyl.domain.entities.lote.EstadoLote;
import ar.com.avaco.nitrophyl.ws.dto.EnsayoDTO;
import ar.com.avaco.nitrophyl.ws.service.EnsayoEPService;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class EnsayoRestController extends AbstractDTORestController<EnsayoDTO, Long, EnsayoEPService> {

	@Resource(name = "ensayoEPService")
	public void setService(EnsayoEPService ensayoEPService) {
		super.service = ensayoEPService;
	}

	@RequestMapping(value = "/ensayo/{idLote}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list(@PathVariable Long idLote) {
		List<EnsayoDTO> ensayos = super.service.listByLote(idLote);
		JSONResponse response = new JSONResponse();
		response.setData(ensayos);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/ensayo", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> create(@RequestBody EnsayoDTO ensayoDTO) throws BusinessException {
		EnsayoDTO saved = this.service.save(ensayoDTO);
		JSONResponse response = new JSONResponse();
		response.setData(saved);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ensayo/sinresultados", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> createSinResultados(@RequestBody EnsayoDTO ensayoDTO) throws BusinessException {
		
		// Reseto de valores por si mandan algo mal del front
		ensayoDTO.setEstado(EstadoEnsayo.SIN_RESULTADOS.toString());
		ensayoDTO.getResultados().clear();

		// Quitar el seteo de fecha si viene desde el front
		ensayoDTO.setFecha(DateUtils.toString(DateUtils.getFechaYHoraActual(), DateUtils.PATTERN_SOLO_FECHA));
		
		EnsayoDTO saved = this.service.save(ensayoDTO);
		JSONResponse response = new JSONResponse();
		response.setData(saved);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

}