package ar.com.avaco.nitrophyl.ws.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.lote.EstadoLote;
import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;
import ar.com.avaco.nitrophyl.service.cliente.ClienteService;
import ar.com.avaco.nitrophyl.service.formula.FormulaService;
import ar.com.avaco.nitrophyl.service.lote.LoteService;
import ar.com.avaco.nitrophyl.service.reporte.ReporteLoteConfiguracionClienteService;
import ar.com.avaco.nitrophyl.ws.dto.ArchivoDTO;
import ar.com.avaco.nitrophyl.ws.dto.LoteDTO;
import ar.com.avaco.nitrophyl.ws.service.LoteEPService;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.informe.InformeBuilder;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("loteEPService")
public class LoteEPServiceImpl extends CRUDEPBaseService<Long, LoteDTO, Lote, LoteService> implements LoteEPService {

	private ReporteLoteConfiguracionClienteService reporteLoteConfigClienteService;

	private ClienteService clienteService;

	private FormulaService formulaService;
	
	@Resource(name = "reporteLoteConfiguracionClienteService")

	public void setReporteLoteConfigClienteService(
			ReporteLoteConfiguracionClienteService reporteLoteConfigClienteService) {
		this.reporteLoteConfigClienteService = reporteLoteConfigClienteService;
	}

	@Override
	public LoteDTO save(LoteDTO dto) throws BusinessException {

		List<Lote> listPattern = this.service.listPattern("nroLote", dto.getNroLote());
		if (!listPattern.isEmpty()) {
			throw new BusinessException("El número de lote ya existe");
		}
		dto.setEstado("PENDIENTE_APROBACION");
		return super.save(dto);
	}

	@Override
	public LoteDTO update(LoteDTO dto) throws BusinessException {
		Lote update = this.service.get(dto.getId());
		update.setFecha(DateUtils.toDate(dto.getFecha(), DateUtils.PATTERN_SOLO_FECHA));
		if (dto.getIdFormula() != update.getFormula().getId()) {
			if (this.service.hasEnsayos(dto.getId())) {
				throw new BusinessException("No puede cambiarse la fórmula del lote porque tiene ensayos asociados");
			} else {
				Formula formula = this.formulaService.get(dto.getIdFormula());
				update.setFormula(formula);
				update.setRevisionParametros(formula.getRevision());
			}
		}
		List<Lote> listPattern = this.service.listPattern("nroLote", dto.getNroLote());
		if (!listPattern.isEmpty() && listPattern.get(0).getId() != dto.getId()) {
			throw new BusinessException("No puede cambiarse el número de lote porque ya existe");
		}

		update.setNroLote(dto.getNroLote());
		update.setObservaciones(dto.getObservaciones());
		return convertToDto(this.service.save(update));
	}

	@Override
	protected Lote convertToEntity(LoteDTO dto) {
		Lote lote = new Lote();
		Formula formula = new Formula();
		formula.setId(dto.getIdFormula());
		lote.setId(dto.getId());
		lote.setFormula(formula);
		lote.setFecha(DateUtils.toDate(dto.getFecha(), DateUtils.PATTERN_SOLO_FECHA));
		lote.setObservaciones(dto.getObservaciones());
		lote.setNroLote(dto.getNroLote());
		lote.setEstado(EstadoLote.valueOf(dto.getEstado()));
		if (StringUtils.isNotBlank(dto.getFechaEstado())) {
			lote.setFechaEstado(DateUtils.toDate(dto.getFechaEstado(), DateUtils.PATTERN_SOLO_FECHA));
		}
		lote.setObservacionesEstado(dto.getObservacionesEstado());
		return lote;
	}

	@Override
	protected LoteDTO convertToDto(Lote entity) {
		LoteDTO dto = new LoteDTO();
		dto.setFecha(DateUtils.toStringFecha(entity.getFecha()));
		dto.setFormula(entity.getFormula().toString());
		dto.setId(entity.getId());
		dto.setIdFormula(entity.getFormula().getId());
		dto.setObservaciones(entity.getObservaciones());
		dto.setNroLote(entity.getNroLote());
		dto.setEstado(entity.getEstado().toString());
		dto.setFormulaSimple(entity.getFormula().getNombre());
		if (entity.getFechaEstado() != null) {
			dto.setFechaEstado(DateUtils.toStringFecha(entity.getFechaEstado()));
		}
		dto.setObservacionesEstado(entity.getObservacionesEstado());
		return dto;
	}

	@Override
	public void aprobar(Long idLote, String estado, String observaciones) {
		this.service.aprobar(idLote, estado, observaciones);
	}

	@Override
	public void rechazar(Long idLote, String observaciones) {
		this.service.rechazar(idLote, observaciones);
	}

	@Override
	public ArchivoDTO generarReporteLoteCliente(Long idLote, Long idCliente) throws BusinessException {
		Lote lote = this.service.getLoteCompleto(idLote);
		Cliente cliente = clienteService.getCliente(idCliente);
		InformeBuilder ib = new InformeBuilder();
		ArchivoDTO adto = new ArchivoDTO();
		try {
			adto = ib.generarReporte(lote, reporteLoteConfigClienteService, cliente);
		} catch (DocumentException | IOException | URISyntaxException e) {
			throw new BusinessException("No se pudo generar el informe", e);
		}
		return adto;

	}

	public void borrar(Long idLote) throws BusinessException {
		if (this.service.hasEnsayos(idLote))
			throw new BusinessException("No se puede borrar el lote porque tiene ensayos asociados");
		this.service.borrar(idLote);

	}

	@Override
	@Resource(name = "loteService")
	protected void setService(LoteService service) {
		this.service = service;
	}

	@Resource(name = "clienteService")
	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@Resource(name = "formulaService")
	public void setFormulaService(FormulaService formulaService) {
		this.formulaService = formulaService;
	}

}
