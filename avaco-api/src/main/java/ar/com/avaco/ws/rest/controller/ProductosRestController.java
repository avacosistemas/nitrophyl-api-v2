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
public class ProductosRestController extends AbstractDTORestController<PiezaDTO, Long, PiezaEPService> {

	@Resource(name = "piezaEPService")
	public void setService(PiezaEPService piezaEPService) {
		super.service = piezaEPService;
	}

	@RequestMapping(value = "/productos/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listProductos() throws Exception {
		PiezaFilter filter = new PiezaFilter();
		filter.setDistinctRootEntity(true);
		filter.setEsProducto(Boolean.TRUE);
		List<PiezaDTO> listFilter = super.service.listFilter(filter);
		JSONResponse response = new JSONResponse();
		response.setData(listFilter);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/productos/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getProducto(@PathVariable("id") Long id) throws Exception {
		return super.get(id);
	}

	@RequestMapping(value = "/productos/simple", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> createProductoSimple(@RequestBody PiezaDTO piezaDTO) throws Exception {
		piezaDTO.setId(null);
		piezaDTO.setTipo(TipoPieza.SIMPLE);
		piezaDTO.setEsProducto(Boolean.TRUE);
		return super.create(piezaDTO);
	}

	@RequestMapping(value = "/productos/simple/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> updateProductoSimple(@PathVariable("id") Long id, @RequestBody PiezaDTO piezaDTO)
			throws Exception {
		piezaDTO.setTipo(TipoPieza.SIMPLE);
		piezaDTO.setEsProducto(Boolean.TRUE);
		return super.update(id, piezaDTO);
	}

	@RequestMapping(value = "/productos/compuesto", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> createProductoCompuesto(@RequestBody PiezaDTO piezaDTO) throws Exception {
		piezaDTO.setId(null);
		piezaDTO.setTipo(TipoPieza.COMPUESTA);
		piezaDTO.setEsProducto(Boolean.TRUE);
		return super.create(piezaDTO);
	}

	@RequestMapping(value = "/productos/compuesto/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> updateProductoCompuesto(@PathVariable("id") Long id,
			@RequestBody PiezaDTO piezaDTO) throws Exception {
		piezaDTO.setId(id);
		piezaDTO.setTipo(TipoPieza.COMPUESTA);
		piezaDTO.setEsProducto(Boolean.TRUE);
		return super.update(id, piezaDTO);
	}

	@RequestMapping(value = "/productos/compuesto/{id}/agregar/{idPieza}", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> addPiezaToProductoCompuesto(@PathVariable("id") Long id,
			@PathVariable("idPieza") Long idPieza) throws Exception {
		PiezaDTO saved = this.service.addPiezaToProductoCompuesto(id, idPieza);
		JSONResponse response = new JSONResponse();
		response.setData(saved);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/productos/compuesto/{id}/quitar/{idPieza}", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> deletePiezaFromProductoCompuesta(@PathVariable("id") Long id,
			@PathVariable("idPieza") Long idPieza) throws Exception {
		PiezaDTO saved = this.service.removePiezaFromProductoCompuesto(id, idPieza);
		JSONResponse response = new JSONResponse();
		response.setData(saved);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
}