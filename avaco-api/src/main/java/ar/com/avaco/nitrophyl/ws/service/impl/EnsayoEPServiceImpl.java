package ar.com.avaco.nitrophyl.ws.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPrueba;
import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPruebaParametro;
import ar.com.avaco.nitrophyl.domain.entities.lote.Ensayo;
import ar.com.avaco.nitrophyl.domain.entities.lote.EnsayoResultado;
import ar.com.avaco.nitrophyl.domain.entities.lote.EstadoEnsayo;
import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;
import ar.com.avaco.nitrophyl.service.lote.EnsayoService;
import ar.com.avaco.nitrophyl.service.maquina.ConfiguracionPruebaService;
import ar.com.avaco.nitrophyl.ws.dto.EnsayoDTO;
import ar.com.avaco.nitrophyl.ws.dto.EnsayoResultadoDTO;
import ar.com.avaco.nitrophyl.ws.service.EnsayoEPService;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("ensayoEPService")
public class EnsayoEPServiceImpl extends CRUDEPBaseService<Long, EnsayoDTO, Ensayo, EnsayoService>
		implements EnsayoEPService {

	private ConfiguracionPruebaService configuracionPruebaService;

	@Override
	@Resource(name = "ensayoService")
	protected void setService(EnsayoService service) {
		this.service = service;
	}

	@Resource(name = "configuracionPruebaService")
	public void setConfiguracionPruebaService(ConfiguracionPruebaService configuracionPruebaService) {
		this.configuracionPruebaService = configuracionPruebaService;
	}

	@Override
	protected Ensayo convertToEntity(EnsayoDTO dto) {
		Ensayo ensayo = new Ensayo();
		ensayo.setId(dto.getId());
		Lote lote = new Lote();
		lote.setId(dto.getIdLote());
		ensayo.setLote(lote);
		ensayo.setObservaciones(dto.getObservaciones());
		Set<EnsayoResultado> resultados = new HashSet<>();
		dto.getResultados().forEach(res -> {
			EnsayoResultado er = new EnsayoResultado();
			er.setId(er.getId());
			er.setConfiguracionPruebaParametro(
					new ConfiguracionPruebaParametro(res.getIdConfiguracionPruebaParametro()));
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
		dto.setIdLote(entity.getLote().getId());
		dto.setMaquina(entity.getConfiguracionPrueba().getMaquina().getNombre());
		dto.setObservaciones(entity.getObservaciones());
		dto.setEstado(entity.getEstado().toString());
		return dto;
	}

	@Override
	public List<EnsayoDTO> listByLote(Long idLote) {
		List<Ensayo> ensayos = this.service.listByLote(idLote);
		List<EnsayoDTO> dtos = new ArrayList<EnsayoDTO>();
		ensayos.forEach(x -> dtos.add(convertToDto(x)));
		return dtos;
	}

	@Override
	public EnsayoDTO save(EnsayoDTO dto) throws BusinessException {
		// Convierto el dto en entidad
		Ensayo ensayo = convertToEntity(dto);

		// Obtengo la configuracion de la prueba
		ConfiguracionPrueba configuracionPrueba = configuracionPruebaService.get(dto.getIdConfiguracionPrueba());

		// Limpio el ID
		ensayo.setId(null);

		// Seteo la configuracion
		ensayo.setConfiguracionPrueba(configuracionPrueba);

		// Seteo la fecha
		ensayo.setFecha(DateUtils.toDate(dto.getFecha(), DateUtils.dd_MM_yyyy));

		// Seteo la fecha
		ensayo.setEstado(EstadoEnsayo.valueOf(dto.getEstado()));

		// Seteo la fecha y creacion del ensayo
		ensayo.setUsuarioCreacion(SecurityContextHolder.getContext().getAuthentication().getName());
		ensayo.setFechaCreacion(DateUtils.getFechaYHoraActual());

		// Seteo en cada resultado el usuario y fecha de creacion
		ensayo.getResultados().forEach(resultado ->	{
			resultado.setUsuarioCreacion(SecurityContextHolder.getContext().getAuthentication().getName());
			resultado.setFechaCreacion(DateUtils.getFechaYHoraActual());
		});
		
		Ensayo save = this.service.save(ensayo);
		dto.setId(save.getId());
		return dto;
	}

	@Override
	public EnsayoDTO update(EnsayoDTO dto) throws BusinessException {

		Ensayo ensayo = this.service.get(dto.getId());

		// Seteo las observaciones
		ensayo.setObservaciones(dto.getObservaciones());
		
		// Seteo la fecha
		ensayo.setFecha(DateUtils.toDate(dto.getFecha(), DateUtils.dd_MM_yyyy));

		// Seteo el estado
		ensayo.setEstado(EstadoEnsayo.valueOf(dto.getEstado()));

		ensayo.getResultados().forEach(resultado ->	{
			// Busco el registro en listado de dtos
			EnsayoResultadoDTO res = dto.getResultados().stream().filter(x -> x.getId().equals(resultado.getId())).findFirst().get();
			
			// Si cambio el resultado o el redondeo los actualizo y marco el usuario y fecha de actualizacion
			if (!resultado.getRedondeo().equals(res.getRedondeo()) || !resultado.getResultado().equals(res.getResultado())) {
				resultado.setRedondeo(res.getRedondeo());
				resultado.setResultado(res.getResultado());
				resultado.setFechaActualizacion(DateUtils.getFechaYHoraActual());
				resultado.setUsuarioActualizacion(SecurityContextHolder.getContext().getAuthentication().getName());
			}
		});
		
		// Seteo el usuario y fecha de actualizacion
		ensayo.setUsuarioActualizacion(SecurityContextHolder.getContext().getAuthentication().getName());
		ensayo.setFechaActualizacion(DateUtils.getFechaYHoraActual());

		// Actualizo
		this.service.update(ensayo);

		return dto;
	}

}
