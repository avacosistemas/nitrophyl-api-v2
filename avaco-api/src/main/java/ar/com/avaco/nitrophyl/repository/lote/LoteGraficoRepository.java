package ar.com.avaco.nitrophyl.repository.lote;

import java.util.Optional;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.nitrophyl.domain.entities.moldes.LoteGrafico;

public interface LoteGraficoRepository extends NJRepository<Long, LoteGrafico>, LoteGraficoRepositoryCustom {

	Optional<LoteGrafico> findFirstByIdLoteOrderByFecha(Long idLote);
	
}
