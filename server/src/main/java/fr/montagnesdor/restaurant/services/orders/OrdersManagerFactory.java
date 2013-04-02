package fr.montagnesdor.restaurant.services.orders;

import fr.montagnesdor.restaurant.services.orders.spi.DefaultOrdersManager;

public class OrdersManagerFactory
{
	public static final OrdersManager getManager()
	{
		return DefaultOrdersManager.getInstance();
	}
	

}

