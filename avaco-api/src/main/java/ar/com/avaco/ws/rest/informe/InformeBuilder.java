package ar.com.avaco.ws.rest.informe;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ar.com.avaco.nitrophyl.domain.entities.formula.ConfiguracionPruebaCondicion;
import ar.com.avaco.nitrophyl.domain.entities.lote.Ensayo;
import ar.com.avaco.nitrophyl.domain.entities.lote.EnsayoResultado;
import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;
import ar.com.avaco.nitrophyl.domain.entities.reporte.ReporteLoteConfiguracionCliente;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.dto.JSONResponse;

public class InformeBuilder {

	private static final BaseColor COLOR_GRIS_BORDES = new BaseColor(238, 238, 238);

	private final static Font fontHeaderTable = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.BLACK);
	private final static Font fontText = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);

	public ResponseEntity<JSONResponse> generarReporte(Lote lote, List<ReporteLoteConfiguracionCliente> configuracion)
			throws DocumentException, IOException, URISyntaxException {

		Document document = new Document(PageSize.A4);

		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(
					"c:\\nitrophyl\\nitrophyl-" + Calendar.getInstance().getTimeInMillis() + ".pdf"));

//			writer.setPageEvent(new PDFEventHelper());

			document.open();
			document.setMargins(20, 20, 10, 10);

			String empresa = "nitrophyl";
			Element encabezado = generarEncabezado(empresa);
			document.add(encabezado);

			Element datosLote = addDatosLotes(lote);
			generarSeccion(document, datosLote, "INFORME DE CALIDAD", true, true);

			for (Ensayo ensayo : lote.getEnsayos()) {

				long idMaquina = ensayo.getConfiguracionPrueba().getMaquina().getId();
				long idFormula = ensayo.getConfiguracionPrueba().getFormula().getId();

				Optional<ReporteLoteConfiguracionCliente> findFirst = configuracion.stream()
						.filter(x -> x.getFormula().getId() == idFormula && x.getMaquina().getId() == idMaquina)
						.findFirst();
				ReporteLoteConfiguracionCliente reporteLoteConfiguracionCliente = findFirst.isPresent()
						? findFirst.get()
						: null;

				Element addEnsayo = addEnsayo(ensayo, reporteLoteConfiguracionCliente);
				generarSeccion(document, addEnsayo, "Reómetro", true, true);
			}

			Paragraph observaciones = new Paragraph();
			observaciones.setSpacingAfter(10);
			String string = "La parametrización entre los valores por reometría y las propiedades físicas mencionadas";
			string += " esta realizada en base a un estudio realizdo en nuestro laboratorio sobre una cuerva patrón";
			string += " normalizada y los ensayos físicos directos descriptos por norma y bajo condiciones reguladas";
			observaciones.add(new Phrase(string));

