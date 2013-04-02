package fr.montagnesdor.restaurant.struts.actions;

import fr.montagnesdor.restaurant.Constants;
import fr.montagnesdor.restaurant.services.authentication.UserManagerFactory;
import fr.montagnesdor.restaurant.struts.MontagnesDOrActionServlet;
import fr.montagnesdor.restaurant.struts.UserSession;
import fr.montagnesdor.util.log.MyLog;
import fr.montagnesdor.util.log.MyLogFactoryImpl;
import fr.montagnesdor.util.log.MyLogImpl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public final class ChangeLanguageAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
		MyLog logger = MyLogFactoryImpl.getInstance().getLogger(ChangeLanguageAction.class.getName());
				        
        HttpSession session = request.getSession();
        if(session != null)
        {
            UserSession userSession = (UserSession)session.getAttribute(MontagnesDOrActionServlet.USER_SESSION_PREFIX_KEY);
            if(userSession != null)
            {
				String language = request.getParameter("language");
				if(language==null)
					language = Constants.DEFAULT_LOCALE_LANGUAGE;

				List list= Arrays.asList(Locale.getISOLanguages());
				if(!list.contains(language))
					language = Constants.DEFAULT_LOCALE_LANGUAGE;

				Locale locale = request.getLocale();
				userSession.processLanguage(language, locale.getCountry());
				userSession.getUser().setLocalesLanguages(UserManagerFactory.getManager().getUserLocalesLanguages(userSession.getUser(), userSession.getCurrentLanguage()));
				
				logger.debug(MyLogImpl.MESSAGES.getString("message.info.language") + " : "  +  language);
				
		        // Forward control to the specified success URI
		        return (mapping.findForward("success"));
            }
        }
        
        // Forward control to the specified success URI
        return (mapping.findForward("sessionInvalide"));
    }
    
}
