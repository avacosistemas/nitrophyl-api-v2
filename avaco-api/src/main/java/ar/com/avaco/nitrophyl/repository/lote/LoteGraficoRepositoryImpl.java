package ar.com.avaco.nitrophyl.repository.lote;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.moldes.LoteGrafico;

@Repository("loteGraficoRepository")
public class LoteGraficoRepositoryImpl extends NJBaseRepository<Long, LoteGrafico>
		implements LoteGraficoRepositoryCustom {

	public LoteGraficoRepositoryImpl(EntityManager entityManager) {
		super(LoteGrafico.class, entityManager);
	}

}
