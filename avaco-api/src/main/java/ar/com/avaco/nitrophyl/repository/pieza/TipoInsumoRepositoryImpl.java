package ar.com.avaco.nitrophyl.repository.pieza;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.TipoInsumo;

@Repository("tipoInsumoRepository")
public class TipoInsumoRepositoryImpl extends NJBaseRepository<Long, TipoInsumo> implements TipoInsumoRepositoryCustom {

	public TipoInsumoRepositoryImpl(EntityManager entityManager) {
		super(TipoInsumo.class, entityManager);
	}

}
