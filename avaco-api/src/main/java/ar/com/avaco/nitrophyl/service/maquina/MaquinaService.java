package ar.com.avaco.nitrophyl.service.maquina;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.nitrophyl.domain.entities.maquina.Maquina;
import ar.com.avaco.nitrophyl.domain.entities.maquina.MaquinaPrueba;


public interface MaquinaService extends NJService<Long, Maquina> {

	void updatePruebasByMaquina(Long idMaquina, List<String> pruebas);

	List<MaquinaPrueba> listPruebasByMaquina(Long idMaquina);
	
}
