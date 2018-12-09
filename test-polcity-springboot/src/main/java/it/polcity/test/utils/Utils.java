package it.polcity.test.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

import it.polcity.test.exception.TestPolcityException;
import it.polcity.test.model.Persona;
import it.polcity.test.model.Persona.Sesso;

/**
 * @author marco.marini
 *	Classe per metodi di utilità utilizzati nel progetto
 */
public class Utils {

	/**
	 * 	Estrae l'idProtocollo dalla stringa completa del protocollo (es: 5/2018)
	 * @param protocollo
	 * 	la stringa completa che identifica il protocollo
	 * @param raise
	 * 	se true, lancia eccezione se l'id estratto è vuoto
	 * @return
	 * @throws TestPolcityException
	 */
	public static Integer getIdFromProtocollo(String protocollo, boolean raise) throws TestPolcityException {

		if (StringUtils.isEmpty(protocollo) || !protocollo.contains(Constants.PROTOCOLLO_SEPARATOR)) {
			if (raise) {
				throw new TestPolcityException(String.format(
						"Il campo protocollo impostato '%s' non è valorizzato oppure non è in un formato corretto",
						protocollo));
			}
			return null;
		}

		String sIdProtocollo = protocollo.substring(0, protocollo.indexOf(Constants.PROTOCOLLO_SEPARATOR));
		Integer idProtocollo = null;
		try {
			idProtocollo = Integer.parseInt(sIdProtocollo);
		} catch (NumberFormatException nfe) {
			throw new TestPolcityException(
					String.format("Il campo impostato '%s' non è un valore numerico corretto", sIdProtocollo),
					nfe.getCause());
		}

		if (idProtocollo == null || idProtocollo < 0) {
			throw new TestPolcityException(String.format(
					"Il campo protocollo impostato '%s' non è valorizzato oppure non è in un formato corretto",
					protocollo));
		}

		return idProtocollo;
	}

	/**
	 * Estrae l'idProtocollo dalla stringa completa del protocollo (es: 5/2018)
	 * @param protocollo
	 * @return
	 * @throws TestPolcityException
	 */
	public static Integer getIdFromProtocollo(String protocollo) throws TestPolcityException {
		return getIdFromProtocollo(protocollo, true);
	}

	/**
	 * Estrae l'annoProtocollo dalla stringa completa del protocollo (es:5/2018)
	 * @param protocollo
	 * 	la stringa completa che identifica il protocollo
	 * @param raise
	 * 	se true, lancia eccezione se l'anno estratto è vuoto
	 * @return
	 * @throws TestPolcityException
	 */
	public static Integer getAnnoFromProtocollo(String protocollo, boolean raise) throws TestPolcityException {
		if (StringUtils.isEmpty(protocollo) || !protocollo.contains(Constants.PROTOCOLLO_SEPARATOR)) {
			if (raise) {
				throw new TestPolcityException(String.format(
						"Il campo protocollo impostato '%s' non è valorizzato oppure non è in un formato corretto",
						protocollo));
			}
			return null;
		}

		String sAnnoProtocollo = protocollo.substring(protocollo.indexOf(Constants.PROTOCOLLO_SEPARATOR) + 1,
				protocollo.length());
		Integer annoProtocollo = null;
		try {
			annoProtocollo = Integer.parseInt(sAnnoProtocollo);
		} catch (NumberFormatException nfe) {
			throw new TestPolcityException(
					String.format("Il campo impostato '%s' non è un valore numerico corretto", sAnnoProtocollo),
					nfe.getCause());
		}

		if (annoProtocollo == null || annoProtocollo < 0) {
			throw new TestPolcityException(String.format(
					"Il campo protocollo impostato '%s' non è valorizzato oppure non è in un formato corretto",
					protocollo));
		}

		return annoProtocollo;
	}

	/**
	 * Estrae l'annoProtocollo dalla stringa completa del protocollo (es:5/2018)
	 * @param protocollo
	 * @return
	 * @throws TestPolcityException
	 */
	public static Integer getAnnoFromProtocollo(String protocollo) throws TestPolcityException {
		return getAnnoFromProtocollo(protocollo, true);
	}

	/**
	 * 	Converte la stringa in formato {@link Constants.DATE_FORMAT} in Date
	 * @param sDate
	 * @param raise
	 * 	se true, lancia eccezione se il campo è vuoto
	 * @return
	 * @throws TestPolcityException
	 */
	public static Date getDate(String sDate, boolean raise) throws TestPolcityException {

		Date date = null;

		if (StringUtils.isEmpty(sDate)) {
			if (raise) {
				throw new TestPolcityException(String.format(
						"Il campo data impostato '%s' non è valorizzato oppure non è nel formato corretto '%s'", sDate,
						Constants.DATE_FORMAT));
			}

			return null;
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
		try {
			date = simpleDateFormat.parse(sDate);
		} catch (ParseException pe) {
			throw new TestPolcityException(String.format(
					"Il campo data impostato '%s' non è valorizzato oppure non è nel formato corretto '%s'", sDate,
					Constants.DATE_FORMAT), pe);
		}

		return date;

	}

	/**
	 * Converte la stringa in formato {@link Constants.DATE_FORMAT} in Date
	 * @param sDate
	 * @return
	 * @throws TestPolcityException
	 */
	public static Date getDate(String sDate) throws TestPolcityException {
		return getDate(sDate, true);
	}

	/**
	 * Formatta la data in formato {@link Constants.DATE_FORMAT}
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {

		if (date == null) {
			return null;
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
		return simpleDateFormat.format(date);
	}

	/**
	 * Ritorna il valore dell'enumerazione {@link Persona.Sesso} data la stringa in ingresso
	 * @param sesso
	 * @param raise
	 * @return
	 * @throws TestPolcityException
	 */
	public static Sesso getSesso(String sesso, boolean raise) throws TestPolcityException {

		Sesso enumSesso = Persona.Sesso.getSesso(sesso);

		if (enumSesso == null) {
			throw new TestPolcityException(String.format(
					"Il campo sesso impostato '%s' non è valorizzato oppure non è in un formato corretto", sesso));
		}

		return enumSesso;

	}

	public static Sesso getSesso(String sesso) throws TestPolcityException {
		return getSesso(sesso, true);
	}

}
