package bookstore.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import bookstore.entity.Book;
import bookstore.entity.ManageBook;
import bookstore.entity.User;
import bookstore.exception.BookStoreException;
import bookstore.interfaces.IBookService;
import bookstore.interfaces.IManageBook;
import bookstore.interfaces.IUserService;
import bookstore.services.BookServiceImpl;
import bookstore.services.ManageBookServiceImpl;
import bookstore.services.UserServiceImpl;
import bookstore.util.UtilScanner;

public class ManageBookController {

	ManageBook manageBook = new ManageBook();
	IManageBook manageBookService = new ManageBookServiceImpl();
	IUserService userService = new UserServiceImpl();
	IBookService bookService = new BookServiceImpl();
//	LocalDate today = LocalDate.now();
	LocalDate today = LocalDate.of(2021, 11, 1);

	public void add() {
		String userId = UtilScanner.getString("User ID : ");
		User user = userService.searchUserById(userId);
		if (user != null) {
			String bookId = UtilScanner.getString("Book ID : ");
			Book book = bookService.searchBookById(bookId);
			if (book != null) {
				if (book.getQuantity() > 0) {
					manageBook.setUserId(user.getId());
					manageBook.setBookId(book.getId());
					// manageBook.setManageId(UtilScanner.getString("ID For Manage Book : "));

					System.out.println((book.getQuantity() > 1) ? book.getQuantity() + " Books Available!"
							: book.getQuantity() + " Book Available!");
					String bookQuantity = UtilScanner.getString("Quantity Of Books : ");

					while (Integer.parseInt(bookQuantity) <= 0 || Integer.parseInt(bookQuantity) > book.getQuantity()) {
						if (Integer.parseInt(bookQuantity) <= 0)
							System.out.println("Enter Valid Quantity!");
						else
							System.out.println((book.getQuantity() > 1) ? book.getQuantity() + " Books Available!"
									: book.getQuantity() + " Book Available!");
						bookQuantity = UtilScanner.getString("Quantity Of Books : ");
					}
					manageBook.setQuantity(bookQuantity);
					manageBook.setDateOfIssue(today);
					manageBook.setDateOfReturn(today.plus(1, ChronoUnit.WEEKS));
					int totalPrice = Integer.parseInt(book.getBookPrice()) * Integer.parseInt(manageBook.getQuantity());
					manageBook.setTotalPrice(String.valueOf(totalPrice));

					try {
						manageBookService.add(manageBook, book);
					} catch (BookStoreException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("Book not available right now!!");

				}
			} else {
				System.out.println("Book Not Found");
			}
		} else {
			System.out.println("User Not Found");
		}
	}

	public void returnBook() {
		String manageId = UtilScanner.getString("Manage ID : ");
		ManageBook manageBook = manageBookService.searchById(manageId);
		if (manageBook != null) {
			String userId = UtilScanner.getString("User ID : ");
			if (userId.equals(manageBook.getUserId())) {
				String bookId = UtilScanner.getString("Book ID : ");
				if (bookId.equals(manageBook.getBookId())) {
					long daysBetween = ChronoUnit.DAYS.between(today, manageBook.getDateOfReturn());
					if(daysBetween >= 0) {
						System.out.println((Integer.parseInt(manageBook.getQuantity()) > 1)
								? "You Took " + manageBook.getQuantity() + " Books"
								: "You Took " + manageBook.getQuantity() + " Book");
						int returnQuantity = UtilScanner.getInt("Return Quantity : ");
						while (returnQuantity > Integer.parseInt(manageBook.getQuantity()) || returnQuantity <= 0) {
							System.out.println("Invalid Quantity!");
							returnQuantity = UtilScanner.getInt("Return Quantity : ");
						}
						int quantity = returnQuantity - Integer.parseInt(manageBook.getQuantity());
						System.out.println(
								(quantity == 0) ? "You Return All Books" : "You Return " + returnQuantity + " Book");
						manageBookService.returnBook(manageId, bookId, returnQuantity, manageBook.getQuantity(),
								manageBook.getTotalPrice());
						
					}else {
						System.out.println("Your Time Is Over!!");
					}
				} else {
					System.out.println("Book Id Not Macthed");
				}
			} else {
				System.out.println("User Id Not Macthed");
			}
		} else {
			System.out.println("Manage Id Not F");
		}

	}

	public void updateById() {

	}

	public void deleteById() {

	}

	public void searchById() {
		String manageId = UtilScanner.getString("ID of the user you want to Search : ");
		ManageBook manageBook = manageBookService.searchById(manageId);
		if (manageBook != null)
			System.out.println("Found " + manageBook.toString());
		else
			System.out.println("Not Found");
	}

	public void getAllData() {
		List<ManageBook> manageBookList = manageBookService.getAllData();
		if (manageBookList != null) {
			for (ManageBook data : manageBookList) {
				System.out.println(data.toString());
			}
		} else {
			System.out.println("Database is empty!");
		}
	}
}
