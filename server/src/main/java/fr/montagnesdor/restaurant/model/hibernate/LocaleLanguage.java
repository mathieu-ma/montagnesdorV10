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
 *	Cette classe est un mapping de la table t_locale_language.
 */
public class LocaleLanguage
{
	/*
		loc_id varchar(3) NOT NULL DEFAULT 'fr',
  		loc_id_language varchar(3) NOT NULL DEFAULT 'fr',
  		lol_label varchar(50) NOT NULL,
	*/  
	private LocaleLanguageID id;
	private String label;

	/**
	 * @return
	 */
	public LocaleLanguageID getId()
	{
		return id;
	}

	/**
	 * @param long1
	 */
	public void setId(LocaleLanguageID s)
	{
		id = s;
	}

/*
	public boolean equals(Object obj)
	{
		if(obj instanceof Locale)
		{
			Locale objTemp = (Locale)obj;
			if(objTemp!=null)
				return objTemp.getId().equals(this.getId());
		}
		return false;
	}
*/	
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

}
