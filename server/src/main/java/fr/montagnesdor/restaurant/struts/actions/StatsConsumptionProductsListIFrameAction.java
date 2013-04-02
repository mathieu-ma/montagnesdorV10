package fr.montagnesdor.restaurant.struts.actions;

import fr.montagnesdor.restaurant.services.menus.MenusManagerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public final class StatsConsumptionProductsListIFrameAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
	    String[] minYearMaxMonthMaxYear = MenusManagerFactory.getManager().getStatsConsumptionProductsMinYearMaxMonthMaxYear();
        
	    if(minYearMaxMonthMaxYear[0]==null)
	    {
	        minYearMaxMonthMaxYear[0] = "-1";
	        minYearMaxMonthMaxYear[1] = "-1";
	        minYearMaxMonthMaxYear[2] = "-1";
	    }
	    String actionDo = request.getParameter("actionDo");
	    String code = request.getParameter("code");
	    String quantityStr = request.getParameter("quantity");
	    String dayStr = request.getParameter("day");
	    String monthStr = request.getParameter("month");
	    String yearStr = request.getParameter("year");
	    String sortMonotony = request.getParameter("sortMonotony");
	    
        if(actionDo!=null && actionDo.toUpperCase().equals("PURGE"))
	    {
	        code = null;
	    }
	    if(monthStr==null)
	        monthStr = minYearMaxMonthMaxYear[1]; 
	    if(yearStr==null)
	        yearStr = minYearMaxMonthMaxYear[2]; 
	    if(dayStr!=null && dayStr.equals("-1"))
	        dayStr = null; 
	    if(monthStr.equals("-1"))
	        monthStr = null; 
	    if(yearStr.equals("-1"))
	        yearStr = null; 
	    Float fQuantity = null;
	    Short sDay = null;
	    Short sMonth = null;
	    Short sYear = null;
	    
	    try
	    {
	        fQuantity = new Float(quantityStr);
	    }
	    catch(Exception e)
	    {
	    }
	    try
	    {
	        sDay = new Short(dayStr);
	    }
	    catch(Exception e)
	    {
	    }
	    try
	    {
	        sMonth = new Short(monthStr);
	    }
	    catch(Exception e)
	    {
	    }
	    try
	    {
	        sYear = new Short(yearStr);
	    }
	    catch(Exception e)
	    {
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
	    request.setAttribute("statsConsumptionProductsList", MenusManagerFactory.getManager().getStatsConsumptionProducts(code, fQuantity, sDay, sMonth, sYear, sortMonotony));
	    
        // Forward control to the specified success URI
        return (mapping.findForward("success"));
    }

}