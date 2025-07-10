package ar.com.avaco.nitrophyl.ws.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.moldes.Molde;
import ar.com.avaco.nitrophyl.domain.entities.moldes.PlanoClasificacion;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaCliente;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaFormula;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaMolde;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaPlano;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaTipo;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Proceso;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Terminacion;
import ar.com.avaco.nitrophyl.domain.entities.pieza.UnidadDureza;
import ar.com.avaco.nitrophyl.service.cliente.ClienteService;
import ar.com.avaco.nitrophyl.service.formula.FormulaService;
import ar.com.avaco.nitrophyl.service.molde.MoldeService;
import ar.com.avaco.nitrophyl.service.pieza.PiezaService;
import ar.com.avaco.nitrophyl.service.pieza.PiezaTipoService;
import ar.com.avaco.nitrophyl.ws.dto.BombeoDTO;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaCreacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaEdicionDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaPUTDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaGrillaDTO;
import ar.com.avaco.nitrophyl.ws.dto.PrensaDTO;
import ar.com.avaco.nitrophyl.ws.service.PiezaEPService;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.service.CRUDAuditableEPBaseService;

@Transactional
@Service("piezaEPService")
public class PiezaEPServiceImpl extends CRUDAuditableEPBaseService<Long, PiezaDTO, Pieza, PiezaService>
		implements PiezaEPService {

	public PiezaEPServiceImpl() {
		super(Pieza.class, PiezaDTO.class);
	}

	private ClienteService clienteService;

	private FormulaService formulaService;

	private MoldeService moldeService;

	private PiezaTipoService piezaTipoService;

	@Override
	public PageDTO<PiezaGrillaDTO> listGrilla(PiezaFilterDTO filter) {
		List<PiezaGrillaDTO> listGrilla = this.service.listGrilla(filter);
		Integer rows = !listGrilla.isEmpty() ? listGrilla.get(0).getRows() : 0;
		return new PageDTO<PiezaGrillaDTO>(listGrilla, rows);
	}

	@Override
	public void marcarVigente(Long piezaId) {
		// Pieza a marcar
		Pieza pieza = this.service.get(piezaId);

		// Pieza vigente actual
		Pieza piezaVigente = this.service.getVigenteByCodigoInterno(pieza.getCodigo());

		if (pieza.getRevision() != piezaVigente.getRevision() + 1) {
			// Ocurrio un error, no puede setearse como vigente una revisión que no es la
			// posterior a la actual
			throw new ErrorValidationException(
					"No se puede marcar la pieza/proceso como vigente ya que no es la ultima");
		}
		piezaVigente.setVigente(false);
		pieza.setVigente(true);

		this.service.update(piezaVigente);
		this.service.update(pieza);
	}

	@Override
	public void nuevaRevision(Long piezaId) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Pieza pieza = this.service.get(piezaId);

		if (!pieza.getVigente()) {
			throw new ErrorValidationException(
					"No se puede generar una nueva revisión ya que la pieza/proceso seleccionada no es la vigente");
		}

		Pieza clonada = pieza.clonar(username);
		this.service.save(clonada);
	}

	@Override
	public void create(PiezaCreacionDTO dto) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Date fechaYHoraActual = DateUtils.getFechaYHoraActual();

		Pieza pieza = new Pieza();
		pieza.setCodigo(dto.getCodigo());
		pieza.setDenominacion(dto.getDenominacion());

		pieza.setRevision(dto.getRevisionIncial());
		pieza.setFechaRevision(fechaYHoraActual);

		pieza.setFechaCreacionPiezaProceso(fechaYHoraActual);

		pieza.setUsuarioCreacion(username);
		pieza.setFechaCreacion(fechaYHoraActual);

		Formula formula = formulaService.get(dto.getIdFormula());

		PiezaFormula detalle = new PiezaFormula();
		detalle.setFormula(formula);

		detalle.setDurezaMinima(dto.getDurezaMinima());
		detalle.setDurezaMaxima(dto.getDurezaMinima());
		detalle.setUnidadDureza(UnidadDureza.valueOf(dto.getUnidadDureza()));

		detalle.setEspesorMinimo(dto.getEspesorMinimo());
		detalle.setEspesorMaximo(dto.getEspesorMaximo());

		detalle.setPesoCrudo(dto.getPesoCrudo());
		detalle.setObservacionesPesoCrudo(dto.getObservacionesPesoCrudo());

		pieza.setDetalleFormula(detalle);

		pieza.setId(null);

		Molde molde = moldeService.get(dto.getIdMolde());
		PiezaMolde pm = new PiezaMolde();
		pm.setObservaciones(dto.getObservacionesMolde());
		pm.setFechaCreacion(fechaYHoraActual);
		pm.setUsuarioCreacion(username);
		pm.setMolde(molde);
		pm.setPieza(pieza);
		pieza.getMoldes().add(pm);

		PiezaPlano plano = new PiezaPlano();
		plano.setArchivo(dto.getPlanoArchivo());
		plano.setClasificacion(PlanoClasificacion.valueOf(dto.getPlanoClasificacion()));
		plano.setCodigo(dto.getPlanoCodigo());
		plano.setFechaCreacion(fechaYHoraActual);
		plano.setObservaciones(dto.getPlanoObservaciones());
		plano.setRevision(dto.getPlanoRevision());
		plano.setUsuarioCreacion(username);
		plano.setPieza(pieza);
		pieza.getPlanos().add(plano);

		Cliente cliente = this.clienteService.get(dto.getIdCliente());

		PiezaCliente piezaCliente = new PiezaCliente();
		piezaCliente.setCliente(cliente);
		piezaCliente.setFechaCreacion(fechaYHoraActual);
		piezaCliente.setNombrePiezaPersonalizado(dto.getNombrePiezaCliente());
		piezaCliente.setPieza(pieza);
		piezaCliente.setUsuarioCreacion(username);

		pieza.getClientes().add(piezaCliente);

		Terminacion terminacion = new Terminacion();
		Proceso proceso = new Proceso();

		terminacion.setProceso(proceso);
		proceso.setTerminacion(terminacion);
		pieza.setProceso(proceso);
		proceso.setPieza(pieza);

		pieza.setRevision(dto.getRevisionIncial());

		PiezaTipo tipo = this.piezaTipoService.get(dto.getIdTipoPieza());
		pieza.setTipo(tipo);

		pieza.setVigente(false);

		this.service.save(pieza);
	}

	@Override
	protected Pieza convertToEntity(PiezaDTO dto) {
		throw new RuntimeException("Not implemented");
	}

	@Override
	protected PiezaDTO convertToDto(Pieza entity) {
		throw new RuntimeException("Not implemented");
	}

	@Override
	@Resource(name = "piezaService")
	protected void setService(PiezaService service) {
		this.service = service;
	}

	@Resource(name = "formulaService")
	public void setFormulaService(FormulaService formulaService) {
		this.formulaService = formulaService;
	}

	@Resource(name = "moldeService")
	public void setMoldeService(MoldeService moldeService) {
		this.moldeService = moldeService;
	}

	@Resource(name = "piezaTipoService")
	public void setPiezaTipoService(PiezaTipoService piezaTipoService) {
		this.piezaTipoService = piezaTipoService;
	}

	@Resource(name = "clienteService")
	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@Override
	public PiezaEdicionDTO getByIdEdicion(Long idPieza) {
		Pieza pieza = this.service.get(idPieza);

		PiezaEdicionDTO dto = new PiezaEdicionDTO();
		dto.setId(pieza.getId());
		dto.setDenominacion(pieza.getDenominacion());
		dto.setTipo(pieza.getTipo().getNombre());
		dto.setCodigo(pieza.getCodigo());
		dto.setNombreFormula(pieza.getDetalleFormula().getFormula().getNombre());
		dto.setDurezaMinima(pieza.getDetalleFormula().getDurezaMinima());
		dto.setDurezaMaxima(pieza.getDetalleFormula().getDurezaMaxima());
		dto.setUnidadDureza(pieza.getDetalleFormula().getUnidadDureza());
		dto.setEspesorMinimo(pieza.getDetalleFormula().getEspesorMinimo());
		dto.setEspesorMaximo(pieza.getDetalleFormula().getEspesorMaximo());
		dto.setPesoCrudo(pieza.getDetalleFormula().getPesoCrudo());
		dto.setObservacionesPesoCrudo(pieza.getDetalleFormula().getObservacionesPesoCrudo());
		dto.setRevision(pieza.getRevision());
		dto.setFechaRevision(pieza.getFechaRevision());
		dto.setVigente(pieza.getVigente());
		dto.setFechaCreacionPiezaProceso(pieza.getFechaCreacionPiezaProceso());
		dto.setObservacionesRevision(pieza.getObservacionesRevision());

		dto.setPrecalentamientoUnidad(pieza.getProceso().getPrecalentamiento().getUnidad());
		dto.setPrecalentamientoValor(pieza.getProceso().getPrecalentamiento().getValor());

		pieza.getProceso().getPrensas().forEach(prensa -> {
			dto.getPrensas().add(super.modelMapper.map(prensa, PrensaDTO.class));
		});

		dto.setVulcanizacionTemperaturaMin(pieza.getProceso().getVulcanizacion().getTemperaturaMin());
		dto.setVulcanizacionTemperaturaMax(pieza.getProceso().getVulcanizacion().getTemperaturaMax());
		dto.setVulcanizacionTiempo(pieza.getProceso().getVulcanizacion().getTiempo());

		pieza.getProceso().getBombeos().forEach(bombeo -> {
			dto.getBombeos().add(modelMapper.map(bombeo, BombeoDTO.class));
		});

		dto.setDesmoldante(pieza.getProceso().getDesmoldante());
		dto.setPostCura(pieza.getProceso().getPostCura());

		return dto;

	}

	@Override
	public void update(Long idPieza, PiezaPUTDTO piezaFormula) {
		Pieza pieza = this.service.get(idPieza);
		pieza.getDetalleFormula().setDurezaMaxima(piezaFormula.getDurezaMaxima());
		pieza.getDetalleFormula().setDurezaMinima(piezaFormula.getDurezaMinima());
		pieza.getDetalleFormula().setEspesorMaximo(piezaFormula.getEspesorMaximo());
		pieza.getDetalleFormula().setEspesorMinimo(piezaFormula.getEspesorMinimo());
		pieza.getDetalleFormula().setObservacionesPesoCrudo(piezaFormula.getObservacionesPesoCrudo());
		pieza.getDetalleFormula().setPesoCrudo(piezaFormula.getPesoCrudo());
		pieza.getDetalleFormula().setUnidadDureza(piezaFormula.getUnidadDureza());
		pieza.setObservacionesRevision(piezaFormula.getObservacionesRevision());
		pieza.setUsuarioActualizacion(SecurityContextHolder.getContext().getAuthentication().getName());
		pieza.setFechaActualizacion(DateUtils.getFechaYHoraActual());
		this.service.update(pieza);
	}

}
