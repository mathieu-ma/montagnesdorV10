package fr.montagnesdor.restaurant.struts.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public final class AuthorizationFailedAction extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		ActionErrors errors = new ActionErrors();
		errors.add("authorization",  new ActionError("error.authorization.failed"));
		saveErrors(request, errors);

		// Forward control to the specified success URI
		return mapping.findForward("success");
	}
}
