package ar.com.avaco.nitrophyl.repository.pieza;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;

@Repository("piezaRepository")
public class PiezaRepositoryImpl extends NJBaseRepository<Long, Pieza> implements PiezaRepositoryCustom {

	public PiezaRepositoryImpl(EntityManager entityManager) {
		super(Pieza.class, entityManager);
	}

	@Override
	public List<Pieza> findAll() {
		Criteria c = getCurrentSession().createCriteria(Pieza.class);
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return c.list();
	}
	
}
