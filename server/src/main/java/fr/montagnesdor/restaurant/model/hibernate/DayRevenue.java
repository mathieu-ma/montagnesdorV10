/*
 * Created on 29 avr. 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package fr.montagnesdor.restaurant.model.hibernate;

import java.util.Date;
import java.util.List;

/**
 * @author Mathieu MA sous conrad
 *
 *	Cette classe est un mapping de la table t_day_revenue.
 */
public class DayRevenue
{
/*	
	drv_id serial,
	drv_revenue_date timestamp NOT NULL,
	drv_print_date timestamp,  
	drv_closing_date timestamp,
	drv_cash numeric(10,2) NOT NULL DEFAULT 0.00,
	drv_ticket numeric(10,2) NOT NULL DEFAULT 0.00,
	drv_cheque numeric(10,2) NOT NULL DEFAULT 0.00,
	drv_card numeric(10,2) NOT NULL DEFAULT 0.00,
	drv_unpaid numeric(10,2) NOT NULL DEFAULT 0.00,
	drv_amount numeric(10,2) NOT NULL DEFAULT 0.00,
	ttb_id int8 NOT NULL DEFAULT 1,
*/

	private Long id;
	private Date revenueDate;
	private Date printDate;	
	private Date closingDate;
	private float cash;
	private float ticket;	
	private float cheque;
	private float card;
	private float online;
	private float unpaid;
	private float amount;
	//private TypeTable typeTable;
	private boolean takeaway;

	//Liste des valeurs des tva de cette recette journalière
	private List vats;

	/**
	 * @return
	 */
	public float getAmount()
	{
		return amount;
	}

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
	public Date getClosingDate()
	{
		return closingDate;
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
	public Date getPrintDate()
	{
		return printDate;
	}

	/**
	 * @return
	 */
	public Date getRevenueDate()
	{
		return revenueDate;
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
	public void setAmount(float f)
	{
		amount = f;
	}

	/**
	 * @param f
	 */
	public void setCard(float f)
	{
		card = f;
	}

	/**
	 * @return the online
	 */
	public float getOnline() {
		return online;
	}

	/**
	 * @param online the online to set
	 */
	public void setOnline(float online) {
		this.online = online;
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
	 * @param date
	 */
	public void setClosingDate(Date date)
	{
		closingDate = date;
	}

	/**
	 * @param date
	 */
	public void setPrintDate(Date date)
	{
		printDate = date;
	}

	/**
	 * @param date
	 */
	public void setRevenueDate(Date date)
	{
		revenueDate = date;
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

    public boolean isTakeaway()
    {
        return takeaway;
    }
    public void setTakeaway(boolean takeaway)
    {
        this.takeaway = takeaway;
    }
    public List getVats()
    {
        return vats;
    }
    public void setVats(List vats)
    {
        this.vats = vats;
    }
}
