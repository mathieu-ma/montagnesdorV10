/*
 * Project				montagnesdor
 * File name		TestAction.java
 * Created on		5 sept. 2004
 * @author			Mathieu MA sous conrad
 * @version		1.0
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package fr.montagnesdor.restaurant.struts.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.montagnesdor.restaurant.services.menus.MenusManagerFactory;

public class DownloadStatsConsumptionProductsListAction extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
	    Short day = null;
	    Short month = null;
	    Short year = null;
	    
	    try
	    {
		    day = new Short(request.getParameter("day"));
	    }
	    catch(Exception e)
	    {
	    }
	    try
	    {
		    month = new Short(request.getParameter("month"));
	    }
	    catch(Exception e)
	    {
	    }
	    try
	    {
		    year = new Short(request.getParameter("year"));
	    }
	    catch(Exception e)
	    {
	    }
	    
	    if(day!=null && day.shortValue()==-1)
	        day = null;
	    if(month!=null && month.shortValue()==-1)
	        month = null;
	    if(year!=null && year.shortValue()==-1)
	        year = null;
	    request.setAttribute("statsConsumptionProductsList", MenusManagerFactory.getManager().getStatsConsumptionProducts(day, month, year, "DESC"));
		// Forward control to the specified success URI
		return (mapping.findForward("success"));
	}
    
}
