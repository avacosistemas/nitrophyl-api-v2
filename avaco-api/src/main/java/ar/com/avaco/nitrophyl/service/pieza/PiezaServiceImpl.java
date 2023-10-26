package ar.com.avaco.nitrophyl.service.pieza;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;
import ar.com.avaco.nitrophyl.domain.entities.pieza.TipoPieza;
import ar.com.avaco.nitrophyl.repository.pieza.PiezaRepository;

@Transactional
@Service("piezaService")
public class PiezaServiceImpl extends NJBaseService<Long, Pieza, PiezaRepository> implements PiezaService {

	private Logger logger = Logger.getLogger(getClass());

	@Resource(name = "piezaRepository")
	void setClienteRepository(PiezaRepository piezaRepository) {
		this.repository = piezaRepository;
	}

	@Override
	public List<Pieza> list() {
		return getRepository().findAll();
	}
	
	@Override
	public Pieza get(Long id) {
		// TODO Auto-generated method stub
		Pieza pieza = super.get(id);
		if (pieza.isCompuesta()) {
			Hibernate.initialize(pieza.getPiezas());
			pieza.getPiezas();
		}
		return pieza;
	}
	
	@Override
	public Pieza addPiezaToCompuesta(Long id, Long idPieza) {
		Pieza toAdd = repository.getOne(idPieza);

		if (toAdd == null)
			throw new EntityNotFoundException("No se encontro la pieza con ID: " + idPieza);

		Pieza compuesta = repository.getOne(id);

		if (compuesta == null)
			throw new EntityNotFoundException("No se encontro la pieza con ID: " + id);

		if (!compuesta.getPiezas().contains(toAdd)) {

			if (logger.isDebugEnabled()) {
				logger.debug("Removing pieza id: " + toAdd.getId() + " from " + compuesta.getId());
			}

			compuesta.getPiezas().add(toAdd);

			Map<Long, Long> existCompuestas = new HashMap<Long, Long>();
			existCompuestas.put(compuesta.getId(), compuesta.getId());

			validarCircularidad(compuesta.getPiezas(), existCompuestas);

			repository.save(compuesta);

			return toAdd;
		}

		return null;
	}

	private void validarCircularidad(List<Pieza> piezas, Map<Long, Long> existCompuestas) {
		for (Pieza pieza : piezas) {
			if (pieza.getTipo().equals(TipoPieza.COMPUESTA)) {
				if (existCompuestas.get(pieza.getId()) != null) {
					throw new IllegalStateException(
							"La pieza no se puede agregar porque crearia un conflicto de circularidad. El ID "
									+ pieza.getId() + " ya existe en el arbol de piezas.");
				} else {
					//Apilo Id Padre y analizo Arbol
					existCompuestas.put(pieza.getId(), pieza.getId());
					validarCircularidad(pieza.getPiezas(), existCompuestas);
					//Desapilo Id Padre
					existCompuestas.put(pieza.getId(), null);
				}
			}
		}
	}

	@Override
	public Pieza removePiezaFromCompuesta(Long id, Long idPieza) {
		Pieza toRemove = repository.getOne(idPieza);

		if (toRemove == null)
			throw new EntityNotFoundException("No se encontro la pieza con ID: " + idPieza);

		Pieza compuesta = repository.getOne(id);

		if (compuesta == null)
			throw new EntityNotFoundException("No se encontro la pieza con ID: " + id);

		if (compuesta.getPiezas().contains(toRemove)) {
			if (logger.isDebugEnabled()) {
				logger.debug("Removing pieza id: " + toRemove.getId() + " from " + compuesta.getId());
			}

			compuesta.getPiezas().remove(toRemove);
			repository.save(compuesta);

			return toRemove;
		}

		throw new EntityNotFoundException("La pieza con ID: " + idPieza + " no forma parte la pieza compuesta " + id);
	}

}
