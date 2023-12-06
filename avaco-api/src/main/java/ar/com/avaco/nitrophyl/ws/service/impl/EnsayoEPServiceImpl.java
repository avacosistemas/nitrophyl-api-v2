package ar.com.avaco.nitrophyl.ws.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPrueba;
import ar.com.avaco.nitrophyl.domain.entities.lote.Ensayo;
import ar.com.avaco.nitrophyl.domain.entities.lote.EnsayoResultado;
import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;
import ar.com.avaco.nitrophyl.service.lote.EnsayoService;
import ar.com.avaco.nitrophyl.ws.dto.EnsayoDTO;
import ar.com.avaco.nitrophyl.ws.service.EnsayoEPService;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("ensayoEPService")
public class EnsayoEPServiceImpl extends CRUDEPBaseService<Long, EnsayoDTO, Ensayo, EnsayoService>
		implements EnsayoEPService {


	@Override
	@Resource(name = "ensayoService")
	protected void setService(EnsayoService service) {
		this.service = service;
	}

	@Override
	protected Ensayo convertToEntity(EnsayoDTO dto) {
		Ensayo ensayo = new Ensayo();
		ConfiguracionPrueba configuracion = new ConfiguracionPrueba();
		configuracion.setId(dto.getIdConfiguracionPrueba());
		ensayo.setConfiguracion(configuracion);
		ensayo.setFecha(DateUtils.getFechaYHoraActual());
		ensayo.setId(dto.getId());
		Lote lote = new Lote();
		lote.setId(dto.getIdLote());
		ensayo.setLote(lote);
		ensayo.setObservaciones(dto.getObservaciones());
		Set<EnsayoResultado> resultados = new HashSet<>();
		dto.getResultados().forEach(res -> {
			EnsayoResultado er = new EnsayoResultado();
			er.setMaximo(res.getMaximo());
			er.setMinimo(res.getMinimo());
			er.setNombre(res.getNombre());
			er.setEnsayo(ensayo);
			er.setRedondeo(res.getRedondeo());
			er.setResultado(res.getResultado());
			resultados.add(er);
		});
		ensayo.setResultados(resultados);
		return ensayo;
	}

	@Override
	protected EnsayoDTO convertToDto(Ensayo entity) {
		EnsayoDTO dto = new EnsayoDTO();
		dto.setFecha(DateUtils.toStringFecha(entity.getFecha()));
		dto.setId(entity.getId());
		dto.setIdConfiguracionPrueba(entity.getConfiguracion().getId());
		dto.setIdLote(entity.getLote().getId());
		dto.setMaquina(entity.getConfiguracion().getMaquina().getNombre());
		dto.setObservaciones(entity.getObservaciones());
		return dto;
	}

	@Override
	public List<EnsayoDTO> listByLote(Long idLote) {
		List<Ensayo> ensayos = this.service.listByLote(idLote);
		List<EnsayoDTO> dtos = new ArrayList<EnsayoDTO>();
		ensayos.forEach(x-> dtos.add(convertToDto(x)));
		return dtos;
	}
	
	@Override
	public EnsayoDTO save(EnsayoDTO dto) throws BusinessException {
		Ensayo save = this.service.save(convertToEntity(dto));
		dto.setId(save.getId());
		return dto;
	}

}
