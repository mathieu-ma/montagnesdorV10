package fr.montagnesdor.restaurant.struts.forms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import fr.montagnesdor.restaurant.struts.MontagnesDOrActionServlet;
import fr.montagnesdor.restaurant.struts.UserSession;

public final class LogonForm extends ActionForm
{
    /**
     * The password.
     */
    private String password = null;
    
    
    /**
     * The username.
     */
    private String login = null;
    
    /**
     * Return the password.
     */
    public String getPassword()
    {
        return (this.password);
    }
    
    /**
     * Set the password.
     *
     * @param password The new password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    
    /**
     * Return the username.
     */
    public String getLogin()
    {
        return (this.login);
    }
    
    
    /**
     * Set the username.
     *
     * @param username The new username
     */
    public void setLogin(String login)
    {
        this.login = login;
    }
    
    
    // --------------------------------------------------------- Public Methods
    
    
    /**
     * Reset all properties to their default values.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        this.password = null;
        this.login = null;
    }
    
    
    /**
     * Validate the properties that have been set from this HTTP request,
     * and return an <code>ActionErrors</code> object that encapsulates any
     * validation errors that have been found.  If no errors are found, return
     * <code>null</code> or an <code>ActionErrors</code> object with no
     * recorded error messages.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
    {
		HttpSession session = request.getSession();
		if(session != null)
		{
			UserSession userSession = (UserSession)session.getAttribute(MontagnesDOrActionServlet.USER_SESSION_PREFIX_KEY);
			if(userSession != null)
			{
				//Si userSession n'est pas nul, on passe à LgonAction qui elle va renvoyer à AuthorizationFailedAction
				return null;
			}
		}
    	
        ActionErrors errors = new ActionErrors();
        if ((login == null) || (login.length() < 1))
        {
            errors.add("login", new ActionError("error.login.required"));
        }
        if ((password == null) || (password.length() < 1))
        {
            errors.add("password", new ActionError("error.password.required"));
        }
        return errors;
    }
}
