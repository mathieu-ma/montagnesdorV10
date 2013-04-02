/*
 * Created on 29 avr. 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package fr.montagnesdor.restaurant.model.hibernate;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import fr.montagnesdor.util.FormatDecimal;

/**
 * @author Mathieu MA sous conrad
 *
 *	Cette classe est un mapping de la table t_diner_table.
 */
public class DinnerTable
{
    public static int specificRound = FormatDecimal.HALF_ROUND;  

	/*
	  tbd_id serial,
	  usr_id int8 NOT NULL DEFAULT 0,	  
	  roo_id int8 NOT NULL DEFAULT 0,
	  tbd_number varchar(5) NOT NULL,
	  tbd_customers_number int8 NOT NULL DEFAULT 0,
	  tbd_quantities_sum numeric(10,2) NOT NULL DEFAULT 0.00,
	  tbd_amounts_sum numeric(10,2) NOT NULL DEFAULT 0.00,
	  tbd_reduction_ratio numeric(10,2) NOT NULL DEFAULT 0.00,
	  tbd_amount_pay numeric(10,2) NOT NULL DEFAULT 0.00,
	  tbd_registration_date timestamp,
	  tbd_print_date timestamp,  
	  tbd_cashing_date timestamp,  
	  tbd_reduction_ratio_changed bool NOT NULL DEFAULT false,
	  dtb_takeaway bool NOT NULL DEFAULT false,
	 */

	private Long id;
	private User user;
	private long roo_id;
	private String number;
	private int customersNumber;
	private float quantitiesSum;
	private float amountsSum;
	private float reductionRatio;
	private float amountPay;
	private Date registrationDate;
	private Date printDate;
	private Date cashingDate;
	private boolean reductionRatioChanged = false;
	private boolean takeaway = false;

	private Set orders;

	//reduction n'est pas un champ dans la table
	private float reduction;
	private boolean allowModifyOrdersAfterPrinting = false;

	//Liste des valeurs des tva de cette table
	private Map vats;
	
	/**
	 * @return
	 */
	public float getAmountPay()
	{
		return amountPay = getAmountsSum()-getReduction();
	}

	/**
	 * @return
	 */
	public float getAmountsSum()
	{
		return amountsSum;
	}

	/**
	 * @return
	 */
	public Date getCashingDate()
	{
		return cashingDate;
	}

	/**
	 * @return
	 */
	public int getCustomersNumber()
	{
		return customersNumber;
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
	public String getNumber()
	{
		return number;
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
	public float getQuantitiesSum()
	{
		return quantitiesSum;
	}

	/**
	 * @return
	 */
	public float getReductionRatio()
	{
		return reductionRatio;
	}

	/**
	 * @return
	 */
	public boolean isReductionRatioChanged()
	{
		return reductionRatioChanged;
	}

	/**
	 * @return
	 */
	public Date getRegistrationDate()
	{
		return registrationDate;
	}

	/**
	 * @return
	 */
	public long getRoo_id()
	{
		return roo_id;
	}

	/**
	 * @param f
	 */
	public void setAmountPay(float f)
	{
		amountPay = f;
	}

	/**
	 * @param f
	 */
	public void setAmountsSum(float f)
	{
		amountsSum = f;
	}

	/**
	 * @param date
	 */
	public void setCashingDate(Date date)
	{
		cashingDate = date;
	}

	/**
	 * @param i
	 */
	public void setCustomersNumber(int i)
	{
		customersNumber = i;
	}

	/**
	 * @param string
	 */
	public void setNumber(String string)
	{
		number = string;
	}

	/**
	 * @param date
	 */
	public void setPrintDate(Date date)
	{
		printDate = date;
	}

	/**
	 * @param f
	 */
	public void setQuantitiesSum(float f)
	{
		quantitiesSum = f;
	}

	/**
	 * @param f
	 */
	public void setReductionRatio(float f)
	{
		reductionRatio = f;
	}

	/**
	 * @param b
	 */
	public void setReductionRatioChanged(boolean b)
	{
		reductionRatioChanged = b;
	}

	/**
	 * @param date
	 */
	public void setRegistrationDate(Date date)
	{
		registrationDate = date;
	}

	/**
	 * @param l
	 */
	public void setRoo_id(long l)
	{
		roo_id = l;
	}

	/**
	 * @param long1
	 */
	public void setId(Long long1)
	{
		id = long1;
	}

	/**
	 * @return
	 */
	public Set getOrders()
	{
		return orders;
	}

	/**
	 * @param set
	 */
	public void setOrders(Set list)
	{
		orders = list;
	}

	/**
	 * @return
	 */
	public User getUser()
	{
		return user;
	}

	/**
	 * @param user
	 */
	public void setUser(User user)
	{
		this.user = user;
	}

	/**
	 * @return
	 */
	public boolean isTakeaway()
	{
		return takeaway;
	}

	/**
	 * @param b
	 */
	public void setTakeaway(boolean b)
	{
		takeaway = b;
	}

	/**
	 * @return
	 */
	public float getReduction()
	{
		return reduction = FormatDecimal.specificRound(amountsSum*reductionRatio/100f, specificRound);
	}

	/**
	 * @param f
	 */
	public void setReduction(float f)
	{
		reduction = f;
	}

	public void clear()
	{
		id = null;
		user = null;
		roo_id = 0;
		number = null;
		customersNumber = 0;
		quantitiesSum = 0;
		amountsSum = 0;
		reductionRatio = 0;
		amountPay = 0;
		registrationDate = null;
		printDate = null;
		cashingDate = null;
		reductionRatioChanged = false;
		takeaway = false;
		orders = null;
		
		//reduction n'est pas un champ dans la table
		reduction = 0;
		allowModifyOrdersAfterPrinting = false;
	}
	/**
	 * @return
	 */
	public boolean isAllowModifyOrdersPrinting()
	{
		return allowModifyOrdersAfterPrinting;
	}

	/**
	 * @param b
	 */
	public void setAllowModifyOrdersPrinting(boolean b)
	{
		allowModifyOrdersAfterPrinting = b;
	}

    public Map getVats()
    {
        return vats;
    }
    public void setVats(Map vats)
    {
        this.vats = vats;
    }
    public int getSpecificRound()
    {
        return specificRound;
    }
    public static void setSpecificRound(int specificRound)
    {
        DinnerTable.specificRound = specificRound;
    }
}
