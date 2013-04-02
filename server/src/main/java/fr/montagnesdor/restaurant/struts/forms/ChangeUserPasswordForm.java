package fr.montagnesdor.restaurant.struts.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public final class ChangeUserPasswordForm extends ActionForm
{
	private String levelPassword = null;
    private String oldPassword = null;
	private String newPassword = null;
	private String repeatedPassword = null;
	
    // --------------------------------------------------------- Public Methods
    /**
     * Reset all properties to their default values.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        this.oldPassword = null;
		this.newPassword = null;
		this.repeatedPassword = null;
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
	    ActionErrors errors = new ActionErrors();

		if ((levelPassword == null) || (levelPassword.length() < 1))
		{
			errors.add("error.level.password.required", new ActionError("error.level.password.required"));
		}
        if ((oldPassword == null) || (oldPassword.length() < 1))
        {
            errors.add("error.old.password.required", new ActionError("error.old.password.required"));
        }
        if ((newPassword == null) || (newPassword.length() < 1))
        {
            errors.add("error.new.password.required", new ActionError("error.new.password.required"));
        }
		if ((repeatedPassword == null) || (repeatedPassword.length() < 1))
		{
			errors.add("error.repeated.password.required", new ActionError("error.repeated.password.required"));
		}
		
		//Permet d'afficher dans la jsp le niveau du mot de passe dans le cas où une erreur est survenue
		request.setAttribute("attributesOut", levelPassword);
		        
        return errors;
    }
    
	/**
	 * @return
	 */
	public String getNewPassword()
	{
		return newPassword;
	}

	/**
	 * @return
	 */
	public String getOldPassword()
	{
		return oldPassword;
	}

	/**
	 * @return
	 */
	public String getRepeatedPassword()
	{
		return repeatedPassword;
	}

	/**
	 * @param string
	 */
	public void setNewPassword(String string)
	{
		newPassword = string;
	}

	/**
	 * @param string
	 */
	public void setOldPassword(String string)
	{
		oldPassword = string;
	}

	/**
	 * @param string
	 */
	public void setRepeatedPassword(String string)
	{
		repeatedPassword = string;
	}

	/**
	 * @return
	 */
	public String getLevelPassword()
	{
		return levelPassword;
	}

	/**
	 * @param string
	 */
	public void setLevelPassword(String string)
	{
		levelPassword = string;
	}

}
