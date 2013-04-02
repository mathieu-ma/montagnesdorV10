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
 *	Cette classe est un mapping de la table t_locale.
 */
public class Locale
{
	/*
		loc_id varchar(3),
	*/

	private String id;

	/**
	 * @return
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @param long1
	 */
	public void setId(String s)
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
}
