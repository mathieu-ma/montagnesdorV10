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
 *	Cette classe est un mapping de la table t_user_role.
 */
public class UserRole
{
	/*
	  rol_id serial,
	  rol_label_code varchar(20) NOT NULL,  
	 */

	private Long id;
	private String labelCode;

	/**
	 * @return
	 */
	public Long getId()
	{
		return id;
	}

	/**
	 * @return
	 */
	public String getLabelCode()
	{
		return labelCode;
	}

	/**
	 * @param string
	 */
	public void setLabelCode(String string)
	{
		labelCode = string;
	}

	/**
	 * @param long1
	 */
	public void setId(Long long1)
	{
		id = long1;
	}

/*
	public boolean equals(Object obj)
	{
		boolean flag = false;
		if (obj instanceof UserRole)
		{
			UserRole objTemp = (UserRole) obj;
			if (objTemp != null)
			{
				if (objTemp.getId()!=null  && this.getId()!=null)
					flag = objTemp.getId().longValue() == this.getId().longValue();
				else
					flag = objTemp.getId() == this.getId();
					
				if (objTemp.getLabelCode()!=null  && this.getLabelCode()!=null)
					flag = objTemp.getLabelCode().equals(this.getLabelCode());
				else
					flag = objTemp.getId() == this.getId();  	 
			}
		}
		return flag;
	}
*/
}
