/*
 * Created on 23 avr. 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package fr.montagnesdor.util.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author hans
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DateTime
{
	public static void main(String[] args)
	{
		String patternDate = "EEEE dd MMMM yyyy";
		String patternDateShort = "dd/MM/yyyy";
		String patternTime = "HH:mm:ss";
		String patternDateTime = "EEEE dd MMMM yyyy à HH:mm:ss";
		SimpleDateFormat formatDate = null;
		SimpleDateFormat formatDateShort = null;
		SimpleDateFormat formatTime = null;
		SimpleDateFormat formatDateTime = null;

		String localeLanguage = "en";
		Locale locale = new Locale(localeLanguage);

		try
		{
			formatDate = new SimpleDateFormat(patternDate, locale);
			formatDateShort = new SimpleDateFormat(patternDateShort, locale);
			formatTime = new SimpleDateFormat(patternTime, locale);
			formatDateTime = new SimpleDateFormat(patternDateTime, locale);

		}
		catch (Exception e)
		{
		}
		
		String dateStr = formatDate.format(new Date());
		dateStr = dateStr.substring(0,1).toUpperCase()+dateStr.substring(1,dateStr.length());
		
		System.out.println(dateStr);
	}

}
