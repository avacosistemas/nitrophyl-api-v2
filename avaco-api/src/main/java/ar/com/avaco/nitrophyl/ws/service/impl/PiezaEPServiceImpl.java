package ar.com.avaco.nitrophyl.ws.service.impl;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.moldes.Molde;
import ar.com.avaco.nitrophyl.domain.entities.moldes.PlanoClasificacion;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaPlano;
import ar.com.avaco.nitrophyl.domain.entities.pieza.PiezaTipo;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Proceso;
import ar.com.avaco.nitrophyl.service.formula.FormulaService;
import ar.com.avaco.nitrophyl.service.molde.MoldeService;
import ar.com.avaco.nitrophyl.service.molde.PiezaTipoService;
import ar.com.avaco.nitrophyl.service.pieza.PiezaService;
import ar.com.avaco.nitrophyl.ws.dto.PiezaCreacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.PiezaDTO;
import ar.com.avaco.nitrophyl.ws.service.PiezaEPService;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("piezaEPService")
public class PiezaEPServiceImpl extends CRUDEPBaseService<Long, PiezaDTO, Pieza, PiezaService>
		implements PiezaEPService {

	private FormulaService formulaService;

	private MoldeService moldeService;

	private PiezaTipoService piezaTipoService;

	@Override
	public PiezaDTO update(PiezaDTO dto) throws BusinessException {
		// TODO Auto-generated method stub
		return super.update(dto);
	}
	
	@Override
	public void marcarVigente(Long piezaId) {
		// Pieza a marcar
		Pieza pieza = this.service.get(piezaId);

		// Pieza vigente actual
		Pieza piezaVigente = this.service.getVigenteByCodigoInterno(pieza.getCodigoInterno());

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
		pieza.setDenominacion(dto.getDenominacion());
		pieza.setFechaActualizacion(fechaYHoraActual);
		pieza.setFechaCreacion(fechaYHoraActual);
		pieza.setFechaRevision(fechaYHoraActual);

		Formula formula = formulaService.get(dto.getIdFormula());
		pieza.setFormula(formula);

		pieza.setId(null);

		Molde molde = moldeService.get(dto.getIdMolde());
		pieza.getMoldes().add(molde);

		PiezaPlano plano = new PiezaPlano();
		plano.setArchivo(dto.getPlanoArchivo());
		plano.setClasificacion(PlanoClasificacion.valueOf(dto.getPlanoClasificacion()));
		plano.setCodigo(dto.getPlanoCodigo());
		plano.setFechaActualizacion(fechaYHoraActual);
		plano.setFechaCreacion(fechaYHoraActual);
		plano.setObservaciones(dto.getPlanoObservaciones());
		plano.setRevision(dto.getPlanoRevision());
		plano.setUsuarioActualizacion(username);
		plano.setUsuarioCreacion(username);
		pieza.getPlanos().add(plano);

		pieza.setDureza(dto.getDureza());
		pieza.setEspesorMaximo(dto.getEspesorMaximo());
		pieza.setEspesorMinimo(dto.getEspesorMinimo());
		pieza.setObservacionesPesoCrudo(dto.getObservacionesPesoCrudo());
		pieza.setPesoCrudo(dto.getPesoCrudo());

		Proceso proceso = new Proceso();
		proceso.setPieza(pieza);
		pieza.setProceso(proceso);

		pieza.setRevision(0L);

		PiezaTipo tipo = this.piezaTipoService.get(dto.getIdTipoPieza());
		pieza.setTipo(tipo);

		pieza.setVigente(false);

		UUID randomUUID = java.util.UUID.randomUUID();
		pieza.setCodigoInterno(randomUUID.toString());

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
	
}
