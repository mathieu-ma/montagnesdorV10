/*
 * Created on 30 mai 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package fr.montagnesdor.restaurant.model.hibernate;

/**
 * @author Mathieu MA sous conrad
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ProductLanguage
{

	/*
		pdt_id varchar(5) NOT NULL,
	  	pdl_label varchar(60) NOT NULL,
	  	loc_id varchar(3) NOT NULL DEFAULT 'fr',  
	 */

	private ProductLanguageID id;
	private String label;

	/**
	 * @return
	 */
	public String getLabel()
	{
		return label;
	}

	/**
	 * @param string
	 */
	public void setLabel(String string)
	{
		label = string;
	}

	/**
	 * @return
	 */
	public ProductLanguageID getId()
	{
		return id;
	}

	/**
	 * @param languageID
	 */
	public void setId(ProductLanguageID languageID)
	{
		id = languageID;
	}

}
