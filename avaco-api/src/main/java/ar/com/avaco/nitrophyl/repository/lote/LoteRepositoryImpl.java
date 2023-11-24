package ar.com.avaco.nitrophyl.repository.lote;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;

@Repository("loteRepository")
public class LoteRepositoryImpl extends NJBaseRepository<Long, Lote> implements LoteRepositoryCustom {

	public LoteRepositoryImpl(EntityManager entityManager) {
		super(Lote.class, entityManager);
	}

}
