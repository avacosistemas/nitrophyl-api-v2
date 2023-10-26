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

import ar.com.avaco.nitrophyl.domain.entities.pieza.TipoPieza;
import ar.com.avaco.nitrophyl.ws.dto.PiezaDTO;
import ar.com.avaco.nitrophyl.ws.service.PiezaEPService;
import ar.com.avaco.nitrophyl.ws.service.filter.PiezaFilter;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class PiezasRestController extends AbstractDTORestController<PiezaDTO, Long, PiezaEPService> {

	@Resource(name = "piezaEPService")
	public void setService(PiezaEPService piezaEPService) {
		super.service = piezaEPService;
	}

	/* EP Piezas */

	@Override
	@RequestMapping(value = "/piezas/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list() {
		// TODO Auto-generated method stub
		return super.list();
	}
	
	@RequestMapping(value = "/piezas/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getPieza(@PathVariable("id") Long id) throws Exception {
		return super.get(id);
	}
	
	@RequestMapping(value = "/piezas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listPiezas() throws Exception {
		PiezaFilter filter = new PiezaFilter();
		filter.setEsProducto(Boolean.FALSE);
		List<PiezaDTO> listFilter = super.service.listFilter(filter);
		JSONResponse response = new JSONResponse();
		response.setData(listFilter);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/piezas/simple", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> createPiezaSimple(@RequestBody PiezaDTO piezaDTO) throws Exception {
		piezaDTO.setId(null);
		piezaDTO.setTipo(TipoPieza.SIMPLE);
		piezaDTO.setEsProducto(Boolean.FALSE);
		return super.create(piezaDTO);
	}

	@RequestMapping(value = "/piezas/simple/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> updatePiezaSimple(@PathVariable("id") Long id, @RequestBody PiezaDTO piezaDTO)
			throws Exception {
		piezaDTO.setTipo(TipoPieza.SIMPLE);
		piezaDTO.setEsProducto(Boolean.FALSE);
		return super.update(id, piezaDTO);
	}

	@RequestMapping(value = "/piezas/compuesta", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> createPiezaCompuesta(@RequestBody PiezaDTO piezaDTO) throws Exception {
		piezaDTO.setId(null);
		piezaDTO.setTipo(TipoPieza.COMPUESTA);
		piezaDTO.setEsProducto(Boolean.FALSE);
		return super.create(piezaDTO);
	}

	@RequestMapping(value = "/piezas/compuesta/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> updatePiezaCompuesta(@PathVariable("id") Long id,
			@RequestBody PiezaDTO piezaDTO) throws Exception {
		piezaDTO.setId(id);
		piezaDTO.setTipo(TipoPieza.COMPUESTA);
		piezaDTO.setEsProducto(Boolean.FALSE);
		return super.update(id, piezaDTO);
	}

	@RequestMapping(value = "/piezas/compuesta/{id}/agregar/{idPieza}", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> addPiezaToCompuesta(@PathVariable("id") Long id,
			@PathVariable("idPieza") Long idPieza) throws Exception {
		PiezaDTO saved = this.service.addPiezaToCompuesta(id, idPieza);
		JSONResponse response = new JSONResponse();
		response.setData(saved);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/piezas/compuesta/{id}/quitar/{idPieza}", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> deletePiezaFromCompuesta(@PathVariable("id") Long id,
			@PathVariable("idPieza") Long idPieza) throws Exception {
		PiezaDTO saved = this.service.removePiezaFromCompuesta(id, idPieza);
		JSONResponse response = new JSONResponse();
		response.setData(saved);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
}