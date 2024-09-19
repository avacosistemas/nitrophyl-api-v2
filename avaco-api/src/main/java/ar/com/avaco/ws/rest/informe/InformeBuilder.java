package ar.com.avaco.ws.rest.informe;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

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
import ar.com.avaco.nitrophyl.ws.dto.ArchivoDTO;
import ar.com.avaco.utils.DateUtils;

public class InformeBuilder {

	private static final BaseColor COLOR_GRIS_BORDES = new BaseColor(238, 238, 238);

	private final static Font fontHeaderTable = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.BLACK);
	private final static Font fontText = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);

	public ArchivoDTO generarReporte(Lote lote, List<ReporteLoteConfiguracionCliente> configuracion, String cliente,
			String empresa) throws DocumentException, IOException, URISyntaxException {

		Document document = new Document(PageSize.A4);
		ArchivoDTO adto = new ArchivoDTO();
		try {

			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			PdfWriter.getInstance(document, new FileOutputStream(
					"c:\\nitrophyl\\nitrophyl-" + Calendar.getInstance().getTimeInMillis() + ".pdf"));

			PdfWriter.getInstance(document, baos);

//			writer.setPageEvent(new PDFEventHelper());

			document.open();
			document.setMargins(20, 20, 10, 10);

			Element encabezado = generarEncabezado(empresa);
			document.add(encabezado);

			Element datosLote = addDatosLotes(lote);
			generarSeccion(document, datosLote, null, null);

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
				generarSeccion(document, addEnsayo, null, null);

			}

			String string = "La parametrizaci�n entre los valores por reometr�a y las propiedades f�sicas mencionadas";
			string += " esta realizada en base a un estudio realizdo en nuestro laboratorio sobre una cuerva patr�n";
			string += " normalizada y los ensayos f�sicos directos descriptos por norma y bajo condiciones reguladas";

			PdfPCell cellBorder = new PdfPCell();
			cellBorder.setCellEvent(new RoundRectangle());
			cellBorder.setBorder(Rectangle.NO_BORDER);
			cellBorder.setPadding(10f);
			cellBorder.setPaddingTop(0);
			cellBorder.addElement(new Phrase(string, fontText));

			PdfPTable tableBorder = new PdfPTable(1);
			tableBorder.setWidthPercentage(100);
			tableBorder.addCell(cellBorder);

			document.add(tableBorder);

			generarFirma(document);
			document.close();
			adto.setArchivo(baos.toByteArray());
			adto.setNombre("Informe Calidad - " + cliente.replace(".", " - " + lote.getNroLote()) + ".pdf");

		} catch (DocumentException e) {
			document.close();
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			document.close();
			e.printStackTrace();
			throw e;
		} finally {
			
		}
		return adto;
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
		cell.setPhrase(new Phrase("Aprobaci�n de Lote", fontText));
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

			int rowspanpruebas = ensayo.getResultados().size();
			int rowspancondiciones = ensayo.getConfiguracionPrueba().getCondiciones().isEmpty() ? 0 : 1; // ensayo.getConfiguracionPrueba().getCondiciones().size();

			// Armo la tabla de resultados
			// Contiene prueba, min, max, resultado y norma
			float[] colsConResultado = new float[] { 10, 16, 20, 12, 12, 12, 18 };
			float[] colsSinResultado = new float[] { 10, 16, 18, 18, 18, 20 };

			float[] cols = mostrarResultados ? colsConResultado : colsSinResultado;

			PdfPTable tableResultados = new PdfPTable(cols);
			tableResultados.setWidthPercentage(100);
			tableResultados.setSpacingAfter(20);

			// Cabecera
			PdfPCell cell = getPDFPCell();
			cell.setPhrase(new Phrase("", fontHeaderTable));
			cell.setColspan(2);
			tableResultados.addCell(cell);

			cell = getPDFPCell();
			cell.setPhrase(new Phrase("Nombre", fontHeaderTable));
			tableResultados.addCell(cell);

			cell = getPDFPCell();
			cell.setPhrase(new Phrase("Min", fontHeaderTable));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tableResultados.addCell(cell);

			cell = getPDFPCell();
			cell.setPhrase(new Phrase("Max", fontHeaderTable));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tableResultados.addCell(cell);

			if (mostrarResultados) {
				cell = getPDFPCell();
				cell.setPhrase(new Phrase("Valor", fontHeaderTable));
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				tableResultados.addCell(cell);
			}

			cell = getPDFPCell();
			cell.setPhrase(new Phrase("Norma", fontHeaderTable));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableResultados.addCell(cell);

			String observacionesMaquina = "";
			if (StringUtils.isNotBlank(ensayo.getConfiguracionPrueba().getMaquina().getObservacionesReporte())) {
				observacionesMaquina = ensayo.getConfiguracionPrueba().getMaquina().getObservacionesReporte();
			}
			String observacionesParametrizacion = mostraObervacionesParametros
					? ensayo.getConfiguracionPrueba().getObservacionesReporte()
					: "";
			boolean hayObservaciones = StringUtils.isNotEmpty(observacionesParametrizacion)
					|| StringUtils.isNotEmpty(observacionesMaquina);
			
			boolean hayCondiciones = mostrarCondiciones && !ensayo.getConfiguracionPrueba().getCondiciones().isEmpty();
			
			boolean first = true;

			int cantidadPruebas = ensayo.getResultados().size();
			int posPrueba = 1;
			
			for (EnsayoResultado resultado : ensayo.getResultados()) {

				boolean ultimo = posPrueba == cantidadPruebas;
				posPrueba++;
				
				Double minimo = resultado.getConfiguracionPruebaParametro().getMinimo();
				Double maximo = resultado.getConfiguracionPruebaParametro().getMaximo();
				String nombre = resultado.getConfiguracionPruebaParametro().getMaquinaPrueba().getNombre();
				String norma = resultado.getConfiguracionPruebaParametro().getNorma();

				if (first) {
					cell = getPDFPCell();
					cell.setRowspan(rowspancondiciones + rowspanpruebas + 1);
					cell.setPhrase(new Phrase(
							resultado.getConfiguracionPruebaParametro().getMaquinaPrueba().getMaquina().getNombre().replace(" ", System.lineSeparator()),
							fontHeaderTable));
					cell.setRotation(90);
					cell.setBorder(0);
					cell.setBorderWidthRight(1);
					cell.setBorderColorRight(COLOR_GRIS_BORDES);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					tableResultados.addCell(cell);

					cell = getPDFPCell();
					cell.setRowspan(rowspanpruebas);
					cell.setPhrase(new Phrase("Ensayo", fontHeaderTable));
					cell.setBorder(0);
					cell.setBorderWidthRight(1);
					cell.setBorderWidthBottom(1);
					cell.setBorderColorRight(COLOR_GRIS_BORDES);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					if (!hayObservaciones && !hayCondiciones) {
						cell.setBorderWidthBottom(0);
					}
					tableResultados.addCell(cell);

					first = false;
				}

				cell = getPDFPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPhrase(new Phrase(nombre, fontText));
				if (!hayObservaciones && !hayCondiciones && ultimo) {
					cell.setBorderWidthBottom(0);
				}
				tableResultados.addCell(cell);

				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPhrase(new Phrase(String.format("%.2f", minimo), fontText));
				if (!hayObservaciones && !hayCondiciones && ultimo) {
					cell.setBorderWidthBottom(0);
				}
				tableResultados.addCell(cell);

				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPhrase(new Phrase(String.format("%.2f", maximo), fontText));
				if (!hayObservaciones && !hayCondiciones && ultimo) {
					cell.setBorderWidthBottom(0);
				}
				tableResultados.addCell(cell);

				if (mostrarResultados) {
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setPhrase(new Phrase(String.format("%.2f", resultado.getRedondeo()), fontText));
					if (!hayObservaciones && !hayCondiciones && ultimo) {
						cell.setBorderWidthBottom(0);
					}
					tableResultados.addCell(cell);
				}

				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPhrase(new Phrase(norma, fontText));
				
				if (!hayObservaciones && !hayCondiciones && ultimo) {
					cell.setBorderWidthBottom(0);
				}
				
				tableResultados.addCell(cell);

			}

			
			
			first = true;

			// Condiciones
			
			if (hayCondiciones) {

				cell = getPDFPCell();
				cell.setPhrase(new Phrase("Condiciones", fontHeaderTable));
				cell.setRowspan(rowspancondiciones);
				cell.setBorder(0);
				cell.setBorderWidthRight(1);
				cell.setBorderColorRight(COLOR_GRIS_BORDES);
				cell.setBorderWidthBottom(1);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				first = false;
				if (!hayObservaciones) {
					cell.setBorderWidthBottom(0);
				}
				tableResultados.addCell(cell);

				String condiciones = "";

				first = true;

				for (ConfiguracionPruebaCondicion condicion : ensayo.getConfiguracionPrueba().getCondiciones()) {

					if (!first) {
						condiciones += " - ";
					}
					first = false;

					condiciones += condicion.getNombre() + ": " + String.format("%.2f", condicion.getValor());
					
				}

				cell = getPDFPCell();
				cell.setColspan(cols.length - 1);
				cell.setPhrase(new Phrase(condiciones, fontText));
				
				if (!hayObservaciones) {
					cell.setBorderWidthBottom(0);
				}
				
				tableResultados.addCell(cell);

			}

			p.add(tableResultados);

			if (hayObservaciones) {

				cell = getPDFPCell();
				cell.setPhrase(new Phrase("Observaciones", fontHeaderTable));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorderColorRight(COLOR_GRIS_BORDES);
				cell.setBorderWidthBottom(0);
				cell.setBorderWidthRight(1);
				tableResultados.addCell(cell);

				int colspan = cols.length;
				cell = getPDFPCell();
				String string = observacionesMaquina;
				if (StringUtils.isNotBlank(observacionesParametrizacion)
						&& StringUtils.isNotBlank(observacionesMaquina)) {
					string += System.lineSeparator();
				}
				string += observacionesParametrizacion;
				cell.setPhrase(new Phrase(string, fontText));
				cell.setColspan(colspan - 1);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorderWidthBottom(0);
				tableResultados.addCell(cell);
			}

		}

		return p;

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
		cell.setBorderWidthBottom(1);
		cell.setBorderColorBottom(COLOR_GRIS_BORDES);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPadding(5);
		return cell;
	}

	private void generarSeccion(Document document, Element data, String titulo, Float padding)
			throws DocumentException {

		PdfPCell cellBorder = generarCeldaBordeRedondeado(titulo, padding);
		cellBorder.addElement(data);

		PdfPTable tableBorder = new PdfPTable(1);
		tableBorder.setWidthPercentage(100);
		tableBorder.addCell(cellBorder);
		tableBorder.setSpacingAfter(10);

		document.add(tableBorder);
	}

	private PdfPCell generarCeldaBordeRedondeado(String titulo, Float padding) {
		PdfPCell cellBorder = new PdfPCell();
		cellBorder.setCellEvent(new RoundRectangle());
		cellBorder.setBorder(Rectangle.NO_BORDER);
		if (padding != null) {
			cellBorder.setPadding(padding);
		}
		return cellBorder;
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
