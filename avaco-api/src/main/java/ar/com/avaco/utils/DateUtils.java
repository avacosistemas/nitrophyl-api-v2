package ar.com.avaco.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public final static String PATTERN_dd_MM_yyyy = "dd/MM/yyyy";
	public final static String PATTERN_yyyyMMdd = "yyyyMMdd";
	public final static String PATTERN_dd_MM_yyyy_HH_mm = "dd/MM/yy HH:mm";
	public final static String PATTERN_dd_MM_yyyy_HH_mm_ss = "dd/MM/yyyy HH:mm:ss";

	public static Date toDate(LocalDate date) {
		return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDate toLocalDate(String fecha, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return LocalDate.parse(fecha, formatter);
	}

	public static String toString(LocalDate ld, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return ld.format(formatter);
	}

	public static Date toDate(String dia, String pattern) {
		SimpleDateFormat dateFormat = (SimpleDateFormat) DateFormat.getInstance();
		dateFormat.applyPattern(pattern);
		try {
			return dateFormat.parse(dia);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date toDate(String dia) throws ParseException {
		return toDate(dia, PATTERN_yyyyMMdd);
	}

	/**
	 * Convierte fecha a string con pattern dd/MM/yyyy
	 * 
	 * @param fecha
	 * @return
	 */
	public static String toStringFecha(Date fecha) {
		return toString(fecha, PATTERN_dd_MM_yyyy);
	}

	/**
	 * Convierte fecha a string con pattern dd/MM/yy HH:mm
	 * 
	 * @param fecha
	 * @return
	 */
	public static String toStringFechaHora(Date fecha) {
		return toString(fecha, PATTERN_dd_MM_yyyy_HH_mm);
	}

	public static String toStringFechaHoraSinSegundos(Date fecha) {
		return toString(fecha, PATTERN_dd_MM_yyyy_HH_mm);
	}

	public static String toString(Date fecha, String pattern) {
		SimpleDateFormat dateFormat = (SimpleDateFormat) DateFormat.getInstance();
		dateFormat.applyPattern(pattern);
		return dateFormat.format(fecha);
	}

	public static String toString(LocalDate fecha) {
		SimpleDateFormat dateFormat = (SimpleDateFormat) DateFormat.getInstance();
		dateFormat.applyPattern(PATTERN_yyyyMMdd);
		return dateFormat.format(fecha);
	}

	public static Date getFechaYHoraActual() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

}
