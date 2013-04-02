package fr.montagnesdor.restaurant.struts.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public final class ChangeTableForm extends ActionForm
{
	private String tableNumber = null;
	
    // --------------------------------------------------------- Public Methods
    /**
     * Reset all properties to their default values.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        this.tableNumber = null;
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

		if ((tableNumber == null) || (tableNumber.length() < 1))
		{
			errors.add("changeTable.jsp.error.table.number", new ActionError("changeTable.jsp.error.table.number"));
		}
		        
        return errors;
    }

	/**
	 * @return
	 */
	public String getTableNumber()
	{
		return tableNumber;
	}

	/**
	 * @param string
	 */
	public void setTableNumber(String string)
	{
		tableNumber = string;
	}

}
