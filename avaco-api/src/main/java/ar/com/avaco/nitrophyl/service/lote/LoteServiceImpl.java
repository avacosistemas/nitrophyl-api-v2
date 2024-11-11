package ar.com.avaco.nitrophyl.service.lote;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.lote.EstadoLote;
import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;
import ar.com.avaco.nitrophyl.repository.lote.EnsayoRepository;
import ar.com.avaco.nitrophyl.repository.lote.LoteRepository;
import ar.com.avaco.nitrophyl.repository.material.FormulaRepository;
import ar.com.avaco.utils.DateUtils;

@Transactional
@Service("loteService")
public class LoteServiceImpl extends NJBaseService<Long, Lote, LoteRepository> implements LoteService {

	private EnsayoRepository ensayoRepository;
	private FormulaRepository formulaRepository;
	
	@Override
	public Lote save(Lote entity) {
		Formula formula = formulaRepository.getOne(entity.getFormula().getId());
		entity.setId(null);
		entity.setRevisionParametros(formula.getRevision());
		return super.save(entity);
	}

	@Override
	public void aprobar(Long idLote, String estado, String observaciones) {
		this.repository.updateEstadoLote(idLote, EstadoLote.valueOf(estado), observaciones,
				DateUtils.getFechaYHoraActual());
	}

	@Override
	public void rechazar(Long idLote, String observaciones) {
		this.repository.updateEstadoLote(idLote, EstadoLote.RECHAZADO, observaciones, DateUtils.getFechaYHoraActual());
	}

	@Override
	public void borrar(Long idLote) {
		this.repository.delete(idLote);

	}

	@Override
	public boolean hasEnsayos(Long idLote) {
		return this.ensayoRepository.countByLoteId(idLote) > 0;
	}

	public Lote getLoteCompleto(Long idLote) {
		Lote one = this.repository.findOne(idLote);
		return one;
	}
	
	@Resource(name = "ensayoRepository")
	public void setEnsayoRepository(EnsayoRepository ensayoRepository) {
		this.ensayoRepository = ensayoRepository;
	}
	
	@Resource(name = "loteRepository")
	public void setLoteRepository(LoteRepository loteRepository) {
		this.repository = loteRepository;
	}
	
	@Resource(name = "formulaRepository")
	public void setFormulaRepository(FormulaRepository formulaRepository) {
		this.formulaRepository = formulaRepository;
	}
	

}