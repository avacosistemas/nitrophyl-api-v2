package ar.com.avaco.nitrophyl.ws.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.formula.Material;
import ar.com.avaco.nitrophyl.domain.entities.formula.RevisionParametros;
import ar.com.avaco.nitrophyl.service.formula.FormulaService;
import ar.com.avaco.nitrophyl.service.formula.MaterialService;
import ar.com.avaco.nitrophyl.ws.dto.FormulaDTO;
import ar.com.avaco.nitrophyl.ws.dto.RevisionParametrosDTO;
import ar.com.avaco.nitrophyl.ws.service.FormulaEPService;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("formulaEPService")
public class FormulaEPServiceImpl extends CRUDEPBaseService<Long, FormulaDTO, Formula, FormulaService>
		implements FormulaEPService {

	private MaterialService materialService;

	@Override
	@Resource(name = "formulaService")
	protected void setService(FormulaService service) {
		this.service = service;
	}

	@Resource(name = "materialService")
	public void setMaterialService(MaterialService materialService) {
		this.materialService = materialService;
	}

	@Override
	protected Formula convertToEntity(FormulaDTO dto) {
		Formula formula = new Formula();
		formula.setId(dto.getId());
		Material material = materialService.get(dto.getIdMaterial());
		formula.setMaterial(material);
		formula.setNombre(dto.getNombre());
		formula.setNorma(dto.getNorma());
		formula.setObservaciones(dto.getObservaciones());
		return formula;
	}

	@Override
	protected FormulaDTO convertToDto(Formula entity) {
		FormulaDTO dto = new FormulaDTO();
		dto.setId(entity.getId());
		dto.setIdMaterial(entity.getMaterial().getId());
		dto.setMaterial(entity.getMaterial().getNombre());
		dto.setNombre(entity.getNombre());
		dto.setVersion(entity.getVersion());
		dto.setFecha(DateUtils.toStringFecha(entity.getFecha()));
		dto.setNorma(entity.getNorma());
		dto.setObservaciones(entity.getObservaciones());
		RevisionParametros revision = entity.getRevision();
		if (revision != null) {
			RevisionParametrosDTO rpdto = new RevisionParametrosDTO();
			rpdto.setFecha(DateUtils.toStringFecha(revision.getFecha()));
			rpdto.setFechaHasta(DateUtils.toStringFecha(revision.getFechaHasta()));
			rpdto.setRevision(revision.getRevision());
			dto.setRpdto(rpdto);
		}
		return dto;
	}
	
	@Override
	public FormulaDTO clone(FormulaDTO dto) throws BusinessException {
		Formula entity = convertToEntity(dto);
		Formula clone = this.service.clone(entity);
		return convertToDto(clone);
	}

	@Override
	public RevisionParametrosDTO marcarrevision(Long idFormula) {
		RevisionParametros rp = this.service.marcarRevision(idFormula);
		RevisionParametrosDTO rpdto = new RevisionParametrosDTO();
		rpdto.setFecha(DateUtils.toStringFecha(rp.getFecha()));
		rpdto.setFechaHasta(DateUtils.toStringFecha(rp.getFechaHasta()));
		rpdto.setId(rp.getId());
		rpdto.setRevision(rp.getRevision());
		return rpdto;
	}

}
