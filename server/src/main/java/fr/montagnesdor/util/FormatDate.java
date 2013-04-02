package fr.montagnesdor.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import fr.montagnesdor.util.log.MyLog;
import fr.montagnesdor.util.log.MyLogFactoryImpl;

/**
 *
 * @author  maxime
 * @version
 */
public class FormatDate
{
    private static MyLog logger = MyLogFactoryImpl.getInstance().getLogger(FormatDate.class.getName());
	private static ResourceBundle resources = ResourceBundle.getBundle(FormatDate.class.getName());        
    
    public static String timeStampToMySQLString(Date date)
    {
        if(date==null)
            return null;

        SimpleDateFormat sdf = new SimpleDateFormat(resources.getString("mysql.pattern.date.format1"), Locale.FRANCE);        
        
        return sdf.format(date);
    }
    
    public static String dateToMySQLString(Date date)
    {
        if(date==null)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat(resources.getString("mysql.pattern.date.format2"), Locale.FRANCE);        
        
        return sdf.format(date);
    }

    public static String dayYearToString(Date date)
    {
        if(date==null)
            date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(resources.getString("pattern.date.format1"), Locale.FRANCE);        

        return sdf.format(date);
    }

    public static Date mySQLDateStringToDate(String dateStr)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(resources.getString("mysql.pattern.date.format2"), Locale.FRANCE);        
        
        Date aux = null;
        try
        {
            aux = sdf.parse(dateStr);
        }
        catch(ParseException pe)
        {
			logger.error("Impossible de convertir en date", pe);
        }
        catch(NullPointerException npe)
        {
			logger.info("Date nulle");
        }

        return aux;
    }
   
    public static Date mySQLTimeStampStringToDate(String timeStampStr)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(resources.getString("pattern.date.format2"));
        
        Date aux = null;
        try
        {
            aux = sdf.parse(timeStampStr);
        }
        catch(ParseException pe)
        {
			logger.error("Impossible de convertir en date", pe);
        }
        catch(NullPointerException npe)
        {
			logger.info("Date nulle");
        }

        return aux;
    }
    
    public static String timeStampToString(Date date)
    {
        if(date==null)
            return null;

        SimpleDateFormat sdf = new SimpleDateFormat(resources.getString("pattern.date.format3"), Locale.FRANCE);        
        return sdf.format(date);
    }
    
    public static String dateToString(Date date)
    {
        if(date==null)
            return null;
        
        SimpleDateFormat sdf = new SimpleDateFormat(resources.getString("pattern.date.format4"), Locale.FRANCE);        
        return sdf.format(date);
    }

	public static String dateToLongString(Date date)
	{
		if(date==null)
			return null;
        
		SimpleDateFormat sdf = new SimpleDateFormat(resources.getString("pattern.date.format5"), Locale.FRANCE);        
		return sdf.format(date);
	}

    public static Date stringToDate(String date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(resources.getString("pattern.date.format4"), Locale.FRANCE);        

        Date aux = null;
        try
        {
            aux = sdf.parse(date);
        }
        catch(ParseException pe)
        {
			logger.error("Impossible de convertir en date", pe);
        }
        catch(NullPointerException npe)
        {
			logger.error("Date nulle");
        }

        return aux;
    }

    public static void main(String[] args) throws Exception
    {
        //System.out.println(FormatDate.mySQLTimeStampStringToDate("04 09 2002 12:51:56"));
/*        
        String jour = "29";
		String mois = "2";
		String annee = "2000";
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, Integer.parseInt(jour));
		cal.set(Calendar.MONTH, Integer.parseInt(mois)-1);
		cal.set(Calendar.YEAR, Integer.parseInt(annee));
		
		if(cal.get(Calendar.MONTH)!=(Integer.parseInt(mois)-1))		
			System.out.println("false");
		else 
			System.out.println("true");	
*/		
		SimpleDateFormat patternDateTimeSDF = new SimpleDateFormat("dd/MM/yyyy/HH/mm/ss");
		String dateEntreeOccupationChoisie = patternDateTimeSDF.format(new Date());
		System.out.println(dateEntreeOccupationChoisie);
		Thread.sleep(10000);
		Calendar calEntreeOccupationChoisie = Calendar.getInstance();
		calEntreeOccupationChoisie.setTime(patternDateTimeSDF.parse(dateEntreeOccupationChoisie));
		Calendar calCurrent = Calendar.getInstance();
		calEntreeOccupationChoisie.set(Calendar.HOUR, calCurrent.get(Calendar.HOUR));
		calEntreeOccupationChoisie.set(Calendar.MINUTE, calCurrent.get(Calendar.MINUTE));
		calEntreeOccupationChoisie.set(Calendar.SECOND, calCurrent.get(Calendar.SECOND));
		dateEntreeOccupationChoisie = patternDateTimeSDF.format(calEntreeOccupationChoisie.getTime());
		System.out.println(dateEntreeOccupationChoisie);
    }
}
