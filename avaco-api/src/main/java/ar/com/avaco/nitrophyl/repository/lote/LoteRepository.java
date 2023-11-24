package ar.com.avaco.nitrophyl.repository.lote;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;

public interface LoteRepository extends NJRepository<Long, Lote>, LoteRepositoryCustom {

}
