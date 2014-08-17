package fr.montagnesdor.restaurant.struts.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.montagnesdor.restaurant.model.hibernate.User;
import fr.montagnesdor.restaurant.model.hibernate.UserRole;
import fr.montagnesdor.restaurant.services.users.UsersManagerFactory;
import fr.montagnesdor.restaurant.struts.forms.SaveOrUpdateUserForm;

/**
 * @author administrateur
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SaveOrUpdateUserAction extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		ActionErrors errors = new ActionErrors();
		SaveOrUpdateUserForm theForm = (SaveOrUpdateUserForm) form;
		if(theForm.getActionDo()==null || theForm.getActionDo().toUpperCase().equals("DISPLAY"))
	    {
			User user = null;
			// id = 0 when create new user
			if(theForm.getUser().getId() != null)
			{    
			    user = UsersManagerFactory.getManager().findUser(theForm.getUser().getId());
			}
			List<UserRole> roles = UsersManagerFactory.getManager().findUserRoles();
			request.setAttribute("roles", roles);
			request.setAttribute("user", user);
			theForm.setUser(user);
		    return (new ActionForward(mapping.getInput()));
	    }
		else
		{
			if(theForm.getActionDo().toUpperCase().equals("CREATE"))
			{
			    //Create user
				User user = theForm.getUser();
			    UsersManagerFactory.getManager().saveOrUpdateUser(user);
			}
			else if(theForm.getActionDo().toUpperCase().equals("UPDATE"))
			{    
			    //Update user
				User user = theForm.getUser();
				UsersManagerFactory.getManager().saveOrUpdateUser(user);
			}
			else if(theForm.getActionDo().toUpperCase().equals("DELETE"))
			{
			    //Delete user
				User user = theForm.getUser();
				UsersManagerFactory.getManager().deleteUser(user);
			}
			else if(theForm.getActionDo().toUpperCase().equals("PRINT"))
			{
			    //Update user
				User user = theForm.getUser();
				UsersManagerFactory.getManager().saveOrUpdateUser(user);
				request.setAttribute("user", user);
				//Print
				// Forward control to the specified success URI
				return (mapping.findForward("successPrint"));
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
