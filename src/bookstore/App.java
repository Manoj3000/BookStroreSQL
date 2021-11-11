package bookstore;

import bookstore.controller.BookController;
import bookstore.controller.ManageBookController;
import bookstore.controller.UserController;
import bookstore.exception.BookStoreException;
import bookstore.util.UtilScanner;

public class App {
	
	private UserController user = new UserController();
	private BookController book = null;
	private ManageBookController manageBook = null;
	
	private App() {
		user = new UserController();
		book = new BookController();
		manageBook = new ManageBookController();
	}
	
	public void userMenu() throws BookStoreException {

		boolean loop = true;
		while (loop) {
			int option = UtilScanner.getIntNoMsg("\nEnter 1 to Create User :"
					+ "\nEnter 2 to Update User :"
					+ "\nEnter 3 to Delete User :"
					+ "\nEnter 4 to Search User :"
					+ "\nEnter 5 to Get All Users :"
					+ "\nEnter 6 to Users Size :"
					+ "\nEnter to 0 Exit");
			switch (option) {
			case 1:
				user.addUser();
				break;
			case 2:
				user.updateById();
				break;
			case 3:
				user.deleteUser();
				break;
			case 4:
				user.searchById();
				break;
			case 5:
				user.getAllUser();
				break;
			case 6:
				user.getSizeOfUsers();
				break;
			case 0:
				loop = false;
				System.out.println("Exit - Users");
				break;
			default:
				System.out.println("Invalid Option");
				break;
			}
		}
	}

	public void bookMenu() throws BookStoreException {

		boolean loop = true;
		while (loop) {
			int option = UtilScanner.getIntNoMsg("\nEnter 1 to Create Book :\nEnter 2 to Update Book :"
					+ "\nEnter 3 to Delete Book :\nEnter 4 to Search Book :\nEnter 5 to Get All Books :"
					+ "\nEnter 6 to Books Size :\nEnter 7 to Add Book Quantity :\nEnter to 0 Exit");
			switch (option) {
			case 1:
				book.addBook();
				break;
			case 2:
				book.updateBookById();
				break;
			case 3:
				book.deleteBookById();
				break;
			case 4:
				book.searchBookById();
				break;
			case 5:
				book.getAllBooks();
				break;
			case 6:
				book.getSizeOfBooks();
				break;
			case 7:
				book.addBookQuantity();
				break;
			case 0:
				loop = false;
				System.out.println("Exit - Books");
				break;
			default:
				System.out.println("Invalid Option");
				break;
			}
		}
	}
	
	public void manageBookMenu() {
		
		boolean loop = true;
		while (loop) {
			int option = UtilScanner.getIntNoMsg("\nEnter 1 to Buy :\nEnter 2 to Return Book :\n"
					+ "Enter 3 to Update :\nEnter 4 to Delete :\nEnter 5 to Search :\n"
					+ "Enter 6 to Get All :\nEnter to 0 Exit");

			switch (option) {
			case 1:
				manageBook.add();
				break;
			case 2:
				manageBook.returnBook();
				break;
			case 3:
				manageBook.updateById();
				break;
			case 4:
				manageBook.deleteById();
				break;
			case 5:
				manageBook.searchById();
				break;
			case 6:
				manageBook.getAllData();
				break;
			case 0:
				loop = false;
				System.out.println("Exit - Manage Books");
				break;
			default:
				System.out.println("Invalid Option");
				break;
			}
		}
	}
	
	public static void main(String[] args) throws BookStoreException {
		App app = new App();
		
		boolean loop = true;
		while (loop) {
			int choice = UtilScanner.getIntNoMsg("\nEnter 1 For User :"
					+ "\nEnter 2 For Book :\n"
					+ "Enter 3 For Manage Book :\n"
					+ "Enter 0 For Exit :");
			switch (choice) {
			case 1:
				app.userMenu();
				break;
			case 2:
				app.bookMenu();
				break;
			case 3:
				app.manageBookMenu();
				break;
			case 0:
				System.out.println("Exit - Program");
				loop = false;
				break;
			}
		}
	}
}
