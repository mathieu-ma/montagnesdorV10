package fr.montagnesdor.restaurant.services.authentication;

import fr.montagnesdor.restaurant.services.authentication.spi.DefaultUserManager;
import fr.montagnesdor.restaurant.services.authentication.UserManager;

public class UserManagerFactory
{
	public static final UserManager getManager()
	{
		return DefaultUserManager.getInstance();
	}
	

}

