package bookstore.interfaces;

import java.util.List;

import bookstore.entity.User;

public interface IUserDao {
	void addUser(User user);
	void deleteUserById(String id);
	User searchUserById(String id);
	void updateUserById(String id, User user);
	List<User> getAllUser();
	int getSizeOfUsers();
	String getLastId();
}
