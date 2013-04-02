/*
 * FormatDecimal.java
 *
 * Created on 23 f�vrier 2002, 13:03
 */

package fr.montagnesdor.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/*
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
*/
/**
 *
 * @author  maxime
 * @version
 */
public class FormatDecimal
{

    public static final int HALF_ROUND = 1;
    public static final int TENTH_ROUND = 2;
    
	private static DecimalFormat formatDecimal = null;

	static 
	{
		formatDecimal = new DecimalFormat("0.00");
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		formatDecimal.setDecimalFormatSymbols(dfs);
	}

	/** Creates new FormatDecimal */
	public static String formatDecimal(double valeur, int decimalPrecision)
	{
		if (valeur == 0)
			valeur = Math.abs(valeur);

		valeur = (Math.round(Math.pow(10, decimalPrecision) * valeur)) / Math.pow(10, decimalPrecision);
		formatDecimal.applyPattern("0." + paddingPattern(decimalPrecision));

		return formatDecimal.format(valeur);
	}

	public static float specificRound(float a, int specificRound)
	{
	    float result = 0;
	    switch(specificRound)
	    {
	        case HALF_ROUND:
	    		float b = a - (float) Math.floor(a);

	    		if (b < 0.25f)
	    			b = 0;
	    		else if (b < 0.75f)
	    			b = 0.5f;
	    		else
	    			b = 1;

	    		result = ((float) Math.floor(a) + b);
	        break;

	        case TENTH_ROUND:
	            result = Math.round(a*10f)/10f;
	        break;
	    }        
	    return result;
	}

	private static String paddingPattern(int nbrPattern)
	{
		String retourPattern = "";
		for (int i = 0; i < nbrPattern; i++)
			retourPattern += "0";

		return retourPattern;
	}

	public static boolean compare(float f1, float f2)
	{
		return formatDecimal.format(f1).equals(formatDecimal.format(f2));
	}

	public static void main(String[] args)
	{
		System.out.println(FormatDecimal.formatDecimal(12.2565, 1));
		/*
		try
		{
			String host = "smtp.wanadoo.fr";
			String from = "mathieu.ma@free.fr";
			String to = "mathieu.ma@free.fr";
			String bcc = "m.ma@smart-up.net";
			String cc = "mathieu-ma@wanadoo.fr";
		
			// Get system properties
			Properties props = System.getProperties();
		
			// Setup mail server
			props.put("mail.smtp.host", host);
		
			// Get session
			Session session = Session.getDefaultInstance(props, null);
		
			// Define message
			MimeMessage message = new MimeMessage(session);
		
			// Set the from address
			message.setFrom(new InternetAddress(from));
		
			// Set the to address
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			//		Set the to address
			message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
			//		Set the to address
			message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));
		
			// Set the subject
			message.setSubject("Hello JavaMail");
		
			// Set the content
			message.setText("Welcome to JavaMail");
		
			// Send message
			Transport.send(message);
		}
		catch (Exception e)
		{
		
			System.out.println(e);
		}
		*/
	}
}
