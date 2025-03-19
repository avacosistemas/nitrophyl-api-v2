package ar.com.avaco.nitrophyl.ws.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.maquina.Maquina;
import ar.com.avaco.nitrophyl.domain.entities.moldes.LoteGrafico;
import ar.com.avaco.nitrophyl.service.lote.LoteGraficoService;
import ar.com.avaco.nitrophyl.service.maquina.MaquinaService;
import ar.com.avaco.nitrophyl.ws.dto.LoteGraficoDTO;
import ar.com.avaco.nitrophyl.ws.dto.LoteGraficoSinArchivoDTO;
import ar.com.avaco.nitrophyl.ws.service.LoteGraficoEPService;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("loteGraficoEPService")
public class LoteGraficoEPServiceImpl extends CRUDEPBaseService<Long, LoteGraficoDTO, LoteGrafico, LoteGraficoService>
		implements LoteGraficoEPService {

	private MaquinaService maquinaService;
	
	@Override
	protected LoteGrafico convertToEntity(LoteGraficoDTO dto) {
		Maquina maquina = maquinaService.get(dto.getIdMaquina());
		LoteGrafico lg = new LoteGrafico();
		lg.setArchivo(dto.getArchivo());
		lg.setFecha(DateUtils.getFechaYHoraActual());
		lg.setIdLote(dto.getIdLote());
		lg.setMaquina(maquina);
		return lg;
	}

	@Override
	protected LoteGraficoDTO convertToDto(LoteGrafico entity) {
		LoteGraficoDTO lgdto = new LoteGraficoDTO();
		lgdto.setArchivo(entity.getArchivo());
		lgdto.setId(entity.getId());
		lgdto.setIdLote(entity.getIdLote());
		lgdto.setIdMaquina(entity.getMaquina().getId());
		lgdto.setMaquina(entity.getMaquina().getNombre());
		lgdto.setLote(entity.getLote().getNroLote());
		return lgdto;
	}

	@Override
	@Resource(name = "loteGraficoService")
	protected void setService(LoteGraficoService service) {
		this.service = service;
	}

	@Resource(name = "maquinaService")
	public void setMaquinaService(MaquinaService maquinaService) {
		this.maquinaService = maquinaService;
	}

	@Override
	public List<LoteGraficoSinArchivoDTO> listByIdLote(Long idLote) {
		return this.service.listGraficosByLote(idLote);
	}
	
}
