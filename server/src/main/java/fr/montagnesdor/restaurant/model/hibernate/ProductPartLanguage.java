/*
 * Created on 29 avr. 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package fr.montagnesdor.restaurant.model.hibernate;

/**
 * @author Mathieu MA sous conrad
 *
 *	Cette classe est un mapping de la table t_order_part.
 */
public class ProductPartLanguage
{
	/*
		prp_id int8 NOT NULL,
		ppl_label varchar(50) NOT NULL,
		loc_id varchar(3) NOT NULL DEFAULT 'fr',
	*/
	private ProductPartLanguageID id;
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
	public ProductPartLanguageID getId()
	{
		return id;
	}

	/**
	 * @param languageID
	 */
	public void setId(ProductPartLanguageID languageID)
	{
		id = languageID;
	}

}
