/*
 * Commandes.java
 *
 * Created on 21 f�vrier 2002, 20:57
 */
package fr.montagnesdor.restaurant.applets;

import java.applet.Applet;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author  maxime
 * @version
 */
public class TableOrdersApplet extends Applet
{
    private String minAmmountSumsTakeaway = "15";
    private String ratioReductionTakeaway = "10";
        
	private static String entryDate = null;
	private static SimpleDateFormat patternDateSDF = null;
	private static String patternDate = null;
    
	private String tableNumber = null;
	private boolean takeaway = false;
    private String customerNumber = null;

    private String label = null;
    private String price = null;
    private String color = null;
    
    private static String URLServlet = null;
    
    /** Initialization method that will be called after the applet is loaded
     *  into the browser.
     */
    public void init()
    {
        //super.init();
        
        if(entryDate==null)
        {
            if(getParameter("minAmmountSumsTakeaway")!=null)
                minAmmountSumsTakeaway = getParameter("minAmmountSumsTakeaway");
            if(getParameter("ratioReductionTakeaway")!=null)
                ratioReductionTakeaway = getParameter("ratioReductionTakeaway");						

        	URLServlet = this.getCodeBase().getProtocol()+"://"+this.getCodeBase().getHost()+":"+this.getCodeBase().getPort()+getParameter("URLServlet");
			patternDate = getParameter("patternDate");
			patternDateSDF = new SimpleDateFormat(patternDate);
			
			entryDate = patternDateSDF.format(new Date());
			
			System.out.println("Initialisation de TableApplet  URLServlet :  1 "+URLServlet);
			System.out.println("Initialisation de TableApplet  patternDate : 1 "+patternDate);
			System.out.println("Initialisation de TableApplet  entryDate :  1 "+entryDate);
        }
    }
    
    private void resetAll()
    {
        this.tableNumber = null;
        this.takeaway = false;
        this.customerNumber = null;
        this.label = null;
        this.price = null;
        this.color = null;
    }
    
    public boolean isSessionValide()
    {
//System.out.println("Est-ce valide : numeroTable="+numeroTable+" : customerNumber="+customerNumber+" : designation="+designation+" : prix="+prix);        
        return ((tableNumber!=null) || (customerNumber!=null) || (label!=null) || (price!=null));
    }
    
	public boolean isProduitEffacable(String codeProduit)
	{
		boolean flag = false;
		try
		{
			String requete = URLEncoder.encode("action", "UTF-8")+"="+URLEncoder.encode("isProduitEffacable", "UTF-8")+"&"+URLEncoder.encode("codeProduit", "UTF-8")+"="+URLEncoder.encode(codeProduit, "UTF-8"); 
			String isProduitEffacable = (String)sendRequest(requete);
			if(isProduitEffacable==null)
			{
				resetAll();
			}
			else if(isProduitEffacable.equals("true"))
			{
				flag = true;
			}
		}
		catch(UnsupportedEncodingException uee)
		{
			System.out.println("Impossible d'encoder la requ�te "+uee);
		}
        
		return flag;
	}
	
	public boolean isTypeProduitEffacable(String idType)
	{
		boolean flag = false;
		try
		{
			String request = URLEncoder.encode("action", "UTF-8")+"="+URLEncoder.encode("isTypeProduitEffacable", "UTF-8")+"&"+URLEncoder.encode("idType", "UTF-8")+"="+URLEncoder.encode(idType, "UTF-8"); 
			String isTypeProduitEffacable = (String)sendRequest(request);
			if(isTypeProduitEffacable==null)
			{
				resetAll();
			}
			else if(isTypeProduitEffacable.equals("true"))
			{
				flag = true;
			}
		}
		catch(UnsupportedEncodingException uee)
		{
			System.out.println("Impossible d'encoder la requ�te "+uee);
		}
        
		return flag;
	}
    
