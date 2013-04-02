package fr.montagnesdor.restaurant.applets;

import java.applet.Applet;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DateTimeApplet extends Applet
{
//	private boolean debug = false;
	private static String localeLanguage = "fr";
	private static Locale locale = new Locale(localeLanguage);
	private static SimpleDateFormat formatDate = null;
	private static SimpleDateFormat formatDateShort = null;
	private static SimpleDateFormat formatTime = null;
	private static SimpleDateFormat formatDateTime = null;
	private static String startJavascriptFunction = null;
	
	
	public void init()
	{
		//if(formatDate==null)
		{ 
			//simpleDateFormat = new SimpleDateFormat("EEEE dd MMMM yyyy � HH:mm:ss");
			String patternDate ="EEEE dd MMMM yyyy";
			String patternDateShort = "dd/MM/yyyy";
			String patternTime = "HH:mm:ss";
			String patternDateTime = "EEEE dd MMMM yyyy � HH:mm:ss";
			try
			{
				if(getParameter("localeLanguage")!=null)
				{
					localeLanguage = getParameter("localeLanguage");
					locale = new Locale(localeLanguage);
				}				
				if(getParameter("patternDate")!=null)
					patternDate = getParameter("patternDate");
				if(getParameter("patternDateShort")!=null)	
					patternDateShort = getParameter("patternDateShort");
				if(getParameter("patternTime")!=null)	
					patternTime = getParameter("patternTime");
				if(getParameter("patternDateTime")!=null)	
					patternDateTime = getParameter("patternDateTime");
				
//				debug = (debugStr!=null && debugStr.equals("true"));
				formatDate = new SimpleDateFormat(patternDate, locale);
				formatDateShort = new SimpleDateFormat(patternDateShort, locale);
				formatTime = new SimpleDateFormat(patternTime, locale);
				formatDateTime = new SimpleDateFormat(patternDateTime, locale);
				
				String debugStr = getParameter("debug");
				startJavascriptFunction = getParameter("startJavascriptFunction");
			}
			catch (Exception e)
			{
//				debug = true;
				System.out.println("Une exception s'est produite lors de l'appel du constructeur SimpleDateFormat : ");
				e.printStackTrace();				
			}
/*			
			if(debug)
			{
				System.out.println("Initialisation de l'applet DateTimeApplet : ");
				System.out.println("patternDate : "+patternDate);
				System.out.println("patternDateCourte : "+patternDateCourte);
				System.out.println("patternTime : "+patternTime);
				System.out.println("patternDateTime : "+patternDateTime);
			}
*/			
		}
	}
	
	public String getDateShort()
	{
		String dateStr = formatDateShort.format(new Date());
/*		
		if(debug)
			System.out.println("Valeur de retour de la m�thode getDateCourte() "+dateStr);
*/			
		return dateStr;
	}
		
	public String getDate()
	{
		String dateStr = formatDate.format(new Date());
		dateStr = dateStr.substring(0,1).toUpperCase()+dateStr.substring(1,dateStr.length());
/*		
		if(debug)
			System.out.println("Valeur de retour de la m�thode getDate() "+dateStr);
*/			
		return dateStr;
	}

	public String getDate(String newDate)
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(newDate.substring(6, 10)), Integer.parseInt(newDate.substring(3, 5))-1, Integer.parseInt(newDate.substring(0, 2)));
		String dateStr = formatDate.format(cal.getTime());
		dateStr = dateStr.substring(0,1).toUpperCase()+dateStr.substring(1,dateStr.length());
/*		
		if(debug)
			System.out.println("Argument newDate de la m�thode getDate(newDate) "+newDate+"\nValeur de retour de la m�thode getDate(newDate) "+dateStr);
*/			
		return dateStr;
	}

	public String getDateTime()
	{
		String dateTimeStr = formatDateTime.format(new Date());
		dateTimeStr = dateTimeStr.substring(0,1).toUpperCase()+dateTimeStr.substring(1,dateTimeStr.length());
/*		
		if(debug)
			System.out.println("Valeur de retour de la m�thode getDateTime() "+dateTimeStr);
*/		
		return dateTimeStr;
	}
	
	public String getTime()
	{
		String timeStr = formatTime.format(new Date());
/*		
		if(debug)
			System.out.println("Valeur de retour de la m�thode getTime() "+timeStr);
*/			
		return timeStr;
	}
	/* (non-Javadoc)
	 * @see java.applet.Applet#start()
	 */
	public void start()
	{
		try
		{
			getAppletContext().showDocument(new URL("javascript:"+startJavascriptFunction+"()"));			
		}
	  	catch (MalformedURLException me) 
	  	{ 
			System.out.println("Il faut préciser une fonction javascript pour le paramètre startJavascriptFunction");
	  	}
	}

}
