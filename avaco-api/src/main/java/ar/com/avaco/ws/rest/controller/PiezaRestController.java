package ar.com.avaco.ws.rest.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.nitrophyl.ws.dto.PiezaDTO;
import ar.com.avaco.nitrophyl.ws.service.PiezaEPService;

@RestController
public class PiezaRestController extends AbstractDTORestController<PiezaDTO, Long, PiezaEPService> {

	@Resource(name = "piezaEPService")
	public void setService(PiezaEPService piezaEPService) {
		super.service = piezaEPService;
	}

}