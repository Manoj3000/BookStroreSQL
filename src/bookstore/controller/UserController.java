package bookstore.controller;

import java.util.List;

import bookstore.entity.User;
import bookstore.exception.BookStoreException;
import bookstore.interfaces.IUserService;
import bookstore.services.UserServiceImpl;
import bookstore.util.UtilScanner;

public class UserController {

	User user = null;
	IUserService userService = null;

	public UserController() {
		user = new User();
		userService = new UserServiceImpl();
	}
	
	public void addUser() throws BookStoreException {
//		user.setUserId(UtilScanner.getString("User ID : "));
		user.setName(UtilScanner.getString("User Name : "));
		user.setMobile(UtilScanner.getString("User Mobile Number: "));
		user.setEmail(UtilScanner.getString("User Email : "));
		user.setAddress(UtilScanner.getString("User Address : "));

		userService.addUser(user);
	}

	public void updateById() throws BookStoreException {
		String userId = UtilScanner.getString("ID of the user you want to Update : ");
		User user = userService.searchUserById(userId);
		if (user != null) {
			boolean loop = true;
			while (loop) {
				int option = UtilScanner.getIntNoMsg("\nEnter 1 to Update Name :"
						+ "\nEnter 2 to Update Mobile No :"
						+ "\nEnter 3 to Update Email :"
						+ "\nEnter 4 to Update Address :"
						+ "\nEnter 0 to Exit");
				switch (option) {
				case 1:
					user.setName(UtilScanner.getString("Update Name : "));
					break;
				case 2:
					user.setMobile(UtilScanner.getString("Updated Mobile Number : "));
					break;
				case 3:
					user.setEmail(UtilScanner.getString("Update Email : "));
					break;
				case 4:
					user.setAddress(UtilScanner.getString("Update Address : "));
					break;
				case 0:
					loop = false;
					break;
				default:
					break;
				}
			}
			userService.updateUserById(user.getId(), user);
		} else {
			System.out.println("User Not Found");
		}
	}

	public void deleteUser() {
		String userId = UtilScanner.getString("ID of the user you want to Delete : ");
		User user = userService.searchUserById(userId);
		if (user != null)
			userService.deleteUserById(userId);
		else
			System.out.println("User Not Found");
	}

	public void searchById() {
		String userId = UtilScanner.getString("ID of the user you want to Search : ");
		User user = userService.searchUserById(userId);
		if (user != null)
			System.out.println("Found " + user.toString());
		else
			System.out.println("User Not Found");
	}

	public void getAllUser() {
		List<User> users = userService.getAllUser();
		if(users != null) {
			users.forEach(user -> System.out.println(user.toString()));
		}else {
			System.out.println("Database is empty!");
		}
	}

	public void getSizeOfUsers() {
		int size = userService.getSizeOfUsers();
		System.out.println("Total Users in Database : " + size);
	}

}
