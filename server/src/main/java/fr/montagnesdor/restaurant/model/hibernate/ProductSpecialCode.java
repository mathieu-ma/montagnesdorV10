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
public class ProductSpecialCode
{
	/*
		psc_id serial,
	 	psc_code varchar(50) NOT NULL,
	 */
	private Long id;
	private String code;

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
	public String getCode()
	{
		return code;
	}

	/**
	 * @param long1
	 */
	public void setId(Long long1)
	{
		id = long1;
	}

	/**
	 * @param string
	 */
	public void setCode(String string)
	{
		code = string;
	}

}
