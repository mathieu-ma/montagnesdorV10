package fr.montagnesdor.restaurant.struts.forms;


import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import fr.montagnesdor.restaurant.services.menus.MenusManagerFactory;

public final class PurgeStatsConsumptionProductsForm extends ActionForm
{
    private String actionDo = null;
    private Short day = null;
    private Short month = null;
    private Short year = null;
    private String code;
    private Float quantity;
    private String password;
    
    
    // --------------------------------------------------------- Public Methods
    /**
     * Reset all properties to their default values.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        actionDo = null;
        password = null;
        
        code = null;
        quantity = null;
        day = null;
        month = null;
        year = null;
    }
    
    
    /**
     * Validate the properties that have been set from this HTTP request,
     * and return an <code>ActionErrors</code> object that encapsulates any
     * validation errors that have been found.  If no errors are found, return
     * <code>null</code> or an <code>ActionErrors</code> object with no
     * recorded error messages.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
    {
	    String[] minYearMaxMonthMaxYear = MenusManagerFactory.getManager().getStatsConsumptionProductsMinYearMaxMonthMaxYear();
	    if(minYearMaxMonthMaxYear[0]==null)
	    {
	        minYearMaxMonthMaxYear[0] = "-1";
	        minYearMaxMonthMaxYear[1] = "-1";
	        minYearMaxMonthMaxYear[2] = "-1";
	    }
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		ArrayList dateForMonthList = new ArrayList(12);
		for(int i=0; i<12; i++)
		{
			calendar.set(Calendar.MONTH, i);
			dateForMonthList.add(i, calendar.getTime());		
		}
		request.setAttribute("dateForMonthList", dateForMonthList);
	    request.setAttribute("minYear", minYearMaxMonthMaxYear[0]);
	    request.setAttribute("maxMonth", minYearMaxMonthMaxYear[1]);
	    request.setAttribute("maxYear", minYearMaxMonthMaxYear[2]);
        
	    ActionErrors errors = new ActionErrors();
		if(actionDo==null || actionDo.toUpperCase().equals("DISPLAY"))
	    {
		    return null;
	    }

		if ((password == null) || (password.length() < 1))
		{
			errors.add("error.password.required", new ActionError("error.password.required"));
		}

		if(day==null || month==null || year==null)
		{
		    errors.add("statsConsumptionProductsListIFrame.jsp.error.date", new ActionError("statsConsumptionProductsListIFrame.jsp.error.date"));
		}
	    
        return errors;
    }
    
    public String getCode()
    {
        return code;
    }
    public void setCode(String code)
    {
        this.code = code;
    }
    public String getActionDo()
    {
        return actionDo;
    }
    public void setActionDo(String actionDo)
    {
        this.actionDo = actionDo;
    }
    public Short getDay()
    {
        return day;
    }
    public void setDay(Short day)
    {
        this.day = day;
    }
    public Short getMonth()
    {
        return month;
    }
    public void setMonth(Short month)
    {
        this.month = month;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public Float getQuantity()
    {
        return quantity;
    }
    public void setQuantity(Float quantity)
    {
        this.quantity = quantity;
    }
    public Short getYear()
    {
        return year;
    }
    public void setYear(Short year)
    {
        this.year = year;
    }
}
