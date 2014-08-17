package fr.montagnesdor.restaurant.struts.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public final class CustomerDeliveryInfoForm extends ActionForm
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String customerDeliveryFullName = null;
	private String customerDeliveryAddress1 = null;
	private String customerDeliveryAddress2 = null;
	private String customerDeliveryAddress3 = null;
	private String customerDeliveryPhone = null;
	private String customerDeliveryEmail = null;

	private String printingType = "DELIVERY";

	/**
     * Reset all properties to their default values.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        this.customerDeliveryFullName = null;
        this.customerDeliveryAddress1 = null;
        this.customerDeliveryAddress2 = null;
        this.customerDeliveryAddress3 = null;
        this.customerDeliveryPhone = null;
        this.customerDeliveryEmail = null;
        this.printingType = "DELIVERY";
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

	public String getPrintingType() {
		return printingType;
	}


	/**
	 * @return the customerDeliveryFullName
	 */
	public String getCustomerDeliveryFullName() {
		return customerDeliveryFullName;
	}

	/**
	 * @param customerDeliveryFullName the customerDeliveryFullName to set
	 */
	public void setCustomerDeliveryFullName(String customerDeliveryFullName) {
		this.customerDeliveryFullName = customerDeliveryFullName;
	}

	/**
	 * @return the customerDeliveryAddress1
	 */
	public String getCustomerDeliveryAddress1() {
		return customerDeliveryAddress1;
	}

	/**
	 * @param customerDeliveryAddress1 the customerDeliveryAddress1 to set
	 */
	public void setCustomerDeliveryAddress1(String customerDeliveryAddress1) {
		this.customerDeliveryAddress1 = customerDeliveryAddress1;
	}

	/**
	 * @return the customerDeliveryAddress2
	 */
	public String getCustomerDeliveryAddress2() {
		return customerDeliveryAddress2;
	}

	/**
	 * @param customerDeliveryAddress2 the customerDeliveryAddress2 to set
	 */
	public void setCustomerDeliveryAddress2(String customerDeliveryAddress2) {
		this.customerDeliveryAddress2 = customerDeliveryAddress2;
	}

	/**
	 * @return the customerDeliveryAddress3
	 */
	public String getCustomerDeliveryAddress3() {
		return customerDeliveryAddress3;
	}

	/**
	 * @param customerDeliveryAddress3 the customerDeliveryAddress3 to set
	 */
	public void setCustomerDeliveryAddress3(String customerDeliveryAddress3) {
		this.customerDeliveryAddress3 = customerDeliveryAddress3;
	}

	/**
	 * @return the customerDeliveryPhone
	 */
	public String getCustomerDeliveryPhone() {
		return customerDeliveryPhone;
	}

	/**
	 * @param customerDeliveryPhone the customerDeliveryPhone to set
	 */
	public void setCustomerDeliveryPhone(String customerDeliveryPhone) {
		this.customerDeliveryPhone = customerDeliveryPhone;
	}

	/**
	 * @return the customerDeliveryEmail
	 */
	public String getCustomerDeliveryEmail() {
		return customerDeliveryEmail;
	}

	/**
	 * @param customerDeliveryEmail the customerDeliveryEmail to set
	 */
	public void setCustomerDeliveryEmail(String customerDeliveryEmail) {
		this.customerDeliveryEmail = customerDeliveryEmail;
	}
}