    private boolean getCustomerNumber(String tableNumber)
    {
        boolean flag = false;
        try
        {
            String request = URLEncoder.encode("action", "UTF-8")+"="+URLEncoder.encode("getCustomerNumber", "UTF-8")+"&"+URLEncoder.encode("tableNumber", "UTF-8")+"="+URLEncoder.encode(tableNumber, "UTF-8");
            this.tableNumber = tableNumber;            
            customerNumber = (String)sendRequest(request);
            if(customerNumber==null)
            {
                resetAll();
            }
            else if(!customerNumber.equals(""))
            {
                flag = true;
            }
        }
        catch(UnsupportedEncodingException uee)
        {
            System.out.println("Impossible d'encoder la requ�te "+uee);
        }
        
        return flag;
    }
    
    public String getTableNumber()
    {
        return tableNumber;
    }
    
    public boolean isTableExist(String tableNumber)
    {
		resetAll();
        return getCustomerNumber(tableNumber);
    }
    
    public String getCustomerNumber()
    {
        return customerNumber;
    }
    
    private boolean getProduct(String code)
    {
        boolean flag = false;
        try
        {
            String request = URLEncoder.encode("action", "UTF-8")+"="+URLEncoder.encode("getProduct", "UTF-8")+"&"+URLEncoder.encode("code", "UTF-8")+"="+URLEncoder.encode(code, "UTF-8");
            String[] codeDesignationPrixColor = (String[])sendRequest(request);
            if(codeDesignationPrixColor==null || codeDesignationPrixColor.length == 0)
            {
                if(codeDesignationPrixColor==null)
                    resetAll();
            }
            else
            {
				label = codeDesignationPrixColor[1];
				price = codeDesignationPrixColor[2];
				color = codeDesignationPrixColor[3];
                flag = true;
            }
        }
        catch(UnsupportedEncodingException uee)
        {
            System.out.println("Impossible d'encoder la requ�te "+uee);
        }
        
        return flag;
    }
    
    public boolean isProductExist(String code)
    {
        return getProduct(code);
    }
    
    public String getLabel(String codeProduit)
    {
        return label;
    }
    
    public String getPrice(String codeProduit)
    {
        return price;
    }
    
    public boolean setCustomerNumber(String customerNumber)
    {
        boolean flag = false;
        try
        {
            String request = URLEncoder.encode("action", "UTF-8")+"="+URLEncoder.encode("setCustomerNumber", "UTF-8")+"&"+URLEncoder.encode("tableNumber", "UTF-8")+"="+URLEncoder.encode(tableNumber, "UTF-8")+"&"+URLEncoder.encode("customerNumber", "UTF-8")+"="+URLEncoder.encode(customerNumber, "UTF-8");
            if(sendRequest(request)!=null)
            {
                this.customerNumber = customerNumber;
                flag = true;
            }
            else
                resetAll();
        }
        catch(UnsupportedEncodingException uee)
        {
            System.out.println("Impossible d'encoder la requ�te");
        }
        
        return flag;
    }

	public boolean setTakeaway(boolean takeaway)
	{
		boolean flag = false;
		//S'assurer que le nombre de personne est à zéro en cas d'enregistrment de la table
		customerNumber = "0";
		try
		{
			String request = URLEncoder.encode("action", "UTF-8")+"="+URLEncoder.encode("setTakeaway", "UTF-8")+"&"+URLEncoder.encode("tableNumber", "UTF-8")+"="+URLEncoder.encode(tableNumber, "UTF-8")+"&"+URLEncoder.encode("takeaway", "UTF-8")+"="+URLEncoder.encode(takeaway+"", "UTF-8");
			if(sendRequest(request)!=null)
			{
				this.takeaway = takeaway;
				flag = true;
			}
			else
				resetAll();
		}
		catch(UnsupportedEncodingException uee)
		{
			System.out.println("Impossible d'encoder la requ�te");
		}
        
		return flag;
	}
	
	public boolean isTakeaway()
	{
		 return takeaway;
	}