//			generarSeccion(document, observaciones, "Observaciones", true, true);

			generarFirma(document);

		} catch (DocumentException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			document.close();
		}
		JSONResponse response = new JSONResponse();
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	private Element generarEncabezado(String empresa)
			throws BadElementException, MalformedURLException, IOException, URISyntaxException, DocumentException {
		PdfPTable table = generateTable(2);
		PdfPCell cell = getPDFPCell();
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		URL resource = null;
		if (empresa.equals("nitrophyl")) {
			resource = getClass().getClassLoader().getResource("nitro-logo.jpg");
		} else {
			resource = getClass().getClassLoader().getResource("elasint-logo.jpg");
		}
		cell.addElement(Image.getInstance(new File(resource.toURI()).getAbsolutePath()));
		table.addCell(cell);
		cell.setPhrase(
				new Phrase("INFORME DE CALIDAD", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK)));
		table.addCell(cell);

		Paragraph p = new Paragraph();
		p.add(table);

		return p;
	}

	private void generarFirma(Document document)
			throws BadElementException, MalformedURLException, IOException, URISyntaxException, DocumentException {
		PdfPTable table = generateTable(2);
		PdfPCell cell = getPDFPCell();
		cell.setBorder(0);

		URL resource = getClass().getClassLoader().getResource("firma-romina.jpg");
		Image firmaRomina = Image.getInstance(new File(resource.toURI()).getAbsolutePath());
		firmaRomina.setWidthPercentage(50);
		cell.addElement(firmaRomina);
		table.addCell(cell);

		cell = getPDFPCell();
		cell.setBorder(0);
		resource = getClass().getClassLoader().getResource("firma-graciela.jpg");
		Image firmagraciela = Image.getInstance(new File(resource.toURI()).getAbsolutePath());
		firmagraciela.setAlignment(Element.ALIGN_RIGHT);
		firmagraciela.setWidthPercentage(50);
//		cell.addElement(firmagraciela);
		table.addCell(cell);

		document.add(table);
	}

	private Element addDatosLotes(Lote lote) throws DocumentException {
		String fecha = DateUtils.toString(lote.getFecha(), DateUtils.PATTERN_SOLO_FECHA);
		String material = lote.getFormula().getMaterial().getNombre();
		String formula = lote.getFormula().getNombre();
		String norma = lote.getFormula().getNorma();
		String loteNro = lote.getNroLote();

		PdfPTable table = new PdfPTable(new float[] { 20, 40, 20, 20 });
		table.setWidthPercentage(100);
		PdfPCell cell = getPDFPCell();

		cell.setPhrase(new Phrase("FECHA", fontHeaderTable));
		table.addCell(cell);
		cell.setPhrase(new Phrase(fecha, fontText));
		table.addCell(cell);
		cell.setPhrase(new Phrase("MATERIAL", fontHeaderTable));
		table.addCell(cell);
		cell.setPhrase(new Phrase(material, fontText));
		table.addCell(cell);
		cell.setPhrase(new Phrase("OBJETIVO", fontHeaderTable));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Aprobación de Lote", fontText));
		table.addCell(cell);
		cell.setPhrase(new Phrase("GRADO", fontHeaderTable));
		table.addCell(cell);
		cell.setPhrase(new Phrase(formula, fontText));
		table.addCell(cell);

		cell.setPhrase(new Phrase("REF. NORMA", fontHeaderTable));
		cell.setBorder(0);
		table.addCell(cell);
		cell.setPhrase(new Phrase(norma, fontText));
		cell.setBorder(0);
		table.addCell(cell);
		cell.setPhrase(new Phrase("LOTE Nro.", fontHeaderTable));
		cell.setBorder(0);
		table.addCell(cell);
		cell.setPhrase(new Phrase(loteNro, fontText));
		cell.setBorder(0);
		table.addCell(cell);

		return table;

	}

	private Element addEnsayo(Ensayo ensayo, ReporteLoteConfiguracionCliente config) throws DocumentException {

		Paragraph p = new Paragraph();

		boolean mostrarParametros = true;
		boolean mostrarResultados = true;
		boolean mostrarCondiciones = true;
		boolean mostraObervacionesParametros = true;

		if (config != null) {
			mostrarParametros = config.isMostrarParametros();
			mostrarResultados = config.isMostrarResultados();
			mostrarCondiciones = config.isMostrarCondiciones();
			mostraObervacionesParametros = config.isMostrarObservacionesParametro();
		}

		// Principal: si no muestra paramtros el resto no importa
		if (mostrarParametros) {

			// Armo la tabla de resultados
			// Contiene prueba, min, max, resultado y norma
			float[] colsConResultado = new float[] { 40, 15, 15, 15, 15 };
			float[] colsSinResultado = new float[] { 40, 20, 20, 20 };

			float[] cols = mostrarResultados ? colsConResultado : colsSinResultado;

			PdfPTable tableResultados = new PdfPTable(cols);
			tableResultados.setWidthPercentage(100);
			tableResultados.setSpacingAfter(20);
			PdfPCell cell = getPDFPCell();

			// Cabecera gris "Ensayo"
			PdfPCell header = new PdfPCell();
			header.setColspan(5);
			header.setHorizontalAlignment(Element.ALIGN_CENTER);
			header.setPhrase(new Phrase("Ensayo", fontHeaderTable));
			header.setBackgroundColor(COLOR_GRIS_BORDES);
			header.setBorder(Rectangle.NO_BORDER);
			header.setPadding(20);
			header.setPaddingTop(4);
			header.setPaddingBottom(6);
			tableResultados.addCell(header);

			// Cabecera de min, max, resultado y norma
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setPhrase(new Phrase("", fontHeaderTable));
			tableResultados.addCell(cell);
			cell.setPhrase(new Phrase("Min", fontHeaderTable));
			tableResultados.addCell(cell);
			cell.setPhrase(new Phrase("Max", fontHeaderTable));
			tableResultados.addCell(cell);
			if (mostrarResultados) { // Si muestro resultados agrego el header de la columna
				cell.setPhrase(new Phrase("Resultado", fontHeaderTable));
				tableResultados.addCell(cell);
			}
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPhrase(new Phrase("Norma", fontHeaderTable));
			tableResultados.addCell(cell);

			int ultimo = ensayo.getResultados().size();

			// Recorro cada uno de los ensayos
			// por cada ensayo necesito: minimo, maximo, nombre y resultado

			int pos = 1;
			for (EnsayoResultado resultado : ensayo.getResultados()) {
				Double minimo = resultado.getConfiguracionPruebaParametro().getMinimo();
				Double maximo = resultado.getConfiguracionPruebaParametro().getMaximo();
				String nombre = resultado.getConfiguracionPruebaParametro().getMaquinaPrueba().getNombre();
				String norma = resultado.getConfiguracionPruebaParametro().getNorma();
				agregarPrueba(tableResultados, cell, nombre, minimo, maximo, resultado.getRedondeo(), norma,
						pos == ultimo, mostrarResultados);
				pos++;
			}

			p.add(tableResultados);

			// Condiciones
			if (mostrarCondiciones && !ensayo.getConfiguracionPrueba().getCondiciones().isEmpty()) {
				PdfPTable tableCondiciones = new PdfPTable(new float[] { 35, 15, 35, 15 });
				tableCondiciones.setWidthPercentage(100);
				tableCondiciones.setSpacingAfter(20);

				header.setPhrase(new Phrase("Condiciones", fontHeaderTable));
				tableCondiciones.addCell(header);

				for (ConfiguracionPruebaCondicion condicion : ensayo.getConfiguracionPrueba().getCondiciones()) {
					cell.setBorder(0);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setPhrase(new Phrase(condicion.getNombre(), fontHeaderTable));
					tableCondiciones.addCell(cell);
					cell.setPhrase(new Phrase(condicion.getValor().toString(), fontHeaderTable));
					tableCondiciones.addCell(cell);
				}

				p.add(new Paragraph(new Phrase("")));
				p.add(tableCondiciones);

			}

			if (mostraObervacionesParametros) {
				String observacionesParam = ensayo.getConfiguracionPrueba().getObservacionesReporte();
				PdfPTable tableObservaciones = new PdfPTable(new float[] { 100 });
				tableObservaciones.setWidthPercentage(100);
				tableObservaciones.setSpacingAfter(20);
				header.setPhrase(new Phrase("Observaciones", fontHeaderTable));
				tableObservaciones.addCell(header);
				cell.setBorder(0);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPhrase(new Phrase(observacionesParam, fontText));
				tableObservaciones.addCell(cell);

				p.add(tableObservaciones);
			}
		}
		return p;

	}

	private void agregarPrueba(PdfPTable table, PdfPCell cell, String prueba, Double min, Double max, Double valor,
			String norma, boolean ultimaFila, boolean mostrarResultados) {
		if (ultimaFila)
			cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setPhrase(new Phrase(prueba, fontText));
		table.addCell(cell);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setPhrase(new Phrase(String.format( "%.2f",min), fontText));
		table.addCell(cell);
		cell.setPhrase(new Phrase(String.format( "%.2f",max), fontText));
		table.addCell(cell);
		if (mostrarResultados) {
			cell.setPhrase(new Phrase(String.format( "%.2f",valor), fontText));
			table.addCell(cell);
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPhrase(new Phrase(norma, fontText));
		table.addCell(cell);
	}

	private PdfPTable generateTable(int columns) {
		PdfPTable table = new PdfPTable(columns);
		table.setWidthPercentage(100);
		return table;
	}

	private PdfPCell getPDFPCell() {
		PdfPCell cell = new PdfPCell();
		cell.setUseAscender(true);
		cell.setBorder(0);
		cell.setMinimumHeight(25);
		cell.setBorderWidthBottom(1);
		cell.setBorderColorBottom(COLOR_GRIS_BORDES);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPadding(10);
		return cell;
	}

	private void generarSeccion(Document document, Element data, String titulo, boolean blue, boolean centered)
			throws DocumentException {

		PdfPCell cellBorder = generarCeldaBordeRedondeado(titulo, blue, centered);

		cellBorder.addElement(data);

		PdfPTable tableBorder = new PdfPTable(1);
		tableBorder.setWidthPercentage(100);
		tableBorder.addCell(cellBorder);
		tableBorder.setSpacingAfter(10);

		document.add(tableBorder);

	}

	private PdfPCell generarCeldaBordeRedondeado(String titulo, boolean blue, boolean centered) {
		PdfPCell cellBorder = new PdfPCell();
		if (!StringUtils.isAllBlank(titulo))
			if (blue)
				cellBorder.addElement(addHeaderBlue(titulo, 0));
			else
				cellBorder.addElement(addHeader(titulo));
		if (centered)
			cellBorder.setHorizontalAlignment(Element.ALIGN_CENTER);

		cellBorder.setCellEvent(new RoundRectangle());
		cellBorder.setBorder(Rectangle.NO_BORDER);
		cellBorder.setPadding(20);
		cellBorder.setPaddingTop(15);
		cellBorder.setPaddingBottom(4);
		return cellBorder;
	}

	private Element addHeader(String titulo) {
		return addHeader(titulo, 0);
	}

	private Element addHeader(String titulo, float paddingleftright) {

		Font fontHeaderSection = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, new BaseColor(44, 45, 114));

		Paragraph p = new Paragraph();
		PdfPTable tableHeader = new PdfPTable(1);
		tableHeader.setWidthPercentage(100);
		PdfPCell cellHeader = new PdfPCell();

		cellHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellHeader.setPhrase(new Phrase(titulo.toUpperCase(), fontHeaderSection));
		cellHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellHeader.setBorder(0);
		cellHeader.setBackgroundColor(COLOR_GRIS_BORDES);
		cellHeader.setBorderColorBottom(new BaseColor(228, 228, 228));
		cellHeader.setBorderWidthBottom(1);
		cellHeader.setPadding(8);
//		cellHeader.setPaddingBottom(8);

		tableHeader.addCell(cellHeader);
		p.setIndentationLeft(paddingleftright);
		p.setIndentationRight(paddingleftright);
		p.add(tableHeader);
//		p.setSpacingAfter(4);
		return p;
	}

	private Element addHeaderBlue(String titulo, float paddingleftright) {

		Font fontHeaderSection = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);

		Paragraph p = new Paragraph();
		PdfPTable tableHeader = new PdfPTable(1);
		tableHeader.setWidthPercentage(100);
		PdfPCell cellHeader = new PdfPCell();

		cellHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellHeader.setPhrase(new Phrase(titulo.toUpperCase(), fontHeaderSection));
		cellHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellHeader.setBorder(0);
		cellHeader.setBackgroundColor(new BaseColor(27, 26, 57));
