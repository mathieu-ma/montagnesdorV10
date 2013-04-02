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

import fr.montagnesdor.restaurant.services.authentication.UserManagerFactory;
import fr.montagnesdor.restaurant.struts.MontagnesDOrActionServlet;
import fr.montagnesdor.restaurant.struts.UserSession;
import fr.montagnesdor.restaurant.struts.forms.ChangeUserPasswordForm;

/**
 * @author administrateur
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ChangeUserPasswordAction extends Action
{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute(MontagnesDOrActionServlet.USER_SESSION_PREFIX_KEY);

		//Validate the request parameters specified by the user
		ActionErrors errors = new ActionErrors();

		ChangeUserPasswordForm changeUserPasswordForm = (ChangeUserPasswordForm) form;
		String levelPasswordString = changeUserPasswordForm.getLevelPassword();
		int levelPassword = 0;
		String realPassword = null;
		try
		{
			levelPassword = Integer.parseInt(levelPasswordString);
		}
		catch (Exception e)
		{
		}
		
		switch(levelPassword)
		{
			case 0:
				realPassword = userSession.getUser().getUserAuthentication().getPassword();
			break;	
			case 1:
				realPassword = userSession.getUser().getUserAuthentication().getLevelPass1();
			break;	
			case 2:
				realPassword = userSession.getUser().getUserAuthentication().getLevelPass2();
			break;	
			case 3:
				realPassword = userSession.getUser().getUserAuthentication().getLevelPass3();
			break;	
		}

		if (realPassword == null)
		{
			errors.add("error.password.noaccess", new ActionError("error.password.noaccess"));
		}
		else
		{
			String oldPassword = changeUserPasswordForm.getOldPassword();
			if (!oldPassword.equals(realPassword))
			{
				errors.add("error.password.incorrect", new ActionError("error.password.incorrect"));
			}
			else
			{
				String newPassword = changeUserPasswordForm.getNewPassword();
				String repeatPassword = changeUserPasswordForm.getRepeatedPassword();
				if(newPassword!=null && repeatPassword!=null && newPassword.equals(repeatPassword))
				{
					UserManagerFactory.getManager().changeUserPassword(userSession.getUser(), levelPassword, newPassword);
//					userSession.getUser().setLevelPass(typePassword, newPassword);
//					request.setAttribute("passwordOk", "true");
				}
				else
				{
					errors.add("error.repeated.password.different", new ActionError("error.repeated.password.different"));
				}
			}
		}
		
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
