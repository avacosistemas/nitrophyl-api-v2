package ar.com.avaco.nitrophyl.ws.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.formula.Material;
import ar.com.avaco.nitrophyl.service.formula.FormulaService;
import ar.com.avaco.nitrophyl.service.formula.MaterialService;
import ar.com.avaco.nitrophyl.ws.dto.FormulaDTO;
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
		dto.setFecha(DateUtils.toString(entity.getFecha().getTime()));
		dto.setNorma(entity.getNorma());
		return dto;
	}
	
	@Override
	public FormulaDTO clone(FormulaDTO dto) throws BusinessException {
		Formula entity = convertToEntity(dto);
		Formula clone = this.service.clone(entity);
		return convertToDto(clone);
	}

}
