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
 *	Cette classe est un mapping de la table t_user_role_language.
 */
public class UserRoleLanguage
{
	/*
	  rol_id int8 NOT NULL,
	  url_label varchar(20) NOT NULL,
	  loc_id varchar(3) NOT NULL DEFAULT 'fr',
	 */

	private UserRoleLanguageID id;
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
	public UserRoleLanguageID getId()
	{
		return id;
	}

	/**
	 * @param languageID
	 */
	public void setId(UserRoleLanguageID languageID)
	{
		id = languageID;
	}

}
