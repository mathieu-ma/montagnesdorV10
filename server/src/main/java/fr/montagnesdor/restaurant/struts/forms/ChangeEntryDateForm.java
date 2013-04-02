package fr.montagnesdor.restaurant.struts.forms;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public final class ChangeEntryDateForm extends ActionForm
{
	private String password = null;
	
	private String date = null;
	private String month = null;
	private String year = null;

	// --------------------------------------------------------- Public Methods
	/**
	 * Reset all properties to their default values.
	 *
	 * @param mapping The mapping used to select this instance
	 * @param request The servlet request we are processing
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.password = null;

		this.date = null;
		this.month = null;
		this.year = null;
	}

	/**
	 * Validate the properties that have been set from this HTTP request,
	 * and return an <code>ActionErrors</code> object that encapsulates any
	 * validation errors that have been found.  If no errors are found, return
	 * <code>null</code> or an <code>ActionErrors</code> object with no
	 * recorded error messages.
	 *
	 * @param mapping The mapping used to select this instance
	 * @param request The servlet request we are processing
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
	{
		//Pour l'affichage des mois
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		ArrayList dateForMonthList = new ArrayList(12);
		for(int i=0; i<12; i++)
		{
			calendar.set(Calendar.MONTH, i);
			dateForMonthList.add(i, calendar.getTime());		
		}
		Object attributesOut = dateForMonthList;
		//Attribut générique permettant d'être utilisé dans les différents jsp de redirection				
		request.setAttribute("attributesOut", attributesOut);

		ActionErrors errors = new ActionErrors();
		if ((password == null) || (password.length() < 1))
		{
			errors.add("error.password.required", new ActionError("error.password.required"));
		}

		boolean flagDate = true;
		if ((date == null) || (date.length() < 1))
		{
			errors.add("changeEntryDate.jsp.error.date.required", new ActionError("changeEntryDate.jsp.error.date.required"));
			flagDate = false;
		}
		else
		{
			int dateInt = Integer.parseInt(date);
			if (dateInt < 1 || dateInt > 31)
			{
				errors.add("changeEntryDate.jsp.error.date.impossible", new ActionError("changeEntryDate.jsp.error.date.impossible"));
				flagDate = false;
			}
		}

		if ((month == null) || (month.length() < 1))
		{
			errors.add("changeEntryDate.jsp.error.month.required", new ActionError("changeEntryDate.jsp.error.month.required"));
			flagDate = false;
		}
		else
		{
			int monthInt = Integer.parseInt(month);
			if (monthInt < 1 || monthInt > 12)
			{
				errors.add("changeEntryDate.jsp.error.month.impossible", new ActionError("changeEntryDate.jsp.error.month.impossible"));
				flagDate = false;
			}
		}

		if ((year == null) || (year.length() < 1))
		{
			errors.add("changeEntryDate.jsp.error.year.required", new ActionError("changeEntryDate.jsp.error.year.required"));
			flagDate = false;
		}
		else
		{
			int yearInt = Integer.parseInt(year);
			if (yearInt < 1900)
			{
				errors.add("changeEntryDate.jsp.error.year.impossible", new ActionError("changeEntryDate.jsp.error.year.impossible"));
				flagDate = false;
			}
		}

		if (flagDate && !isDateValid())
		{
			errors.add("changeEntryDate.jsp.error.date.incompatible", new ActionError("changeEntryDate.jsp.error.date.incompatible"));
		}

		return errors;
	}

	private boolean isDateValid()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, Integer.parseInt(date));
		cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		cal.set(Calendar.YEAR, Integer.parseInt(year));

		if (cal.get(Calendar.MONTH) != (Integer.parseInt(month) - 1))
			return false;
		else
			return true;
	}

	/**
	 * @return
	 */
	public String getDate()
	{
		return date;
	}

	/**
	 * @return
	 */
	public String getMonth()
	{
		return month;
	}

	/**
	 * @return
	 */
	public String getYear()
	{
		return year;
	}

	/**
	 * @param string
	 */
	public void setDate(String string)
	{
		date = string;
	}

	/**
	 * @param string
	 */
	public void setMonth(String string)
	{
		month = string;
	}

	/**
	 * @param string
	 */
	public void setYear(String string)
	{
		year = string;
	}

	/**
	 * @return
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param string
	 */
	public void setPassword(String string)
	{
		password = string;
	}

}
