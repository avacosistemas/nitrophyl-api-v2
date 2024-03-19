package ar.com.avaco.ws.rest.informe;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Calendar;

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

import ar.com.avaco.nitrophyl.domain.entities.lote.Ensayo;
import ar.com.avaco.nitrophyl.domain.entities.lote.EnsayoResultado;
import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.dto.JSONResponse;

public class InformeBuilder {

	private static final BaseColor COLOR_GRIS_BORDES = new BaseColor(238, 238, 238);

	private final static Font fontHeaderTable = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.BLACK);
	private final static Font fontText = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);

	public ResponseEntity<JSONResponse> generarReporte(Lote lote) throws DocumentException, IOException, URISyntaxException {

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
				Element addEnsayo = addEnsayo(ensayo);
//				generarSeccion(document, p, "Reómetro", true, true);
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
		cell.setPhrase(new Phrase("INFORME DE CALIDAD", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK)));
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

	private Element addEnsayo(Ensayo ensayo) throws DocumentException {
		
		PdfPTable tableResultados = new PdfPTable(new float[] { 40, 15, 15, 15, 15 });
		tableResultados.setWidthPercentage(100);
		tableResultados.setSpacingAfter(20);
		PdfPCell cell = getPDFPCell();

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

		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setPhrase(new Phrase("", fontHeaderTable));
		tableResultados.addCell(cell);
		cell.setPhrase(new Phrase("Min", fontHeaderTable));
		tableResultados.addCell(cell);
		cell.setPhrase(new Phrase("Max", fontHeaderTable));
		tableResultados.addCell(cell);
		cell.setPhrase(new Phrase("Resultado", fontHeaderTable));
		tableResultados.addCell(cell);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPhrase(new Phrase("Norma", fontHeaderTable));
		tableResultados.addCell(cell);

		int ultimo = ensayo.getResultados().size();
		
		int pos = 1;
		for (EnsayoResultado resultado : ensayo.getResultados()) {
			Double minimo = resultado.getMinimo();
			Double maximo = resultado.getMaximo();
			String nombre = resultado.getNombre();
//			agregarPrueba(tableResultados, cell, nombre, minimo, maximo, resultado.getRedondeo(), resultado.getNorma(), pos == ultimo);
			pos++;
		}
		
//		agregarPrueba(tableResultados, cell, "tmax (kg)", "26.00", "31.00", "30.69", "NOR2", false);
//		agregarPrueba(tableResultados, cell, "Tg δ", "0.04", "", "0.03", "NOR3", true);
		
		PdfPTable tableCondiciones = new PdfPTable(new float[] { 35, 15, 35, 15 });
		tableCondiciones.setWidthPercentage(100);
		tableCondiciones.setSpacingAfter(20);
		
		header.setPhrase(new Phrase("Condiciones", fontHeaderTable));
		tableCondiciones.addCell(header);

		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setPhrase(new Phrase("Temperatura (ºC)", fontHeaderTable));
		tableCondiciones.addCell(cell);
		cell.setPhrase(new Phrase("90.00", fontHeaderTable));
		tableCondiciones.addCell(cell);
		cell.setPhrase(new Phrase("Presion (atm)", fontHeaderTable));
		tableCondiciones.addCell(cell);
		cell.setPhrase(new Phrase("1.00", fontHeaderTable));
		tableCondiciones.addCell(cell);
		
		PdfPTable tableObservaciones = new PdfPTable(new float[] { 100 });
		tableObservaciones.setWidthPercentage(100);
		tableObservaciones.setSpacingAfter(20);
		header.setPhrase(new Phrase("Observaciones", fontHeaderTable));
		tableObservaciones.addCell(header);
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setPhrase(new Phrase("Observaciones de remoetría de Observaciones de remoetría Observaciones de de remoetría Observaciones de de remoetría de de de Observaciones de remoetría Observaciones de remoetría Observaciones de remoetría ", fontText));
		tableObservaciones.addCell(cell);
		
		
		Paragraph p = new Paragraph();
		p.add(tableResultados);
		p.add(new Paragraph(new Phrase("")));
		p.add(tableCondiciones);
		p.add(tableObservaciones);
		
		return p;
		
	}
	
