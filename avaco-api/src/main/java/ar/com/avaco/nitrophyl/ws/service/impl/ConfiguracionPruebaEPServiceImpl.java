package ar.com.avaco.nitrophyl.ws.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPrueba;
import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPruebaCondicion;
import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPruebaParametro;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.maquina.Maquina;
import ar.com.avaco.nitrophyl.domain.entities.maquina.MaquinaPrueba;
import ar.com.avaco.nitrophyl.service.maquina.ConfiguracionPruebaService;
import ar.com.avaco.nitrophyl.ws.dto.ConfiguracionPruebaCondicionDTO;
import ar.com.avaco.nitrophyl.ws.dto.ConfiguracionPruebaDTO;
import ar.com.avaco.nitrophyl.ws.dto.ConfiguracionPruebaParametroDTO;
import ar.com.avaco.nitrophyl.ws.service.ConfiguracionPruebaEPService;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("configuracionPruebaEPService")
public class ConfiguracionPruebaEPServiceImpl
		extends CRUDEPBaseService<Long, ConfiguracionPruebaDTO, ConfiguracionPrueba, ConfiguracionPruebaService>
		implements ConfiguracionPruebaEPService {

	@Override
	@Resource(name = "configuracionPruebaService")
	protected void setService(ConfiguracionPruebaService service) {
		this.service = service;
	}

	@Override
	protected ConfiguracionPrueba convertToEntity(ConfiguracionPruebaDTO dto) {
		ConfiguracionPrueba cf = new ConfiguracionPrueba();
		// FIXME cambiar por fecha y hora recibida por parametro
		cf.setFecha(DateUtils.getFechaYHoraActual());

		Formula formula = new Formula();
		formula.setId(dto.getIdFormula());
		cf.setFormula(formula);

		Maquina maquina = new Maquina();
		maquina.setId(dto.getIdMaquina());
		cf.setMaquina(maquina);

		cf.setObservacionesReporte(dto.getObservacionesReporte());

		cf.setParametros(new HashSet<>(dto
				.getParametros().stream().map(x -> new ConfiguracionPruebaParametro(cf,
						new MaquinaPrueba(x.getIdMaquinaPrueba()), x.getMinimo(), x.getMaximo(), x.getNorma()))
				.collect(Collectors.toList())));

		cf.setCondiciones(new HashSet<>(dto.getCondiciones().stream()
				.map(x -> new ConfiguracionPruebaCondicion(cf, x.getNombre(), x.getValor()))
				.collect(Collectors.toList())));

		return cf;
	}

	@Override
	protected ConfiguracionPruebaDTO convertToDto(ConfiguracionPrueba entity) {
		ConfiguracionPruebaDTO dto = convertToDtoSinCondicionParametro(entity);
		dto.setCondiciones(entity.getCondiciones().stream().map(x -> convertCondToDto(x)).collect(Collectors.toList()));
		dto.setParametros(entity.getParametros().stream().map(x -> convertParamToDto(x)).collect(Collectors.toList()));
		return dto;
	}

	protected ConfiguracionPruebaDTO convertToDtoSinCondicionParametro(ConfiguracionPrueba entity) {
		ConfiguracionPruebaDTO dto = new ConfiguracionPruebaDTO();
		dto.setFecha(DateUtils.toStringFecha(entity.getFecha()));
		dto.setId(entity.getId());
		dto.setIdFormula(entity.getFormula().getId());
		dto.setIdMaquina(entity.getMaquina().getId());
		dto.setMaquina(entity.getMaquina().getNombre());
		dto.setObservacionesReporte(entity.getObservacionesReporte());
		return dto;
	}

	private ConfiguracionPruebaParametroDTO convertParamToDto(ConfiguracionPruebaParametro cfp) {
		ConfiguracionPruebaParametroDTO dto = new ConfiguracionPruebaParametroDTO();
		dto.setId(cfp.getId());
		dto.setMaximo(cfp.getMaximo());
		dto.setMinimo(cfp.getMinimo());
		dto.setIdMaquinaPrueba(cfp.getMaquinaPrueba().getId());
		dto.setNombre(cfp.getMaquinaPrueba().getNombre());
		dto.setNorma(cfp.getNorma());
		return dto;
	}

	private ConfiguracionPruebaCondicionDTO convertCondToDto(ConfiguracionPruebaCondicion cfp) {
		ConfiguracionPruebaCondicionDTO dto = new ConfiguracionPruebaCondicionDTO();
		dto.setId(cfp.getId());
		dto.setValor(cfp.getValor());
		dto.setNombre(cfp.getNombre());
		return dto;
	}

	@Override
	public List<ConfiguracionPruebaDTO> list(Long idFormula) {
		List<ConfiguracionPrueba> listByIdFormula = this.service.listByIdFormula(idFormula);
		return listByIdFormula.stream().map(x -> convertToDtoSinCondicionParametro(x)).collect(Collectors.toList());
	}

}
