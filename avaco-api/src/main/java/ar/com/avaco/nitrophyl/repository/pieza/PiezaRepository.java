package ar.com.avaco.nitrophyl.repository.pieza;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;

public interface PiezaRepository extends NJRepository<Long, Pieza>, PiezaRepositoryCustom {


}
