package fr.montagnesdor.restaurant.struts.forms;


import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import fr.montagnesdor.restaurant.services.menus.MenusManagerFactory;

public final class SaveOrUpdateProductForm extends ActionForm
{
    private String actionDo = null;
    private String code = null;
    private String label = null;
    private String colorRGB = null;
    private float price;
    private Long vatId;
    
    // --------------------------------------------------------- Public Methods
    /**
     * Reset all properties to their default values.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        actionDo = null;
        code = null;
        label = null;
        colorRGB = null;
        price = 0;
        vatId = null;
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
	    request.setAttribute("vatsList", MenusManagerFactory.getManager().getVats());
		if(actionDo==null || actionDo.toUpperCase().equals("DISPLAY"))
	    {
		    return null;
	    }
	    
	    if(code==null || code.length()<1)
	        errors.add("saveOrUpdateProduct.jsp.alert.code", new ActionError("saveOrUpdateProduct.jsp.alert.code"));   

	    if(actionDo!=null && !actionDo.toUpperCase().equals("DELETE"))
	    {
		    if(label==null || label.length()<1)
		        errors.add("saveOrUpdateProduct.jsp.alert.label", new ActionError("saveOrUpdateProduct.jsp.alert.label"));   
	    }
	    
        return errors;
    }
    
    public String getCode()
    {
        return code;
    }
    public void setCode(String code)
    {
        this.code = code;
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
    public float getPrice()
    {
        return price;
    }
    public void setPrice(float price)
    {
        this.price = price;
    }
    public String getColorRGB()
    {
        if(colorRGB!=null && colorRGB.toUpperCase().equals("NULL"))
            colorRGB = null;
        return colorRGB;
    }
    public void setColorRGB(String colorRGB)
    {
        this.colorRGB = colorRGB;
    }
    public Long getVatId()
    {
        return vatId;
    }
    public void setVatId(Long vatId)
    {
        this.vatId = vatId;
    }
}
