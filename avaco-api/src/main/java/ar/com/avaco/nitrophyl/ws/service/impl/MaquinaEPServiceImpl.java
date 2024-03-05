package ar.com.avaco.nitrophyl.ws.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.maquina.EstadoMaquina;
import ar.com.avaco.nitrophyl.domain.entities.maquina.Maquina;
import ar.com.avaco.nitrophyl.domain.entities.maquina.MaquinaPrueba;
import ar.com.avaco.nitrophyl.service.maquina.MaquinaService;
import ar.com.avaco.nitrophyl.ws.dto.MaquinaDTO;
import ar.com.avaco.nitrophyl.ws.service.MaquinaEPService;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("maquinaEPService")
public class MaquinaEPServiceImpl extends CRUDEPBaseService<Long, MaquinaDTO, Maquina, MaquinaService>
		implements MaquinaEPService {

	@Override
	@Resource(name = "maquinaService")
	protected void setService(MaquinaService service) {
		this.service = service;
	}

	@Override
	protected Maquina convertToEntity(MaquinaDTO dto) {
		Maquina maquina = new Maquina();
		maquina.setEstado(EstadoMaquina.valueOf(dto.getEstado()));
		maquina.setId(dto.getId());
		maquina.setNombre(dto.getNombre());
		maquina.setObservacionesReporte(dto.getObservacionesReporte());
		return maquina;
	}

	@Override
	protected MaquinaDTO convertToDto(Maquina entity) {
		MaquinaDTO dto = new MaquinaDTO();
		dto.setEstado(entity.getEstado().name());
		dto.setId(entity.getId());
		dto.setNombre(entity.getNombre());
		dto.setObservacionesReporte(entity.getObservacionesReporte());
		return dto;
	}

	@Override
	public List<String> updateMaquinaPrueba(Long idMaquina, List<String> pruebas) {
		this.service.updatePruebasByMaquina(idMaquina, pruebas);
		return listPruebas(idMaquina);
	}

	@Override
	public List<String> listPruebas(Long idMaquina) {
		return this.service.listPruebasByMaquina(idMaquina).stream().map(MaquinaPrueba::getNombre)
				.collect(Collectors.toList());
	}

}
