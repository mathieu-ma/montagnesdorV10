package fr.montagnesdor.restaurant.services.receipts;

import fr.montagnesdor.restaurant.services.receipts.spi.DefaultReceiptsManager;

public class ReceiptsManagerFactory
{
	public static final ReceiptsManager getManager()
	{
		return DefaultReceiptsManager.getInstance();
	}
	

}

