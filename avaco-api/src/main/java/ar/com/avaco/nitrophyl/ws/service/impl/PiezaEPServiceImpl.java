package ar.com.avaco.nitrophyl.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaCompuesta;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaSimple;
import ar.com.avaco.nitrophyl.domain.entities.pieza.TipoPieza;
import ar.com.avaco.nitrophyl.service.pieza.PiezaService;
import ar.com.avaco.nitrophyl.ws.dto.PiezaDTO;
import ar.com.avaco.nitrophyl.ws.service.PiezaEPService;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("piezaEPService")
public class PiezaEPServiceImpl extends CRUDEPBaseService<Long, PiezaDTO, Pieza, PiezaService>
		implements PiezaEPService {

	@Override
	protected Pieza convertToEntity(PiezaDTO dto) {
		if (dto == null)
			return null;
		
		// Validaciones en caso de Update.
		if (dto.getId() != null) {
			validateTipoPieza(dto);
		}

		Pieza pieza;
		if (dto.getTipo().equals(TipoPieza.SIMPLE)) {
			pieza = new PiezaSimple();
		} else {
			pieza = new PiezaCompuesta();
		}

		pieza.setCodigoPieza(dto.getCodigoPieza());
		pieza.setEsProducto(dto.getEsProducto());
		pieza.setCodigoInterno(dto.getCodigoInterno());
		pieza.setId(dto.getId());
		pieza.setNombre(dto.getNombre());
		pieza.setTipo(dto.getTipo());

		return pieza;
	}

	private void validateTipoPieza(PiezaDTO dto) {
		Pieza original = service.get(dto.getId());
		if (!original.getTipo().equals(dto.getTipo()))
			throw new IllegalStateException("No se puede modificar el tipo de una pieza. La pieza actual es de tipo "
					+ original.getTipo().toString());
	}

	@Override
	protected PiezaDTO convertToDto(Pieza entity) {
		PiezaDTO piezaDTO = new PiezaDTO();
		if (entity != null) {
			piezaDTO.setCodigoInterno(entity.getCodigoInterno());
			piezaDTO.setCodigoPieza(entity.getCodigoPieza());
			piezaDTO.setId(entity.getId());
			piezaDTO.setNombre(entity.getNombre());
			piezaDTO.setTipo(entity.getTipo());
			piezaDTO.setEsProducto(entity.getEsProducto());

			if (piezaDTO.getTipo().equals(TipoPieza.COMPUESTA)) {
				List<PiezaDTO> piezasDTO = new ArrayList<PiezaDTO>();
				if (entity.getPiezas() != null) {
					for (Pieza pieza : entity.getPiezas()) {
						piezasDTO.add(convertToDto(pieza));
					}
				}
				piezaDTO.setPiezas(piezasDTO);
			}
		}
		return piezaDTO;
	}

	@Override
	public List<PiezaDTO> listFilter(AbstractFilter abstractFilter) {
		List<Pieza> entities = service.listFilter(abstractFilter);
		List<PiezaDTO> dtos = convertPiezasSinHijos(entities);
		return dtos;
	}
	
	
	
	@Override
	public List<PiezaDTO> list() {
		List<Pieza> entities = service.list();
		List<PiezaDTO> dtos = convertPiezasSinHijos(entities);
		return dtos;
	}
	
	@Override
	public PiezaDTO get(Long id) {
		Pieza pieza = service.get(id);
		return convertToDto(pieza);
	}
	
	private List<PiezaDTO> convertPiezasSinHijos(List<Pieza> entities) {
		List<PiezaDTO> list = new ArrayList<>();
		for (Pieza entity : entities) {
			PiezaDTO piezaDTO = new PiezaDTO();
			piezaDTO.setCodigoInterno(entity.getCodigoInterno());
			piezaDTO.setCodigoPieza(entity.getCodigoPieza());
			piezaDTO.setId(entity.getId());
			piezaDTO.setNombre(entity.getNombre());
			piezaDTO.setTipo(entity.getTipo());
			piezaDTO.setEsProducto(entity.getEsProducto());
			list.add(piezaDTO);
		}
		return list;
	}

	@Override
	@Resource(name = "piezaService")
	protected void setService(PiezaService service) {
		this.service = service;
	}

	@Override
	public PiezaDTO addPiezaToCompuesta(Long id, Long idPieza) {
		Pieza saved = service.addPiezaToCompuesta(id, idPieza);
		return convertToDto(saved);
	}

	@Override
	public PiezaDTO removePiezaFromCompuesta(Long id, Long idPieza) {
		Pieza saved = service.removePiezaFromCompuesta(id, idPieza);
		return convertToDto(saved);
	}

	@Override
	public PiezaDTO addPiezaToProductoCompuesto(Long id, Long idPieza) {
		Pieza saved = service.addPiezaToCompuesta(id, idPieza);
		return convertToDto(saved);
	}

	@Override
	public PiezaDTO removePiezaFromProductoCompuesto(Long id, Long idPieza) {
		Pieza saved = service.removePiezaFromCompuesta(id, idPieza);
		return convertToDto(saved);
	}

}
