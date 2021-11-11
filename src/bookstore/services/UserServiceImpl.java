package bookstore.services;

import java.util.List;

import bookstore.dao.UserDao;
import bookstore.entity.User;
import bookstore.exception.BookStoreException;
import bookstore.interfaces.IUserDao;
import bookstore.interfaces.IUserService;
import bookstore.common.ErrorMessages;
import bookstore.common.UtilRegex;
import bookstore.util.UtilValidation;

public class UserServiceImpl implements IUserService {

	private IUserDao userDao = null;

	public UserServiceImpl() {
		userDao = new UserDao();
	}
	
	@Override
	public void addUser(User user) throws BookStoreException {
		user.setUserId(getLastId());
		checkValidation(user);
		userDao.addUser(user);
	}

	private String getLastId() {
		String id = userDao.getLastId();
		if(id != null) {
			id = id.substring(1);
			int newId = Integer.parseInt(id) + 1;
			String userId = "U" + Integer.toString(newId);
			return userId;
		}else {
			return "U101";
		}
	}

	@Override
	public void updateUserById(String id, User user) {
		try {
			checkValidation(user);
			userDao.updateUserById(id, user);
		} catch (BookStoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUserById(String userId) {
		try {
			checkUserId(userId);
			userDao.deleteUserById(userId);
		} catch (BookStoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User searchUserById(String userId) {
		try {
			checkUserId(userId);
			User user = userDao.searchUserById(userId);
			return user;
		} catch (BookStoreException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> getAllUser() {
		List<User> users = userDao.getAllUser();
		return users;
	}

	@Override
	public int getSizeOfUsers() {
		int size = userDao.getSizeOfUsers();
		return size;
	}

	private void checkUserId(String userId) throws BookStoreException {
		if (userId.isEmpty())
			throw new BookStoreException("ID" + ErrorMessages.FIELD_IS_EMPTY.getConstant());
		else if (!UtilValidation.isValid(UtilRegex.USERID, userId))
			throw new BookStoreException(ErrorMessages.INVALID_DATA.getConstant() + "ID");
	}

	private void checkValidation(User user) throws BookStoreException {

		if (user.getName().trim().isEmpty())
			throw new BookStoreException("Name" + ErrorMessages.FIELD_IS_EMPTY.getConstant());
		else if (!UtilValidation.isValid(UtilRegex.NAME, user.getName()))
			throw new BookStoreException(ErrorMessages.INVALID_DATA.getConstant() + "Name");

		if (user.getMobile().trim().isEmpty())
			throw new BookStoreException("Email" + ErrorMessages.FIELD_IS_EMPTY.getConstant());
		else if (!UtilValidation.isValid(UtilRegex.MOBILE, user.getMobile()))
			throw new BookStoreException(ErrorMessages.INVALID_DATA.getConstant() + "Mobile Number");

		if (user.getEmail().trim().isEmpty())
			throw new BookStoreException("Email" + ErrorMessages.FIELD_IS_EMPTY.getConstant());
		else if (!UtilValidation.isValid(UtilRegex.EMAIL, user.getEmail()))
			throw new BookStoreException(ErrorMessages.INVALID_DATA.getConstant() + "Email");

		if (user.getAddress().trim().isEmpty())
			throw new BookStoreException("Address" + ErrorMessages.FIELD_IS_EMPTY.getConstant());
		else if (!UtilValidation.isValid(UtilRegex.ADDRESS, user.getAddress()))
			throw new BookStoreException(ErrorMessages.INVALID_DATA.getConstant() + "Address");
	}
}
