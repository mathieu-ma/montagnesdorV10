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
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import fr.montagnesdor.restaurant.services.menus.MenusManagerFactory;
import fr.montagnesdor.restaurant.struts.MontagnesDOrActionServlet;
import fr.montagnesdor.restaurant.struts.UserSession;
import fr.montagnesdor.restaurant.struts.forms.PurgeStatsConsumptionProductsForm;

public class PurgeStatsConsumptionProductsAction extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		PurgeStatsConsumptionProductsForm purgeStatsConsumptionProductsForm = (PurgeStatsConsumptionProductsForm) form;

	    if(purgeStatsConsumptionProductsForm.getActionDo()==null || purgeStatsConsumptionProductsForm.getActionDo().toUpperCase().equals("DISPLAY"))
	    {
		    return (new ActionForward(mapping.getInput()));
	    }
	    
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute(MontagnesDOrActionServlet.USER_SESSION_PREFIX_KEY);
	    
	    String code = purgeStatsConsumptionProductsForm.getCode();
	    Float quantity = purgeStatsConsumptionProductsForm.getQuantity(); 
	    Short day = purgeStatsConsumptionProductsForm.getDay(); 
	    Short month = purgeStatsConsumptionProductsForm.getMonth(); 
	    Short year = purgeStatsConsumptionProductsForm.getYear();
	    
		//Validate the request parameters specified by the user
		ActionErrors errors = new ActionErrors();
		String password = purgeStatsConsumptionProductsForm.getPassword();
		if(userSession.getLevelPass1()!=null)
		{
			if(!password.equals(userSession.getUser().getUserAuthentication().getLevelPass1()))
			{
				errors.add("error.authentication.failed", new ActionError("error.authentication.failed"));
			}
			else
			{
			    if(code!=null && code.trim().equals(""))
			        code = null; 
			    if(day!=null && day.shortValue()==-1)
			        day = null; 
			    if(month!=null && month.shortValue()==-1)
			        month = null; 
			    if(year!=null  && year.shortValue()==-1)
			        year = null; 
			    MenusManagerFactory.getManager().purgeStatsConsumptionProducts(code, quantity, day, month, year);
			}    
		}
		else
			errors.add("error.authentication.denied", new ActionError("error.authentication.denied"));
		
		// Report any errors we have discovered back to the original form
		if (!errors.isEmpty())
		{
			saveErrors(request, errors);
			return (new ActionForward(mapping.getInput()));
		}

		// Forward control to the specified success URI
		return (mapping.findForward("success"));
	}
    
}
