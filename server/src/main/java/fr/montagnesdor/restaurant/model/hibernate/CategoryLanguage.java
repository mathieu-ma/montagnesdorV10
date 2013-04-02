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
 *	Cette classe est un mapping de la table t_category.
 */
public class CategoryLanguage
{
	/*
		cat_id serial,
  		loc_id varchar(3) NOT NULL DEFAULT 'fr',
  		ctl_label varchar(50) NOT NULL,
  	*/

	private CategoryLanguageID id;
	private String label;

	/**
	 * @return
	 */
	public CategoryLanguageID getId()
	{
		return id;
	}

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
	 * @param long1
	 */
	public void setId(CategoryLanguageID categoryLanguageID)
	{
		id = categoryLanguageID;
	}

}