    public String saveOrderLine(String orderLineId, String quantity, String code, String label, String price, String amount)
    {
		if(entryDate==null)
			entryDate = patternDateSDF.format(new Date());

		try
		{	
			Calendar calendarEntryDate = Calendar.getInstance();
			calendarEntryDate.setTime(patternDateSDF.parse(entryDate));
		
			Calendar calCurrent = Calendar.getInstance();
			calendarEntryDate.set(Calendar.HOUR_OF_DAY, calCurrent.get(Calendar.HOUR_OF_DAY));
			calendarEntryDate.set(Calendar.MINUTE, calCurrent.get(Calendar.MINUTE));
			calendarEntryDate.set(Calendar.SECOND, calCurrent.get(Calendar.SECOND));
		
			entryDate = patternDateSDF.format(calendarEntryDate.getTime());
		}
		catch(Exception e)
		{
			System.out.println("Impossible de r�écup�érer la date d'entré�e d'occupation");
			e.printStackTrace();
		}

		try
        {
            String requete =    URLEncoder.encode("action", "UTF-8")+"="+URLEncoder.encode("saveOrderLine", "UTF-8")+"&"+
								URLEncoder.encode("tableNumber", "UTF-8")+"="+URLEncoder.encode(tableNumber, "UTF-8")+"&"+
								URLEncoder.encode("entryDate", "UTF-8")+"="+URLEncoder.encode(entryDate, "UTF-8")+"&"+
								URLEncoder.encode("patternDate", "UTF-8")+"="+URLEncoder.encode(patternDate, "UTF-8")+"&"+
                                URLEncoder.encode("customerNumber", "UTF-8")+"="+URLEncoder.encode(customerNumber, "UTF-8")+"&"+
                                URLEncoder.encode("orderLineId", "UTF-8")+"="+URLEncoder.encode(orderLineId, "UTF-8")+"&"+
                                URLEncoder.encode("quantity", "UTF-8")+"="+URLEncoder.encode(quantity, "UTF-8")+"&"+
                                URLEncoder.encode("code", "UTF-8")+"="+URLEncoder.encode(code, "UTF-8");
                        
            if(label!=null)
                requete+="&"+URLEncoder.encode("label", "UTF-8")+"="+URLEncoder.encode(label, "UTF-8");
            if(price!=null)
                requete+="&"+URLEncoder.encode("price", "UTF-8")+"="+URLEncoder.encode(price, "UTF-8");
            if(amount!=null)
                requete+="&"+URLEncoder.encode("amount", "UTF-8")+"="+URLEncoder.encode(amount, "UTF-8");
			
			orderLineId = (String)sendRequest(requete);
            
        }
        catch(UnsupportedEncodingException uee)
        {
            System.out.println("Impossible d'encoder la requ�te");
        }
        return orderLineId;
    }
    
    public boolean deleteOrderLine(String orderLineId)
    {
        boolean flag = false;
        try
        {
            String request = 	URLEncoder.encode("action", "UTF-8")+"="+URLEncoder.encode("deleteOrderLine", "UTF-8")+"&"+
            					URLEncoder.encode("tableNumber", "UTF-8")+"="+URLEncoder.encode(tableNumber, "UTF-8")+"&"+
            					URLEncoder.encode("orderLineId", "UTF-8")+"="+URLEncoder.encode(orderLineId, "UTF-8");

            if(sendRequest(request)!=null)
                flag = true;
        }
        catch(UnsupportedEncodingException uee)
        {
            System.out.println("Impossible d'encoder la requ�te");
        }
        return flag;
    }
    
    public boolean setReductionRatio(String reductionRatio, String isReductionRatioChanged)
    {
        boolean flag = false;
        try
        {
            String request = 	URLEncoder.encode("action", "UTF-8")+"="+URLEncoder.encode("setReductionRatio", "UTF-8")+"&"+
            					URLEncoder.encode("tableNumber", "UTF-8")+"="+URLEncoder.encode(tableNumber, "UTF-8")+"&"+
            					URLEncoder.encode("reductionRatio", "UTF-8")+"="+URLEncoder.encode(reductionRatio, "UTF-8")+"&"+
            					URLEncoder.encode("isReductionRatioChanged", "UTF-8")+"="+URLEncoder.encode(isReductionRatioChanged, "UTF-8");
            if(sendRequest(request)!=null)
                flag = true;
        }
        catch(UnsupportedEncodingException uee)
        {
            System.out.println("Impossible d'encoder la requête");
        }
        return flag;
    }

