package fr.montagnesdor.restaurant.struts.forms;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import fr.montagnesdor.restaurant.model.hibernate.User;
import fr.montagnesdor.restaurant.model.hibernate.UserAddress;
import fr.montagnesdor.restaurant.model.hibernate.UserEmail;
import fr.montagnesdor.restaurant.model.hibernate.UserPhone;

public final class SaveOrUpdateUserForm extends ActionForm
{
    private String actionDo = null;
    private User user = null;

    //  Public Methods
    /**
     * Reset all properties to their default values.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        actionDo = null;
        user = new User();
        // Only one phone
        user.getPhones().add(new UserPhone());
        // Only one email
        user.getEmails().add(new UserEmail());
        // Only one address
        user.getAddresses().add(new UserAddress());
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

        return errors;
    }
    
    public String getActionDo()
    {
        return actionDo;
    }
    
    public void setActionDo(String actionDo)
    {
        this.actionDo = actionDo;
    }
    
    public User getUser()
    {
        return user;
    }
    
    public void setUser(User user)
    {
        this.user = user;
    }
}