//		tableResultados = new PdfPTable(new float[] { 40, 20, 20, 20 });
//		tableResultados.setWidthPercentage(100);
//		tableResultados.setSpacingAfter(10);
//		cell = getPDFPCell();
//
//		header = new PdfPCell();
//		header.setColspan(4);
//		header.setHorizontalAlignment(Element.ALIGN_CENTER);
//		header.setPhrase(new Phrase("Ensayo", fontHeaderTable));
//		header.setBackgroundColor(COLOR_GRIS_BORDES);
//		header.setBorder(Rectangle.NO_BORDER);
//		header.setPadding(20);
//		header.setPaddingTop(4);
//		header.setPaddingBottom(6);
//		tableResultados.addCell(header);
//
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setPhrase(new Phrase("", fontHeaderTable));
//		tableResultados.addCell(cell);
//		cell.setPhrase(new Phrase("Min", fontHeaderTable));
//		tableResultados.addCell(cell);
//		cell.setPhrase(new Phrase("Max", fontHeaderTable));
//		tableResultados.addCell(cell);
//		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//		cell.setPhrase(new Phrase("Norma", fontHeaderTable));
//		tableResultados.addCell(cell);
//
//		agregarPrueba(tableResultados, cell, "Resistencia a la tracción (kg/cm2)", "140.00", "", "ATR123", false);
//		agregarPrueba(tableResultados, cell, "Módulo al 100% (kg/cm2)", "25.00", "", "ATR456", false);
//		agregarPrueba(tableResultados, cell, "Elongación (%)", "125.00", "", "", "JKQ87", true);
//		
//		tableObservaciones = new PdfPTable(new float[] { 35, 15, 35, 15 });
//		tableObservaciones.setWidthPercentage(100);
//		
//		header.setPhrase(new Phrase("Condiciones", fontHeaderTable));
//		tableObservaciones.addCell(header);
//
//		cell.setBorder(0);
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setPhrase(new Phrase("Temperatura (ºC)", fontHeaderTable));
//		tableObservaciones.addCell(cell);
//		cell.setPhrase(new Phrase("80.00", fontHeaderTable));
//		tableObservaciones.addCell(cell);
//		cell.setPhrase(new Phrase("Presion (atm)", fontHeaderTable));
//		tableObservaciones.addCell(cell);
//		cell.setPhrase(new Phrase("1.10", fontHeaderTable));
//		tableObservaciones.addCell(cell);
//		
//		tableObservaciones = new PdfPTable(new float[] { 100 });
//		tableObservaciones.setWidthPercentage(100);
//		tableObservaciones.setSpacingAfter(20);
//		header.setPhrase(new Phrase("Observaciones", fontHeaderTable));
//		tableObservaciones.addCell(header);
//		cell.setBorder(0);
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setPhrase(new Phrase("Observaciones de dinamometro del dina momemtro obervaciones Observaciones de dinamometro del dina momemtro obervaciones Observaciones de dinamometro del dina momemtro obervaciones Observaciones de dinamometro del dina momemtro obervaciones ", fontText));
//		tableObservaciones.addCell(cell);
//
//		
//		p = new Paragraph();
//		p.add(tableResultados);
//		p.add(tableObservaciones);
//		
//		generarSeccion(document, p, "Dinamómetro", true, true);
//		
//		
//		
//		tableResultados = new PdfPTable(new float[] { 40, 20, 20, 20 });
//		tableResultados.setWidthPercentage(100);
//		tableResultados.setSpacingAfter(10);
//		cell = getPDFPCell();
//
//		header = new PdfPCell();
//		header.setColspan(4);
//		header.setHorizontalAlignment(Element.ALIGN_CENTER);
//		header.setPhrase(new Phrase("Ensayo", fontHeaderTable));
//		header.setBackgroundColor(COLOR_GRIS_BORDES);
//		header.setBorder(Rectangle.NO_BORDER);
//		header.setPadding(20);
//		header.setPaddingTop(4);
//		header.setPaddingBottom(6);
//		tableResultados.addCell(header);
//
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setPhrase(new Phrase("", fontHeaderTable));
//		tableResultados.addCell(cell);
//		cell.setPhrase(new Phrase("Min", fontHeaderTable));
//		tableResultados.addCell(cell);
//		cell.setPhrase(new Phrase("Max", fontHeaderTable));
//		tableResultados.addCell(cell);
//		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//		cell.setPhrase(new Phrase("Norma", fontHeaderTable));
//		tableResultados.addCell(cell);
//
//		agregarPrueba(tableResultados, cell, "Dureza (Shore A)", "75", "85", "GS784", true);
//		
//		p = new Paragraph();
//		p.add(tableResultados);
//		
//		generarSeccion(document, p, "Durómetro", true, true);
//		
//		tableResultados = new PdfPTable(new float[] { 40, 20, 20, 20 });
//		tableResultados.setWidthPercentage(100);
//		tableResultados.setSpacingAfter(10);
//		cell = getPDFPCell();
//
//		header = new PdfPCell();
//		header.setColspan(4);
//		header.setHorizontalAlignment(Element.ALIGN_CENTER);
//		header.setPhrase(new Phrase("Ensayo", fontHeaderTable));
//		header.setBackgroundColor(COLOR_GRIS_BORDES);
//		header.setBorder(Rectangle.NO_BORDER);
//		header.setPadding(20);
//		header.setPaddingTop(4);
//		header.setPaddingBottom(6);
//		tableResultados.addCell(header);
//
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setPhrase(new Phrase("", fontHeaderTable));
//		tableResultados.addCell(cell);
//		cell.setPhrase(new Phrase("Min", fontHeaderTable));
//		tableResultados.addCell(cell);
//		cell.setPhrase(new Phrase("Max", fontHeaderTable));
//		tableResultados.addCell(cell);
//		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//		cell.setPhrase(new Phrase("Norma", fontHeaderTable));
//		tableResultados.addCell(cell);
//
//		agregarPrueba(tableResultados, cell, "Densidad (g/cm3)", "1.15", "1.25", "AOG419", true);
//		
//		p = new Paragraph();
//		p.add(tableResultados);
//		
//		generarSeccion(document, p, "Densímetro", true, true);

