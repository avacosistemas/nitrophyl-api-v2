package ar.com.avaco.nitrophyl.repository.cliente;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Contacto;

/**
 * Repositorio de Contactos del Cliente
 */
@Repository("contactoRepository")
public class ContactoRepositoryImpl extends NJBaseRepository<Long, Contacto> implements ContactoRepositoryCustom {

	public ContactoRepositoryImpl(EntityManager entityManager) {
		super(Contacto.class, entityManager);
	}
	

}
