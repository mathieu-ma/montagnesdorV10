package fr.montagnesdor.restaurant.services.users;

import java.util.List;

import fr.montagnesdor.restaurant.model.hibernate.User;
import fr.montagnesdor.restaurant.model.hibernate.UserRole;

public interface UsersManager 
{
	void saveOrUpdateUser(User user);
	User findUser(Long id);
	List<User> findUsers();
	List<User> searchUsers(String searchedText);
	void deleteUser(User user);
	
	List<UserRole> findUserRoles();
}
