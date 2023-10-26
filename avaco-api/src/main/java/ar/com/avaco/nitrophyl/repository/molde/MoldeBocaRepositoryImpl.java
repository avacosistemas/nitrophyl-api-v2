package ar.com.avaco.nitrophyl.repository.molde;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldeBoca;

@Repository("moldeBocaRepository")
public class MoldeBocaRepositoryImpl extends NJBaseRepository<Long, MoldeBoca> implements MoldeRepositoryCustom {

	public MoldeBocaRepositoryImpl(EntityManager entityManager) {
		super(MoldeBoca.class, entityManager);
	}

}
