package fr.montagnesdor.restaurant.services.authentication;

import java.util.List;

import fr.montagnesdor.restaurant.model.hibernate.User;

public interface UserManager
{
	public List getConnections(String startDate, String endDate, String LAN_CODE_ISO);
	
	public User getUserByLogin(String login);
	
	public List getUserLocalesLanguages(User user, String currentLanguage);
	
	public void changeUserPassword(User user, int levelPassword, String newPassword);
	
	public void changePrintLanguage(User user, String preferedPrintLanguage);
}