	public boolean isReductionRatioChanged()
	{
		boolean flag = false;
		try
		{
			String request = URLEncoder.encode("action", "UTF-8")+"="+URLEncoder.encode("isReductionRatioChanged", "UTF-8")+"&"+URLEncoder.encode("tableNumber", "UTF-8")+"="+URLEncoder.encode(tableNumber, "UTF-8");
			Boolean isReductionRatioChanged = (Boolean)sendRequest(request);
			if(isReductionRatioChanged!=null)
				flag = isReductionRatioChanged.booleanValue();
		}
		catch(UnsupportedEncodingException uee)
		{
			System.out.println("Impossible d'encoder la requ�te");
		}
		return flag;
	}

    public boolean setPrintDate(String date, String month, String year)
    {
        boolean flag = false;
        try
        {
        	String newDate = "null";
        	try
        	{
        		Integer.parseInt(date);	
				Integer.parseInt(month);
				Integer.parseInt(year);
				Calendar cal = Calendar.getInstance();
				cal.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(date));
				newDate = patternDateSDF.format(cal.getTime());
        	}
        	catch(NumberFormatException nfe)
        	{
				System.out.println("Date nulle");
        	}
/*
         	if(!newDate.equals("null"))
        	{
				newDate = patternDateSDF.format(new Date());        		
        	}
*/        	
            String request = 	URLEncoder.encode("action", "UTF-8")+"="+URLEncoder.encode("setPrintDate", "UTF-8")+"&"+
            					URLEncoder.encode("tableNumber", "UTF-8")+"="+URLEncoder.encode(tableNumber, "UTF-8")+"&"+
            					URLEncoder.encode("newDate", "UTF-8")+"="+URLEncoder.encode(newDate, "UTF-8")+"&"+
								URLEncoder.encode("patternDate", "UTF-8")+"="+URLEncoder.encode(patternDate, "UTF-8");
            if(sendRequest(request)!=null)
                flag = true;
        }
        catch(UnsupportedEncodingException uee)
        {
            System.out.println("Impossible d'encoder la requ�te");
        }
        return flag;
    }

    public boolean changerTable(String oldtableNumber, String newtableNumber)
    {
        boolean flag = false;
        try
        {
            String request = URLEncoder.encode("action", "UTF-8")+"="+URLEncoder.encode("changerTable", "UTF-8")+"&"+URLEncoder.encode("tableNumber", "UTF-8")+"="+URLEncoder.encode(oldtableNumber, "UTF-8")+"&"+URLEncoder.encode("newtableNumber", "UTF-8")+"="+URLEncoder.encode(newtableNumber, "UTF-8");
            String result = (String)sendRequest(request);
            if(result!=null)
            {
                if(!result.equals(""))
                {
                    this.tableNumber = newtableNumber;
                    this.customerNumber = result;
                    flag = true;
                }
            }
            else
                resetAll();
        }
        catch(UnsupportedEncodingException uee)
        {
            System.out.println("Impossible d'encoder la requ�te");
        }
        return flag;
    }

    public boolean deleteDinnerTable(String tableId)
    {
        boolean flag = false;
        try
        {
            String request = URLEncoder.encode("action", "UTF-8")+"="+URLEncoder.encode("deleteDinnerTable", "UTF-8")+"&"+URLEncoder.encode("tableNumber", "UTF-8")+"="+URLEncoder.encode(tableNumber, "UTF-8")+"&"+URLEncoder.encode("tableId", "UTF-8")+"="+URLEncoder.encode(tableId, "UTF-8");
            if(sendRequest(request)!=null)
                flag = true;
        }
        catch(UnsupportedEncodingException uee)
        {
            System.out.println("Impossible d'encoder la requête");
        }
        return flag;
    }

    public boolean cumulerTable(String oldTableNumber, String newTableNumber)
    {
        boolean flag = false;
        try
        {
            String request = URLEncoder.encode("action", "UTF-8")+"="+URLEncoder.encode("cumulerTable", "UTF-8")+"&"+URLEncoder.encode("tableNumber", "UTF-8")+"="+URLEncoder.encode(oldTableNumber, "UTF-8")+"&"+URLEncoder.encode("newTableNumber", "UTF-8")+"="+URLEncoder.encode(newTableNumber, "UTF-8");
            String result = (String)sendRequest(request);
            if(result!=null)
            {
                if(!result.equals(""))
                {
                    this.tableNumber = newTableNumber;
                    this.customerNumber = result;
                    flag = true;
                }
            }
            else
                resetAll();
        }
        catch(UnsupportedEncodingException uee)
        {
            System.out.println("Impossible d'encoder la requ�te");
        }
        return flag;
    }

    private Object sendRequest(String request)
    {
        Object result = null;
        
        java.io.DataOutputStream dos = null;
        java.io.ObjectInputStream ois = null;
        try
        {
            //Demande une conection � une servlet et r�cup�rer des listes d'objets
            //R�cup�re l'url de la servlet venant de la page HTML
            java.net.URL url = new java.net.URL(URLServlet);
            
            //Ouvre une connection � partir de l'url obtenue
            java.net.URLConnection uc = url.openConnection();
            uc.setDoInput(true);
            uc.setDoOutput(true);
            uc.setUseCaches(false);
            uc.setRequestProperty("Content-type","application/x-www-form-urlencoded");
            
//System.out.println("envoyerRequete(String requete) 2 " );
            //Ouvre un flux de sortir � partir de la connection
            dos = new java.io.DataOutputStream(uc.getOutputStream());
            dos.writeBytes(request);
            dos.flush();
//System.out.println("envoyerRequete(String requete) 3 " );
            //Ouvre un flux d'entr�e � partir de la connection
            ois = new java.io.ObjectInputStream(uc.getInputStream());
//System.out.println("envoyerRequete(String requete) 4 " );
            result = ois.readObject();
//System.out.println("envoyerRequete(String requete) 5 " + result);
        }
        catch(java.io.IOException ioe)
        {
            ioe.printStackTrace();
        }
        catch(ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
        }
        finally
        {
            try
            {dos.close();}catch(Exception e)
            {}
            try
            {ois.close();}catch(Exception e)
            {}
        }
        
        return result;
    }
	/**
	 * Returns the dateEntreeOccupationChoisie.
	 * @return String
	 */
	public String getEntryDate()
	{

		return entryDate;
	}

	/**
	 * Sets the dateEntreeOccupationChoisie.
	 * @param dateEntreeOccupationChoisie The dateEntreeOccupationChoisie to set
	 */
	public void setEntryDate(String date, String month, String year)
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(date));
		entryDate = patternDateSDF.format(cal.getTime());
	}
	/**
	 * @return
	 */
	public boolean isAllowModifyOrdersPrinting()
	{
		boolean flag = false;
		try
		{
			String request = URLEncoder.encode("action", "UTF-8")+"="+URLEncoder.encode("isAllowModifyOrdersPrinting", "UTF-8")+"&"+URLEncoder.encode("tableNumber", "UTF-8")+"="+URLEncoder.encode(tableNumber, "UTF-8");
			Boolean isAllowModifyOrdersPrinting = (Boolean)sendRequest(request);
			if(isAllowModifyOrdersPrinting!=null)
				flag = isAllowModifyOrdersPrinting.booleanValue();
		}
		catch(UnsupportedEncodingException uee)
		{
			System.out.println("Impossible d'encoder la requ�te");
		}
		return flag;
	}

    /**
     * @return Renvoie color.
     */
    public String getColor()
    {
        return color;
    }
    /**
     * @return Renvoie minAmmountSumsTakeaway.
     */
    public String getMinAmmountSumsTakeaway()
    {
        return minAmmountSumsTakeaway;
    }
    /**
     * @return Renvoie ratioReductionTakeaway.
     */
    public String getRatioReductionTakeaway()
    {
        return ratioReductionTakeaway;
    }
}
