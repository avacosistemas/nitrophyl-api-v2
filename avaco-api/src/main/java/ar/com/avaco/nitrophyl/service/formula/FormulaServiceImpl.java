package ar.com.avaco.nitrophyl.service.formula;

import java.util.Calendar;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.repository.material.FormulaRepository;

@Transactional
@Service("formulaService")
public class FormulaServiceImpl extends NJBaseService<Long, Formula, FormulaRepository> implements FormulaService {

	@Override
	public Formula save(Formula entity) {
		entity.setId(null);
		entity.setFecha(Calendar.getInstance());
		entity.setVersion(1);
		return super.save(entity);
	}

	@Override
	public Formula clone(Formula entity) {
		Formula one = this.repository.getOne(entity.getId());
		entity.setId(null);
		entity.setVersion(one.getVersion() + 1);
		entity.setFecha(Calendar.getInstance());
		return super.save(entity);
	}

	@Resource(name = "formulaRepository")
	public void setFormulaRepository(FormulaRepository formulaRepository) {
		this.repository = formulaRepository;
	}

}
