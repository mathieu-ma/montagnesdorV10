package fr.montagnesdor.restaurant.struts.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fr.montagnesdor.restaurant.model.hibernate.User;
import fr.montagnesdor.restaurant.services.users.UsersManagerFactory;
import fr.montagnesdor.restaurant.struts.forms.SearchUsersForm;

/**
 * @author administrateur
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SearchUsersAction extends Action
{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		SearchUsersForm theForm = (SearchUsersForm) form;
		List<User> usersList = UsersManagerFactory.getManager().searchUsers(theForm.getSearchedText());
		request.setAttribute("usersList", usersList);
		
		// Forward control to the specified success URI
		return (mapping.findForward("success"));
	}
}
