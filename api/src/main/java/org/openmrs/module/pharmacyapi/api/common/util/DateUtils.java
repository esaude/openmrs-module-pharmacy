/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.pharmacyapi.api.common.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	private DateUtils() {
	}
	
	/**
	 * Retorna o valor do horário minimo para a data de referencia passada. <BR>
	 * <BR>
	 * Por exemplo se a data for "30/01/2009 as 17h:33m:12s e 299ms" a data retornada por este
	 * metodo será "30/01/2009 as 00h:00m:00s e 000ms".
	 * 
	 * @param date de referencia.
	 * @return {@link Date} que representa o horário minimo para dia informado.
	 */
	public static Date lowDateTime(Date date) {
		Calendar aux = Calendar.getInstance();
		aux.setTime(date);
		toOnlyDate(aux); // zera os parametros de hour,min,sec,milisec
		return aux.getTime();
	}
	
	/**
	 * Retorna o valor do horário maximo para a data de referencia passada. <BR>
	 * <BR>
	 * Por exemplo se a data for "30/01/2009 as 17h:33m:12s e 299ms" a data retornada por este
	 * metodo será "30/01/2009 as 23h:59m:59s e 999ms".
	 * 
	 * @param date de referencia.
	 * @return {@link Date} que representa o horário maximo para dia informado.
	 */
	public static Date highDateTime(Date date) {
		Calendar aux = Calendar.getInstance();
		aux.setTime(date);
		toOnlyDate(aux); // zera os parametros de hour,min,sec,milisec
		aux.roll(Calendar.DATE, true); // vai para o dia seguinte
		aux.roll(Calendar.MILLISECOND, false); // reduz 1 milisegundo
		return aux.getTime();
	}
	
	/**
	 * Zera todas as referencias de hora, minuto, segundo e milesegundo do {@link Calendar}.
	 * 
	 * @param date a ser modificado.
	 */
	public static void toOnlyDate(Calendar date) {
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
	}
}
