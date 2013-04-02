package fr.montagnesdor.restaurant.services.menus;

import fr.montagnesdor.restaurant.services.menus.spi.DefaultMenusManager;


public class MenusManagerFactory
{
	public static final MenusManager getManager()
	{
		return DefaultMenusManager.getInstance();
	}
	

}

