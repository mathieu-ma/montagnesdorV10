package fr.montagnesdor.restaurant.struts.forms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class CountriesStatsForm extends ActionForm 
{
	
	private String startDate;
	private String endDate;	
	private String LAN_CODE_ISO; 
	private String LAN_DATE_FORMAT;
	private String dataSource;

	/* (non-Javadoc)
	* @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	*/
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest req) 
	{

//System.out.println("Your in CountriesStatsForm, Mathieu !!!");

		ActionErrors errors = new ActionErrors();
		if ((startDate == null) || (startDate.length() < 1))
			errors.add("ERROR.STATS.USERS.DATE_START", new ActionError("error.stats.users.startDate.required"));
		if ((endDate == null) || (endDate.length() < 1))
			errors.add("ERROR.STATS.USERS.DATE_END", new ActionError("error.stats.users.endDate.required"));
		if ((LAN_DATE_FORMAT == null) || (LAN_DATE_FORMAT.length() < 1))
			errors.add("ERROR.STATS.USERS.LAN_DATE_FORMAT", new ActionError("error.stats.users.LAN_DATE_FORMAT.required"));

		if(errors.size()!=0)
			return errors;
			
		Calendar cal = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat(LAN_DATE_FORMAT);
		Date dateStart = null;
		Date dateEnd = null;
		try
		{
			dateStart = sdf.parse(startDate);
			dateEnd = sdf.parse(endDate);
		}
		catch(ParseException pe)
		{
			errors.add("ERROR.STATS.USERS.LAN_DATE_FORMAT.UNVALID", new ActionError("error.stats.users.LAN_DATE_FORMAT.unvalid"));	
		}
		
		if(dateStart!=null && dateEnd!=null && dateStart.after(dateEnd))
			errors.add("ERROR.STATS.USERS.STARTDATE.AFTER.ENDATE", new ActionError("error.stats.users.startDate.after.endDate"));

		if(errors.size()!=0)
			return errors;
		
		return null;

	}

	/**
	 * Gets the startDate
	 * @return Returns a String
	 */
	public String getStartDate() {
		return startDate;
	}
    /**
     * Sets the startDate
     * @param startDate The startDate to set
     */
    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }


	/**
	 * Gets the endDate
	 * @return Returns a String
	 */
	public String getEndDate() {
		return endDate;
	}
    /**
     * Sets the endDate
     * @param endDate The endDate to set
     */
    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

	/**
	 * Gets the lAN_CODE_ISO
	 * @return Returns a String
	 */
	public String getLAN_CODE_ISO() 
	{
		return LAN_CODE_ISO;
	}
    /**
     * Sets the lAN_CODE_ISO
     * @param lAN_CODE_ISO The lAN_CODE_ISO to set
     */
    public void setLAN_CODE_ISO(String lAN_CODE_ISO)
    {
        LAN_CODE_ISO = lAN_CODE_ISO;
    }


	/**
	 * Gets the lAN_DATE_FORMAT
	 * @return Returns a String
	 */
	public String getLAN_DATE_FORMAT() {
		return LAN_DATE_FORMAT;
	}
    /**
     * Sets the lAN_DATE_FORMAT
     * @param lAN_DATE_FORMAT The lAN_DATE_FORMAT to set
     */
    public void setLAN_DATE_FORMAT(String lAN_DATE_FORMAT)
    {
        LAN_DATE_FORMAT = lAN_DATE_FORMAT;
    }

	/**
	 * Gets the dataSource
	 * @return Returns a String
	 */
	public String getDataSource() {
		return dataSource;
	}
    /**
     * Sets the dataSource
     * @param dataSource The dataSource to set
     */
    public void setDataSource(String dataSource)
    {
        this.dataSource = dataSource;
    }

 	public void reset(ActionMapping mapping, HttpServletRequest request) 
 	{

    }
}