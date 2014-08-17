package fr.montagnesdor.restaurant.services.users;

import fr.montagnesdor.restaurant.services.users.spi.DefaultUsersManager;

public class UsersManagerFactory
{
	public static final UsersManager getManager()
	{
		return DefaultUsersManager.getInstance();
	}
}

