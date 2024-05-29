package ar.com.avaco.ws.rest.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.nitrophyl.ws.dto.LoteDTO;
import ar.com.avaco.nitrophyl.ws.dto.ReporteLoteClienteDTO;
import ar.com.avaco.nitrophyl.ws.service.LoteEPService;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class ReporteRestController extends AbstractDTORestController<LoteDTO, Long, LoteEPService> {

	@Resource(name = "loteEPService")
	public void setService(LoteEPService loteEPService) {
		super.service = loteEPService;
	}

	@RequestMapping(value = "/loteReporte", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list(ReporteLoteClienteDTO dto) {
		this.service.generarReporteLoteCliente(dto.getIdLote(), dto.getIdCliente());
		JSONResponse response = new JSONResponse();
		response.setData(true);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

}