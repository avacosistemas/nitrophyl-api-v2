package ar.com.avaco.nitrophyl.repository.molde;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.moldes.Molde;
import ar.com.avaco.nitrophyl.ws.dto.MoldeFilterDTO;

public interface MoldeRepository extends NJRepository<Long, Molde>, MoldeRepositoryCustom {

}
