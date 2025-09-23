package ar.com.avaco.nitrophyl.repository.pieza;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Cotizacion;

@Repository("cotizacionRepository")
public class CotizacionRepositoryImpl extends NJBaseRepository<Long, Cotizacion> implements CotizacionRepositoryCustom {

	public CotizacionRepositoryImpl(EntityManager entityManager) {
		super(Cotizacion.class, entityManager);
	}

}