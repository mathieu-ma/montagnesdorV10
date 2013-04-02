package fr.montagnesdor.restaurant.struts.actions;

import fr.montagnesdor.restaurant.services.menus.MenusManagerFactory;
import fr.montagnesdor.restaurant.struts.MontagnesDOrActionServlet;
import fr.montagnesdor.restaurant.struts.UserSession;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public final class SearchProductsListAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute(MontagnesDOrActionServlet.USER_SESSION_PREFIX_KEY);
        
		String searchedField = request.getParameter("searchedField");
		if(searchedField==null)
		    searchedField = "id";
		String searchedValue = request.getParameter("searchedValue");
		if(searchedValue==null)
		    searchedValue = "";
		String sortMonotony = request.getParameter("sortMonotony");
		if(sortMonotony==null)
			sortMonotony = "asc";
		
		 try
	     {
		     if(searchedField.toUpperCase().equals("LABEL"))
		     {    
		         byte [] hibytes = ((String)searchedValue).getBytes("ISO-8859-1");
		         searchedValue = new String(hibytes, "UTF-8");
		     }    
	     }
	     catch(UnsupportedEncodingException uee)
	     {
	     }
		
		List list = MenusManagerFactory.getManager().searchProducts(searchedField, searchedValue, sortMonotony, userSession.getCurrentLanguage());
		request.setAttribute("productsList", list);

		List categoriesList = MenusManagerFactory.getManager().getCategories();
		request.setAttribute("categoriesList", categoriesList);
		
		// Forward control to the specified success URI
        return (mapping.findForward("success"));
    }

}