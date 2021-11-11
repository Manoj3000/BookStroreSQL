package bookstore.interfaces;

import java.util.List;

import bookstore.entity.User;
import bookstore.exception.BookStoreException;

public interface IUserService {
	void addUser(User user) throws BookStoreException;
	void deleteUserById(String userId);
	User searchUserById(String userId);
	void updateUserById(String userId, User user) throws BookStoreException;
	List<User> getAllUser();
	int getSizeOfUsers();
}
