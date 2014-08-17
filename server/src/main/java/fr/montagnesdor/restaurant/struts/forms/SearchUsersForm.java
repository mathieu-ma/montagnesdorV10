package fr.montagnesdor.restaurant.struts.forms;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public final class SearchUsersForm extends ActionForm
{
    private String searchedText;    
    
    
    // --------------------------------------------------------- Public Methods
    /**
     * Reset all properties to their default values.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
    	setSearchedText(null);
    }


	/**
	 * @return the searchedText
	 */
	public String getSearchedText() {
		return searchedText;
	}


	/**
	 * @param searchedText the searchedText to set
	 */
	public void setSearchedText(String searchedText) {
		this.searchedText = searchedText;
	}
}