//	}

	private void agregarPrueba(PdfPTable table, PdfPCell cell, String prueba, String min, String max, String valor,
			String norma, boolean ultimaFila) {
		if (ultimaFila)
			cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setPhrase(new Phrase(prueba, fontText));
		table.addCell(cell);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setPhrase(new Phrase(min, fontText));
		table.addCell(cell);
		cell.setPhrase(new Phrase(max, fontText));
		table.addCell(cell);
		cell.setPhrase(new Phrase(valor, fontText));
		table.addCell(cell);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPhrase(new Phrase(norma, fontText));
		table.addCell(cell);
	}

	private void agregarPrueba(PdfPTable table, PdfPCell cell, String prueba, String min, String max, 
			String norma, boolean ultimaFila) {
		if (ultimaFila)
			cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setPhrase(new Phrase(prueba, fontText));
		table.addCell(cell);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setPhrase(new Phrase(min, fontText));
		table.addCell(cell);
		cell.setPhrase(new Phrase(max, fontText));
		table.addCell(cell);
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
//		cell.setPaddingLeft(new Float("7.3"));
//		cell.setPaddingRight(new Float("7.3"));
		return cell;
	}

//	private void addSeccion(Document document, PdfPTable table) throws DocumentException {
//		addSeccion(document, table, null);
//	}

//	private void addSeccion(Document document, PdfPTable table, String titulo) throws DocumentException {
//		generarSeccion(document, table, titulo, false, false);
//	}

	private void generarSeccion(Document document, Element data, String titulo, boolean blue, boolean centered)
			throws DocumentException {
		
		PdfPCell cellBorder = generarCeldaBordeRedondeado(titulo, blue, centered);
		
//		Paragraph paraTable = new Paragraph();
//		paraTable.add(table);
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

	private void addDetalle(Document document) throws DocumentException {
		PdfPTable table = new PdfPTable(new float[] { 20, 80 });
		PdfPCell cell = getPDFPCell();
		cell.setPhrase(new Phrase("Detalle", fontHeaderTable));
		table.addCell(cell);
		cell.setPhrase(new Phrase("PEPE", fontText));
		table.addCell(cell);
//		addSeccion(document, table);
	}

	private void addHeaderCheckGrilla(String id, String titulo, PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(0);
		cell.setFixedHeight(25);
		cell.setBorderColorBottom(new BaseColor(46, 42, 109));
		cell.setPaddingTop(10);
		cell.setBorderWidthBottom(1);
		cell.setPhrase(new Phrase(id, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, new BaseColor(46, 42, 109))));
		table.addCell(cell);
		cell.setPhrase(
				new Phrase(titulo, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, new BaseColor(46, 42, 109))));
		table.addCell(cell);
		cell.setPhrase(new Phrase("", fontText));
		table.addCell(cell);
//		cell.setPhrase(new Phrase("", fontText));
//		table.addCell(cell);
	}

	private void agregarCheckGrilla(String id, String titulo, String estado, String observaciones, PdfPTable table,
			boolean ultimo) {
		PdfPCell cell = new PdfPCell();
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(0);
		cell.setFixedHeight(20);
		if (!ultimo) {
			cell.setBorderColorBottom(COLOR_GRIS_BORDES);
			cell.setBorderWidthBottom(1);
		}
		cell.setPhrase(new Phrase(id, fontHeaderTable));
		table.addCell(cell);
		cell.setPhrase(new Phrase(titulo, fontHeaderTable));
		table.addCell(cell);
		cell.setPhrase(new Phrase(estado, fontText));
		table.addCell(cell);
//		cell.setPhrase(new Phrase(observaciones, fontText));
//		table.addCell(cell);
	}

	private void addLogo(Document document, String informePath)
			throws BadElementException, MalformedURLException, IOException, DocumentException {
		Image logo = Image.getInstance(informePath + "\\logopremec.png");
		logo.scaleToFit(300, 150);
		document.add(logo);
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
