package fr.montagnesdor.restaurant.struts.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public final class CashTableForm extends ActionForm
{
	private Long tableId = null;
	private float cash;
	private float ticket;
	private float cheque;
	private float card;
	private float unpaid;				

    // --------------------------------------------------------- Public Methods
    /**
     * Reset all properties to their default values.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
		tableId = null;
		cash = ticket = cheque = card = unpaid = 0;				
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

		if (tableId == null)
		{
			errors.add("error.table.id", new ActionError("error.table.id"));
		}
		        
        return errors;
    }
	/**
	 * @return
	 */
	public float getCard()
	{
		return card;
	}

	/**
	 * @return
	 */
	public float getCash()
	{
		return cash;
	}

	/**
	 * @return
	 */
	public float getCheque()
	{
		return cheque;
	}

	/**
	 * @return
	 */
	public Long getTableId()
	{
		return tableId;
	}

	/**
	 * @return
	 */
	public float getTicket()
	{
		return ticket;
	}

	/**
	 * @return
	 */
	public float getUnpaid()
	{
		return unpaid;
	}

	/**
	 * @param f
	 */
	public void setCard(float f)
	{
		card = f;
	}

	/**
	 * @param f
	 */
	public void setCash(float f)
	{
		cash = f;
	}

	/**
	 * @param f
	 */
	public void setCheque(float f)
	{
		cheque = f;
	}

	/**
	 * @param long1
	 */
	public void setTableId(Long long1)
	{
		tableId = long1;
	}

	/**
	 * @param f
	 */
	public void setTicket(float f)
	{
		ticket = f;
	}

	/**
	 * @param f
	 */
	public void setUnpaid(float f)
	{
		unpaid = f;
	}

}
