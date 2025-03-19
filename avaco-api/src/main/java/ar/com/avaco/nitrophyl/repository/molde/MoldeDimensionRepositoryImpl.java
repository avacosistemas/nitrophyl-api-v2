package ar.com.avaco.nitrophyl.repository.molde;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldeDimension;
import ar.com.avaco.nitrophyl.ws.dto.MoldeFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeListadoDTO;

@Repository("moldeDimensionRepository")
public class MoldeDimensionRepositoryImpl extends NJBaseRepository<Long, MoldeDimension> implements MoldeDimensionRepositoryCustom {

	public MoldeDimensionRepositoryImpl(EntityManager entityManager) {
		super(MoldeDimension.class, entityManager);
	}

}
