package ar.com.avaco.nitrophyl.ws.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.lote.EstadoLote;
import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;
import ar.com.avaco.nitrophyl.service.lote.LoteService;
import ar.com.avaco.nitrophyl.ws.dto.LoteDTO;
import ar.com.avaco.nitrophyl.ws.service.LoteEPService;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("loteEPService")
public class LoteEPServiceImpl extends CRUDEPBaseService<Long, LoteDTO, Lote, LoteService> implements LoteEPService {

	@Override
	@Resource(name = "loteService")
	protected void setService(LoteService service) {
		this.service = service;
	}

	@Override
	public LoteDTO save(LoteDTO dto) throws BusinessException {
		dto.setEstado(EstadoLote.PENDIENTE_APROBACION.name());
		return super.save(dto);
	}
	
	@Override
	public LoteDTO update(LoteDTO dto) throws BusinessException {
		LoteDTO update = this.get(dto.getId());
		update.setFecha(dto.getFecha());
		if (dto.getIdFormula() != update.getIdFormula() && this.service.hasEnsayos(dto.getId())) {
			throw new BusinessException("No puede actualizarse la fórmula porque tiene ensayos asociados");
		}
		update.setIdFormula(dto.getIdFormula());
		update.setNroLote(dto.getNroLote());
		update.setObservaciones(dto.getObservaciones());
		return super.update(update);
	}
	
	@Override
	protected Lote convertToEntity(LoteDTO dto) {
		Lote lote = new Lote();
		Formula formula = new Formula();
		formula.setId(dto.getIdFormula());
		lote.setId(dto.getId());
		lote.setFormula(formula);
		lote.setFecha(DateUtils.toDate(dto.getFecha(), DateUtils.PATTERN_SOLO_FECHA));
		lote.setObservaciones(dto.getObservaciones());
		lote.setNroLote(dto.getNroLote());
		lote.setEstado(EstadoLote.valueOf(dto.getEstado()));
		if (StringUtils.isNotBlank(dto.getFechaEstado())) {
			lote.setFechaEstado(DateUtils.toDate(dto.getFechaEstado(), DateUtils.PATTERN_SOLO_FECHA));
		}
		lote.setObservacionesEstado(dto.getObservacionesEstado());
		return lote;
	}

	@Override
	protected LoteDTO convertToDto(Lote entity) {
		LoteDTO dto = new LoteDTO();
		dto.setFecha(DateUtils.toStringFecha(entity.getFecha()));
		dto.setFormula(entity.getFormula().toString());
		dto.setId(entity.getId());
		dto.setIdFormula(entity.getFormula().getId());
		dto.setObservaciones(entity.getObservaciones());
		dto.setNroLote(entity.getNroLote());
		dto.setEstado(entity.getEstado().toString());
		dto.setFormulaSimple(entity.getFormula().getNombre());
		if (entity.getFechaEstado() != null) {
			dto.setFechaEstado(DateUtils.toStringFecha(entity.getFechaEstado()));
		}
		dto.setObservacionesEstado(entity.getObservacionesEstado());
		return dto;
	}

	@Override
	public void aprobar(Long idLote, String estado, String observaciones) {
		this.service.aprobar(idLote, estado, observaciones);
	}

	@Override
	public void rechazar(Long idLote, String observaciones) {
		this.service.rechazar(idLote, observaciones);
	}

	@Override
	public void borrar(Long idLote) throws BusinessException {
		if (this.service.hasEnsayos(idLote))
			throw new BusinessException("No se puede borrar el lote porque tiene ensayos asociados");
		this.service.borrar(idLote);
	}

}
