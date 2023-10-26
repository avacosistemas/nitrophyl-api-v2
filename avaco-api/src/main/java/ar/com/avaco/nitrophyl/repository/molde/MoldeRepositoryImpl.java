package ar.com.avaco.nitrophyl.repository.molde;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.moldes.Molde;

@Repository("moldeRepository")
public class MoldeRepositoryImpl extends NJBaseRepository<Long, Molde> implements MoldeRepositoryCustom {

	public MoldeRepositoryImpl(EntityManager entityManager) {
		super(Molde.class, entityManager);
	}

}
