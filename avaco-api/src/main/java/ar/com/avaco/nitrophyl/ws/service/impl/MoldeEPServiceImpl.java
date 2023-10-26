package ar.com.avaco.nitrophyl.ws.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.moldes.EstadoBoca;
import ar.com.avaco.nitrophyl.domain.entities.moldes.EstadoMolde;
import ar.com.avaco.nitrophyl.domain.entities.moldes.Molde;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldeBoca;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldeDimension;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldeFoto;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldePlano;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldeRegistro;
import ar.com.avaco.nitrophyl.domain.entities.moldes.TipoRegistroMolde;
import ar.com.avaco.nitrophyl.service.cliente.ClienteService;
import ar.com.avaco.nitrophyl.service.molde.MoldeBocaService;
import ar.com.avaco.nitrophyl.service.molde.MoldeDimensionService;
import ar.com.avaco.nitrophyl.service.molde.MoldeFotoService;
import ar.com.avaco.nitrophyl.service.molde.MoldePlanoService;
import ar.com.avaco.nitrophyl.service.molde.MoldeRegistroService;
import ar.com.avaco.nitrophyl.service.molde.MoldeService;
import ar.com.avaco.nitrophyl.ws.dto.MoldeBocaListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeClienteDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeDimensionListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeFotoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeFotoListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldePlanoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldePlanoListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeRegistroDTO;
import ar.com.avaco.nitrophyl.ws.service.MoldeEPService;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Transactional
@Service("moldeEPService")
public class MoldeEPServiceImpl extends CRUDEPBaseService<Long, MoldeDTO, Molde, MoldeService>
		implements MoldeEPService {

	@Resource(name = "moldeDimensionService")
	private MoldeDimensionService moldeDimensionService;

	@Resource(name = "moldeBocaService")
	private MoldeBocaService moldeBocaService;

	@Resource(name = "moldeRegistroService")
	private MoldeRegistroService moldeRegistroService;

	@Resource(name = "moldePlanoService")
	private MoldePlanoService moldePlanoService;

	@Resource(name = "moldeFotoService")
	private MoldeFotoService moldeFotoService;

	@Resource(name = "clienteService")
	private ClienteService clienteService;

	@Override
	protected Molde convertToEntity(MoldeDTO dto) {
		Molde molde = new Molde();
		molde.setCodigo(dto.getCodigo());
		molde.setPropio(dto.isPropio());
		molde.setEstado(EstadoMolde.valueOf(dto.getEstado()));
		molde.setId(dto.getId());
		molde.setNombre(dto.getNombre());
		molde.setObservaciones(dto.getObservaciones());
		molde.setUbicacion(dto.getUbicacion());
		if (!dto.isPropio()) {
			Cliente duenio = clienteService.get(dto.getIdClienteDuenio());
			molde.setDuenio(duenio);
		}
		return molde;
	}

	@Override
	protected MoldeDTO convertToDto(Molde entity) {
		MoldeDTO moldeDto = new MoldeDTO();
		moldeDto.setCodigo(entity.getCodigo());
		moldeDto.setEstado(entity.getEstado().toString());
		moldeDto.setPropio(entity.isPropio());
		moldeDto.setId(entity.getId());
		moldeDto.setNombre(entity.getNombre());
		moldeDto.setObservaciones(entity.getObservaciones());
		moldeDto.setUbicacion(entity.getUbicacion());
		if (!entity.isPropio()) {
			moldeDto.setClienteDuenio(entity.getDuenio().getNombre());
			moldeDto.setIdClienteDuenio(entity.getDuenio().getId());
		}
		return moldeDto;
	}

	@Override
	@Resource(name = "moldeService")
	protected void setService(MoldeService service) {
		this.service = service;
	}

	@Override
	public List<MoldeDTO> listMoldes() {
		return convertToDtos(this.service.list());
	}

	@Override
	public List<MoldeListadoDTO> listado() {
		List<Molde> listado = this.service.list();
		if (listado != null) {
			List<MoldeListadoDTO> returnedList = new ArrayList<MoldeListadoDTO>();
			for (Molde molde : listado) {
				MoldeListadoDTO dto = new MoldeListadoDTO();
				dto.setCodigo(molde.getCodigo());
				dto.setEstado(molde.getEstado().toString());
				dto.setNombre(molde.getNombre());
				dto.setId(molde.getId());
				returnedList.add(dto);
			}
			return returnedList;
		}
		return null;

	}

	@Override
	public List<MoldeBocaListadoDTO> getMoldesBoca(Long idMolde) {
		List<MoldeBoca> listado = moldeBocaService.getByMolde(idMolde);
		if (listado != null) {
			List<MoldeBocaListadoDTO> returnedListado = new ArrayList<MoldeBocaListadoDTO>();
			for (MoldeBoca moldeBoca : listado) {
				MoldeBocaListadoDTO moldeBocaListadoDTO = new MoldeBocaListadoDTO();
				moldeBocaListadoDTO.setEstado(moldeBoca.getEstado().toString());
				moldeBocaListadoDTO.setNroBoca(moldeBoca.getNroBoca());
				moldeBocaListadoDTO.setDescripcion(moldeBoca.getDescripcion());
				returnedListado.add(moldeBocaListadoDTO);
			}
			return returnedListado;
		}
		return null;
	}

	@Override
	public List<MoldeBocaListadoDTO> updateMoldesBoca(Long idMolde, List<MoldeBocaListadoDTO> moldeBocaListadoDTO) {
		if (moldeBocaListadoDTO != null) {
			List<MoldeBoca> listado = new ArrayList<MoldeBoca>();

			Molde molde = service.get(idMolde);

			for (MoldeBocaListadoDTO dto : moldeBocaListadoDTO) {
				MoldeBoca moldeBoca = new MoldeBoca();
				moldeBoca.setEstado(EstadoBoca.valueOf(dto.getEstado()));
				moldeBoca.setNroBoca(dto.getNroBoca());
				moldeBoca.setIdMolde(idMolde);
				moldeBoca.setDescripcion(dto.getDescripcion());
				listado.add(moldeBoca);
			}

			moldeBocaService.removeByMolde(idMolde);
			moldeBocaService.save(listado);

			molde.setBocas(listado.size());
			service.save(molde);

			return moldeBocaListadoDTO;
		}
		return null;
	}

	@Override
	public List<MoldeDimensionListadoDTO> getMoldesDimension(Long idMolde) {
		List<MoldeDimension> listado = moldeDimensionService.getByMolde(idMolde);
		if (listado != null) {
			List<MoldeDimensionListadoDTO> returnedListado = new ArrayList<MoldeDimensionListadoDTO>();
			for (MoldeDimension moldeDimension : listado) {
				MoldeDimensionListadoDTO dto = new MoldeDimensionListadoDTO();
				dto.setTipoDimension(moldeDimension.getTipodimension());
				dto.setValor(moldeDimension.getValordimension());
				returnedListado.add(dto);
			}
			return returnedListado;
		}
		return null;
	}

	@Override
	public List<MoldeDimensionListadoDTO> updateMoldeDimensiones(Long idMolde,
			List<MoldeDimensionListadoDTO> moldeDimensionListadoDTOs) {

		if (moldeDimensionListadoDTOs != null) {
			List<MoldeDimension> listado = new ArrayList<MoldeDimension>();

			moldeDimensionService.removeByMolde(idMolde);

			for (MoldeDimensionListadoDTO dto : moldeDimensionListadoDTOs) {
				MoldeDimension moldeDimension = new MoldeDimension();
				moldeDimension.setIdMolde(idMolde);
				moldeDimension.setTipodimension(dto.getTipoDimension());
				moldeDimension.setValordimension(dto.getValor());
				listado.add(moldeDimension);
			}

			this.moldeDimensionService.save(listado);
			return moldeDimensionListadoDTOs;
		}
		return null;
	}

	@Override
	public List<MoldeRegistroDTO> getMoldesRegistro(Long idMolde) {
		List<MoldeRegistro> list = moldeRegistroService.listByMoldeId(idMolde);
		List<MoldeRegistroDTO> ret = new ArrayList<>();
		list.forEach(mr -> ret.add(new MoldeRegistroDTO(mr)));
		return ret;
	}

	@Override
	public MoldeRegistroDTO saveMoldeRegistro(MoldeRegistroDTO moldeRegistroDTO) {
		MoldeRegistro mr = moldeRegistroService.getUltimoRegistro(moldeRegistroDTO.getIdMolde());
		MoldeRegistro nmr;
		if (mr == null || (mr != null && mr.getTipoRegistro().equals(TipoRegistroMolde.EGRESO))) {
			nmr = moldeRegistroService.registrarIngreso(moldeRegistroDTO.getComentarios(),
					moldeRegistroDTO.getIdMolde());
		} else {
			nmr = moldeRegistroService.registrarEgreso(moldeRegistroDTO.getComentarios(),
					moldeRegistroDTO.getIdMolde());
		}
		return new MoldeRegistroDTO(nmr);
	}

	@Override
	public List<MoldeFotoListadoDTO> getMoldesFoto(Long idMolde) {
		List<MoldeFoto> list = moldeFotoService.listByMoldeId(idMolde);
		List<MoldeFotoListadoDTO> ret = new ArrayList<>();
		list.forEach(mp -> ret.add(new MoldeFotoListadoDTO(mp)));
		return ret;
	}

	@Override
	public MoldePlanoDTO addMoldePlano(MoldePlanoDTO moldePlanoDTO) throws ErrorValidationException, BusinessException {

		if (moldePlanoDTO != null) {
			MoldePlano mp = new MoldePlano();
			mp.setIdMolde(moldePlanoDTO.getIdMolde());
			mp.setNombreArchivo(moldePlanoDTO.getNombreArchivo());
			mp.setArchivo(moldePlanoDTO.getArchivo());
			mp.setFecha(DateUtils.getFechaYHoraActual());
			mp.setDescripcion(moldePlanoDTO.getDescripcion());
			mp.setVersion(1);

			MoldePlano nmp = this.moldePlanoService.addMoldePlano(mp);
			return new MoldePlanoDTO(nmp);
		}
		return null;
	}

	@Override
	public MoldePlanoDTO downloadMoldePlano(Long idMoldePlano) {
		MoldePlano moldePlano = moldePlanoService.get(idMoldePlano);

		MoldePlanoDTO moldePlanoDTO = new MoldePlanoDTO();
		moldePlanoDTO.setIdMolde(idMoldePlano);
		moldePlanoDTO.setNombreArchivo(moldePlano.getNombreArchivo());
		moldePlanoDTO.setArchivo(moldePlano.getArchivo());

		return moldePlanoDTO;
	}

	@Override
	public List<MoldePlanoListadoDTO> getMoldesPlano(Long idMolde) {
		List<MoldePlano> list = moldePlanoService.listByMoldeId(idMolde);
		List<MoldePlanoListadoDTO> ret = new ArrayList<>();
		list.forEach(mp -> ret.add(new MoldePlanoListadoDTO(mp)));
		return ret;
	}

	@Override
	public MoldeFotoDTO addMoldeFoto(MoldeFotoDTO moldeFotoDTO) throws ErrorValidationException, BusinessException {

		if (moldeFotoDTO != null) {
			MoldeFoto mf = new MoldeFoto();
			mf.setIdMolde(moldeFotoDTO.getIdMolde());
			mf.setNombreArchivo(moldeFotoDTO.getNombreArchivo());
			mf.setArchivo(moldeFotoDTO.getArchivo());
			mf.setFecha(DateUtils.getFechaYHoraActual());
			mf.setDescripcion(moldeFotoDTO.getDescripcion());
			mf.setVersion(1);

			MoldeFoto nmf = this.moldeFotoService.addMoldeFoto(mf);
			return new MoldeFotoDTO(nmf);
		}
		return null;
	}

	@Override
	public MoldeFotoDTO downloadMoldeFoto(Long idMoldeFoto) {
		MoldeFoto moldeFoto = moldeFotoService.get(idMoldeFoto);

		MoldeFotoDTO moldeFotoDTO = new MoldeFotoDTO();
		moldeFotoDTO.setIdMolde(idMoldeFoto);
		moldeFotoDTO.setNombreArchivo(moldeFoto.getNombreArchivo());
		moldeFotoDTO.setArchivo(moldeFoto.getArchivo());

		return moldeFotoDTO;
	}

	@Override
	public List<MoldeClienteDTO> getMoldeClientes(Long idMolde) {
		Molde m = this.service.get(idMolde);
		List<MoldeClienteDTO> clientes = new ArrayList<>();
		m.getClientes().forEach(x -> {
			clientes.add(new MoldeClienteDTO(x, m));
		});
		return clientes;
	}

	@Override
	public List<MoldeClienteDTO> updateMoldeClientes(Long idMolde, List<MoldeClienteDTO> moldeClientesListadoDTOs) {
		Molde m = this.service.get(idMolde);
		List<Cliente> clientes = this.clienteService.getByIds(
				moldeClientesListadoDTOs.stream().map(MoldeClienteDTO::getIdCliente).collect(Collectors.toList()));
		m.getClientes().clear();
		m.getClientes().addAll(clientes);
		this.service.update(m);
		return getMoldeClientes(idMolde);
	}

}
