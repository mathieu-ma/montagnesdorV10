package fr.montagnesdor.restaurant.struts.forms;


import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public final class SaveOrUpdateCategoryForm extends ActionForm
{
    private String actionDo = null;
    private Long id = null;
    private String label = null;
    
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
        id = null;
        label = null;
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

		if(actionDo==null || actionDo.toUpperCase().equals("DISPLAY"))
	    {
		    return null;
	    }
	    
	    if(actionDo!=null && !actionDo.toUpperCase().equals("DELETE"))
	    {
		    if(label==null || label.length()<1)
		        errors.add("saveOrUpdateCategory.jsp.alert.label", new ActionError("saveOrUpdateCategory.jsp.alert.label"));   
	    }
	    
        return errors;
    }
    
    public Long getId()
    {
        return id;
    }
    
    public void setId(Long id)
    {
        this.id = id;
    }
    
    public String getActionDo()
    {
        return actionDo;
    }
    
    public void setActionDo(String actionDo)
    {
        this.actionDo = actionDo;
    }
    
    public String getLabel()
    {
        try
        {
            byte [] hibytes = ((String)label).getBytes("ISO-8859-1");
            label = new String(hibytes, "UTF-8");
        }
        catch(UnsupportedEncodingException uee)
        {
        }
        return label;
    }
    
    public void setLabel(String label)
    {
        this.label = label;
    }
}
