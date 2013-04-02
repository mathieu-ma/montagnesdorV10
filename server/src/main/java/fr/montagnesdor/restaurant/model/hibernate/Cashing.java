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
 *	Cette classe est un mapping de la table t_cashing.
 */
public class Cashing
{
/*
	csh_id serial,
	dtb_id int8 NOT NULL,
	csh_cash numeric(10,2) NOT NULL DEFAULT 0.00,
	csh_ticket numeric(10,2) NOT NULL DEFAULT 0.00,
	csh_cheque numeric(10,2) NOT NULL DEFAULT 0.00,
	csh_card numeric(10,2) NOT NULL DEFAULT 0.00,
	csh_unpaid numeric(10,2) NOT NULL DEFAULT 0.00,
*/
	
	private Long id;
	private DinnerTable dinnerTable;
	private float cash;
	private float ticket;	
	private float cheque;
	private float card;
	private float unpaid;

	/**
	 * @return
	 */
	public float getCard()
	{
		return card;
	}

	/**
	 * @return
	 */
	public float getCash()
	{
		return cash;
	}

	/**
	 * @return
	 */
	public float getCheque()
	{
		return cheque;
	}

	/**
	 * @return
	 */
	public DinnerTable getDinnerTable()
	{
		return dinnerTable;
	}

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
	public float getTicket()
	{
		return ticket;
	}

	/**
	 * @return
	 */
	public float getUnpaid()
	{
		return unpaid;
	}

	/**
	 * @param f
	 */
	public void setCard(float f)
	{
		card = f;
	}

	/**
	 * @param f
	 */
	public void setCash(float f)
	{
		cash = f;
	}

	/**
	 * @param f
	 */
	public void setCheque(float f)
	{
		cheque = f;
	}

	/**
	 * @param table
	 */
	public void setDinnerTable(DinnerTable table)
	{
		dinnerTable = table;
	}

	/**
	 * @param f
	 */
	public void setTicket(float f)
	{
		ticket = f;
	}

	/**
	 * @param f
	 */
	public void setUnpaid(float f)
	{
		unpaid = f;
	}

/**
 * @param long1
 */
public void setId(Long long1)
{
	id = long1;
}

}
