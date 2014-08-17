package fr.montagnesdor.restaurant.struts.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public final class CustomerBillInfoForm extends ActionForm
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String customerBillName = null;
	private String customerBillAddress = null;
	private String customerBillCity = null;
	private String printingType = "BILL";

	/**
     * Reset all properties to their default values.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        this.customerBillName = null;
        this.customerBillAddress = null;
        this.customerBillCity = null;
        this.printingType = "BILL";
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
/*
		if ((customerBillName == null) || (customerBillName.length() < 1))
		{
			errors.add("billCustomerInfo.jsp.error.name", new ActionError("billCustomerInfo.jsp.error.name"));
		}
		if ((customerBillAddress == null) || (customerBillAddress.length() < 1))
		{
			errors.add("billCustomerInfo.jsp.error.address", new ActionError("billCustomerInfo.jsp.error.address"));
		}
		if ((customerBillCity == null) || (customerBillCity.length() < 1))
		{
			errors.add("billCustomerInfo.jsp.error.city", new ActionError("billCustomerInfo.jsp.error.city"));
		}
*/		        
        return errors;
    }


	public String getCustomerBillAddress() {
		return customerBillAddress;
	}


	public void setCustomerBillAddress(String customerBillAddress) {
		this.customerBillAddress = customerBillAddress;
	}


	public String getCustomerBillCity() {
		return customerBillCity;
	}


	public void setCustomerBillCity(String customerBillCity) {
		this.customerBillCity = customerBillCity;
	}


	public String getCustomerBillName() {
		return customerBillName;
	}


	public void setCustomerBillName(String customerBillName) {
		this.customerBillName = customerBillName;
	}


	public String getPrintingType() {
		return printingType;
	}
}
