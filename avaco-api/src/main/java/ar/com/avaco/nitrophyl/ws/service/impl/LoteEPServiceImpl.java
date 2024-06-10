package ar.com.avaco.nitrophyl.ws.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;

import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;
import ar.com.avaco.nitrophyl.domain.entities.reporte.ReporteLoteConfiguracionCliente;
import ar.com.avaco.nitrophyl.service.lote.LoteService;
import ar.com.avaco.nitrophyl.service.reporte.ReporteLoteConfiguracionClienteService;
import ar.com.avaco.nitrophyl.ws.dto.LoteDTO;
import ar.com.avaco.nitrophyl.ws.service.LoteEPService;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.informe.InformeBuilder;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("loteEPService")
public class LoteEPServiceImpl extends CRUDEPBaseService<Long, LoteDTO, Lote, LoteService> implements LoteEPService {

	private ReporteLoteConfiguracionClienteService reporteLoteConfigClienteService;

	@Override
	@Resource(name = "loteService")
	protected void setService(LoteService service) {
		this.service = service;
	}

	@Resource(name = "reporteLoteConfiguracionClienteService")
	public void setReporteLoteConfigClienteService(
			ReporteLoteConfiguracionClienteService reporteLoteConfigClienteService) {
		this.reporteLoteConfigClienteService = reporteLoteConfigClienteService;
	}

	@Override
	protected Lote convertToEntity(LoteDTO dto) {
		Lote lote = new Lote();
		Formula formula = new Formula();
		formula.setId(dto.getIdFormula());
		lote.setFormula(formula);
		lote.setFecha(DateUtils.toDate(dto.getFecha(), DateUtils.PATTERN_SOLO_FECHA));
		lote.setObservaciones(dto.getObservaciones());
		lote.setNroLote(dto.getNroLote());
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
	public void generarReporteLoteCliente(Long idLote, Long idCliente) {
		Lote lote = this.service.getLoteCompleto(idLote);
		List<ReporteLoteConfiguracionCliente> configuraciones = reporteLoteConfigClienteService
				.listEqField("cliente.id", idCliente);
		InformeBuilder ib = new InformeBuilder();
		try {
			ib.generarReporte(lote, configuraciones);
		} catch (DocumentException | IOException | URISyntaxException e) {
			e.printStackTrace();
		}

	}

}
