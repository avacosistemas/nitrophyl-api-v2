package ar.com.avaco.nitrophyl.ws.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPrueba;
import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPruebaParametro;
import ar.com.avaco.nitrophyl.domain.entities.lote.Ensayo;
import ar.com.avaco.nitrophyl.domain.entities.lote.EnsayoResultado;
import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;
import ar.com.avaco.nitrophyl.service.lote.EnsayoResultadoService;
import ar.com.avaco.nitrophyl.service.lote.EnsayoService;
import ar.com.avaco.nitrophyl.ws.dto.EnsayoDTO;
import ar.com.avaco.nitrophyl.ws.dto.EnsayoResultadoDTO;
import ar.com.avaco.nitrophyl.ws.service.EnsayoEPService;
import ar.com.avaco.nitrophyl.ws.service.EnsayoResultadoEPService;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("ensayoResultadoEPService")
public class EnsayoResultadoEPServiceImpl extends CRUDEPBaseService<Long, EnsayoResultadoDTO, EnsayoResultado, EnsayoResultadoService>
		implements EnsayoResultadoEPService {


	@Override
	@Resource(name = "ensayoResultadoService")
	protected void setService(EnsayoResultadoService service) {
		this.service = service;
	}

	@Override
	protected EnsayoResultado convertToEntity(EnsayoResultadoDTO dto) {
		return null;
	}

	@Override
	protected EnsayoResultadoDTO convertToDto(EnsayoResultado entity) {
		EnsayoResultadoDTO erdto = new EnsayoResultadoDTO();
		erdto.setId(entity.getId());
		erdto.setIdConfiguracionPruebaParametro(entity.getConfiguracion().getId());
		erdto.setIdEnsayo(entity.getEnsayo().getId());
		erdto.setMaximo(entity.getConfiguracion().getMaximo());
		erdto.setMinimo(entity.getConfiguracion().getMinimo());
		erdto.setNombre(entity.getConfiguracion().getNombre());
		erdto.setRedondeo(entity.getRedondeo());
		erdto.setResultado(entity.getResultado());
		return erdto;
	}

	@Override
	public List<EnsayoResultadoDTO> listByEnsayo(Long idEnsayo) {
		List<EnsayoResultado> resultados = this.service.listByEnsayo(idEnsayo);
		List<EnsayoResultadoDTO> dtos = new ArrayList<EnsayoResultadoDTO>();
		resultados.forEach(x-> dtos.add(convertToDto(x)));
		return dtos;
	}

}
