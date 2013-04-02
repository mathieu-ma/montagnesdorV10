package fr.montagnesdor.restaurant.struts.forms;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import fr.montagnesdor.restaurant.services.menus.MenusManagerFactory;

public final class SearchStatsConsumptionProductsForm extends ActionForm
{
    private String actionDo = null;
    private String selectBy;
    
    private Short day = null;
    private Short month = null;
    private Short year = null;
    private String code;
    private String quantityOperator;    
    private Float quantity;
    
    
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
        selectBy = null;
        
        code = null;
        quantityOperator = null;
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

        List categoriesList = MenusManagerFactory.getManager().getCategories();
        request.setAttribute("categoriesList", categoriesList);

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

		if(day==null || month==null || year==null)
		{
		    errors.add("statsConsumptionProductsListIFrame.jsp.error.date", new ActionError("statsConsumptionProductsListIFrame.jsp.error.date"));
		}
		if(selectBy!=null && !selectBy.equals("ALL") && !selectBy.equals("NULL") && !selectBy.equals("DELETED") && !selectBy.startsWith("CAT"))
		{
		    errors.add("statsConsumptionProductsListIFrame.jsp.search.select.undefined", new ActionError("statsConsumptionProductsListIFrame.jsp.search.select.undefined"));
		}
		if(quantity!=null && (quantityOperator==null || (!quantityOperator.equals("=") && !quantityOperator.equals(">") && !quantityOperator.equals("<"))))
		{
		    errors.add("searchStatsConsumptionProductsListIFrame.jsp.quantity.operator.undefined", new ActionError("searchStatsConsumptionProductsListIFrame.jsp.quantity.operator.undefined"));
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
    public String getQuantityOperator()
    {
        return quantityOperator;
    }
    public void setQuantityOperator(String quantityOperator)
    {
        this.quantityOperator = quantityOperator;
    }
    public String getSelectBy()
    {
        return selectBy;
    }
    public void setSelectBy(String selectBy)
    {
        this.selectBy = selectBy;
    }
}
