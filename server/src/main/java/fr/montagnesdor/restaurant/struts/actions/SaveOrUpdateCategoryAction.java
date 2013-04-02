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

import fr.montagnesdor.restaurant.model.hibernate.Category;
import fr.montagnesdor.restaurant.services.menus.MenusManagerFactory;
import fr.montagnesdor.restaurant.struts.MontagnesDOrActionServlet;
import fr.montagnesdor.restaurant.struts.UserSession;
import fr.montagnesdor.restaurant.struts.forms.SaveOrUpdateCategoryForm;

/**
 * @author administrateur
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SaveOrUpdateCategoryAction extends Action
{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute(MontagnesDOrActionServlet.USER_SESSION_PREFIX_KEY);

		ActionErrors errors = new ActionErrors();
		SaveOrUpdateCategoryForm saveOrUpdateCategoryForm = (SaveOrUpdateCategoryForm) form;
		Long id = saveOrUpdateCategoryForm.getId();
		Category category = MenusManagerFactory.getManager().getCategory(id);
		request.setAttribute("category", category);
		if(id!=null)
		{    
		    category = MenusManagerFactory.getManager().getCategory(id);
		}
		if(saveOrUpdateCategoryForm.getActionDo()==null || saveOrUpdateCategoryForm.getActionDo().toUpperCase().equals("DISPLAY"))
	    {
		    return (new ActionForward(mapping.getInput()));
	    }
		else
		{
			
			if(saveOrUpdateCategoryForm.getActionDo().toUpperCase().equals("CREATE"))
			{
			    if(category==null)
			    {
			         //Création d'une catégorie
			        category = new Category();
			        category.setId(id);
			        category.setCurrentLanguage(userSession.getCurrentLanguage());
			        category.setCurrentLabel(saveOrUpdateCategoryForm.getLabel());
					MenusManagerFactory.getManager().saveOrUpdateCategory(category, "SAVE");
			    }
			    else
			    {
			        request.setAttribute("category", null);
			        //Impossible de créer cette catégorie car elle existe déjà
			        errors.add("saveOrUpdateCategory.jsp.error.create.forbidden",  new ActionError("saveOrUpdateCategory.jsp.error.create.forbidden"));
			    }
			}
			else if(saveOrUpdateCategoryForm.getActionDo().toUpperCase().equals("UPDATE"))
			{    
			    if(category==null)
			    {
			        request.setAttribute("category", null);
			        //Impossible de mettre à jour cette catégorie car elle n'existe plus
			        errors.add("saveOrUpdateCategory.jsp.error.update.forbidden",  new ActionError("saveOrUpdateCategory.jsp.error.update.forbidden"));
			    }
			    else
			    {
			        //Mise à jour d'une catégorie
			        category.setCurrentLanguage(userSession.getCurrentLanguage());
			        category.setCurrentLabel(saveOrUpdateCategoryForm.getLabel());
			        MenusManagerFactory.getManager().saveOrUpdateCategory(category, "UPDATE");
			    }
			}
			else if(saveOrUpdateCategoryForm.getActionDo().toUpperCase().equals("DELETE"))
			{
			    if(category!=null)
			    {
		            MenusManagerFactory.getManager().deleteCategory(category);
			    }
			    else
			    {
				    //La catégorie n'existe plus
				    errors.add("saveOrUpdateCategory.jsp.error.delete.forbidden",  new ActionError("saveOrUpdateCategory.jsp.error.delete.forbidden"));
				    saveErrors(request, errors);
					return (mapping.findForward("error"));
			    }
			}
			else
			{
			    //Autres actions impossibles saveOrUpdateCategoryForm.getActionDo()
			    errors.add("error.params.forbidden",  new ActionError("error.params.forbidden"));
			    saveErrors(request, errors);
				return (mapping.findForward("error"));
			}
		}
		
		if(!errors.isEmpty())
		{
		    //Renvoyer vers la page de création ou bien de mise à jour de la catégorie
		    saveErrors(request, errors);
			return (new ActionForward(mapping.getInput()));
		}
		
		// Forward control to the specified success URI
		return (mapping.findForward("success"));
	}
}
