package fr.montagnesdor.restaurant;

import java.util.Locale;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface Constants
{
	public final static String DEFAULT_LOCALE_LANGUAGE = Locale.FRANCE.getLanguage();
	public final static String DEFAULT_LOCALE_DISPLAY_LANGUAGE = "Français";

	public final static String DEFAULT_LOCALE_COUNTRY = Locale.FRANCE.getCountry();
	public final static String DEFAULT_LOCALE_DISPLAY_COUNTRY = "France";
	
	public final static String DEFAULT_PICTURE_NAME = "default.jpg";
	public final static String DEFAULT_PICTURE_NAME_EXTENSION = "jpg";
}