//		cellHeader.setBorderColorBottom(new BaseColor(228, 228, 228));
		cellHeader.setBorderWidthBottom(1);
		cellHeader.setPadding(8);
		cellHeader.setPaddingBottom(8);

		tableHeader.addCell(cellHeader);
		p.setIndentationLeft(paddingleftright);
		p.setIndentationRight(paddingleftright);
		p.add(tableHeader);
		p.setSpacingAfter(10);
		return p;
	}

	public class RoundRectangle implements PdfPCellEvent {
		public void cellLayout(PdfPCell cell, Rectangle rect, PdfContentByte[] canvas) {
			PdfContentByte cb = canvas[PdfPTable.BASECANVAS];
			cb.roundRectangle(rect.getLeft() + 1.5f, rect.getBottom() + 1.5f, rect.getWidth() - 3, rect.getHeight() - 3,
					4);
			cb.setColorFill(BaseColor.WHITE);
			cb.fill();

			PdfContentByte cb2 = canvas[PdfPTable.LINECANVAS];
			cb2.roundRectangle(rect.getLeft() + 1.5f, rect.getBottom() + 1.5f, rect.getWidth() - 3,
					rect.getHeight() - 3, 4);
			cb2.setColorStroke(COLOR_GRIS_BORDES);
			cb2.stroke();
		}
	}

}
