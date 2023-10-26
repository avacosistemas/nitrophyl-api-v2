package ar.com.avaco.nitrophyl.ws.service;

import java.util.List;

import ar.com.avaco.nitrophyl.ws.dto.MaquinaDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface MaquinaEPService extends CRUDEPService<Long, MaquinaDTO> {

	List<String> updateMaquinaPrueba(Long idMaquina, List<String> pruebas);

	List<String> listPruebas(Long idMaquina);

}
