package fr.montagnesdor.restaurant.struts.actions;

import fr.montagnesdor.restaurant.jaas.authentication.Authentication;
import fr.montagnesdor.restaurant.services.authentication.UserManagerFactory;
import fr.montagnesdor.restaurant.struts.MontagnesDOrActionServlet;
import fr.montagnesdor.restaurant.struts.UserSession;
import fr.montagnesdor.restaurant.struts.forms.LogonForm;
import fr.montagnesdor.util.log.MyLog;
import fr.montagnesdor.util.log.MyLogFactoryImpl;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public final class LogonAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
		MyLog logger = MyLogFactoryImpl.getInstance().getLogger(LogonAction.class.getName());
				        
        HttpSession session = request.getSession();
        if(session != null)
        {
            ActionErrors errors = new ActionErrors();
            
            String login = ((LogonForm) form).getLogin();
            String password = ((LogonForm) form).getPassword();
            
            //V�érifier si l'utilisateur a bien renseign�é le login et le password !!!!!!
            logger.debug("login : "+login+" password : " +password);
            
            UserSession userSession = (UserSession)session.getAttribute(MontagnesDOrActionServlet.USER_SESSION_PREFIX_KEY);
            if(userSession == null)
            {
				logger.debug("La session de l'utilisateur est nouvelle : nom attribut " + mapping.getAttribute());
				
            	Authentication authentification = new Authentication(login, password);
            	if(authentification.authenticate())
            	{ 
                	userSession = new UserSession(getServlet().getServletContext(), session, authentification.getUser());
					userSession.setSubject(authentification.getSubject());
					Locale locale = (Locale)request.getLocale();
					userSession.setCurrentLanguage(locale.getLanguage());
					userSession.getUser().setLocalesLanguages(UserManagerFactory.getManager().getUserLocalesLanguages(userSession.getUser(), userSession.getCurrentLanguage()));
					userSession.processLanguage(userSession.getCurrentLanguage(), locale.getCountry());
                	session.setAttribute(MontagnesDOrActionServlet.USER_SESSION_PREFIX_KEY, userSession);
            	}
            	else
					errors.add("password",  new ActionError("error.authentication.failed"));
            }
            else
            {
				//errors.add("password",  new ActionError("error.authentication.failed"));
				//On ne peut revenir dans cette action si userSession n'est pas nul
				MontagnesDOrActionServlet.reinitSession(request);
				return (mapping.findForward("authorizationFailed"));
            }

			// Report any errors we have discovered back to the original form
			if (!errors.isEmpty())
			{
				saveErrors(request, errors);
				return (new ActionForward(mapping.getInput()));
			}
        }
        
        // Forward control to the specified success URI
        return (mapping.findForward("success"));
    }
    
}
