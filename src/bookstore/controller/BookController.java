package bookstore.controller;

import java.util.List;

import bookstore.entity.Book;
import bookstore.exception.BookStoreException;
import bookstore.interfaces.IBookService;
import bookstore.services.BookServiceImpl;
import bookstore.util.UtilScanner;

public class BookController {

	private static BookController bookController;
	
	Book book = new Book();
	IBookService bookService = BookServiceImpl.getObject();

	public static BookController getObject() {
		if(bookController == null) {
			bookController = new BookController();
		} 
		return bookController;
	}
	
	public void addBook() throws BookStoreException {

		book.setBookName(UtilScanner.getString("Book Name : "));
//		book.setBookId(UtilScanner.getString("Book ID : "));
		book.setBookAuthor(UtilScanner.getString("Book Author Name : "));
		book.setBookType(UtilScanner.getString("Book Type : "));
		book.setQuantity(UtilScanner.getInt("Book Quantity : "));
		book.setBookPrice(UtilScanner.getString("Book Price : "));
		book.setBookDesc(UtilScanner.getString("Book Description : "));

		bookService.addBook(book);
	}

	public void updateBookById() throws BookStoreException {
		String bookId = UtilScanner.getString("ID of the Book you want to Update : ");
		Book book = bookService.searchBookById(bookId);
		if (book != null) {
			boolean loop = true;
			while (loop) {
				int option = UtilScanner.getIntNoMsg(
						"\nEnter 1 to Update Name :"
						+ "\nEnter 2 to Update Author Name :"
						+ "\nEnter 3 to Type :"
						+ "\nEnter 4 to Quantity :"
						+ "\nEnter 5 to Update Price :"
						+ "\nEnter 6 to Update Description :"
						+ "\nEnter 0 to Exit");
				switch (option) {
				case 1:
					book.setBookName(UtilScanner.getString("Update Name : "));
					break;
				case 2:
					book.setBookAuthor(UtilScanner.getString("Updated Author Name : "));
					break;
				case 3:
					book.setBookType(UtilScanner.getString("Update Type : "));
					break;
				case 4:
					book.setQuantity(UtilScanner.getInt("Update Quantity : "));
					break;
				case 5:
					book.setBookPrice(UtilScanner.getString("Update Price : "));
					break;
				case 6:
					book.setBookDesc(UtilScanner.getString("Update Description : "));
					break;
				case 0:
					loop = false;
					System.out.println("Exit From Book Update");
					break;
				default:
					break;
				}
			}
			bookService.updateBookById(book.getId(), book);
		} else {
			System.out.println("Book Not Found");
		}
	}

	public void deleteBookById() {
		String bookId = UtilScanner.getString("ID of the Book you want to Delete : ");
		Book book = bookService.searchBookById(bookId);
		if (book != null) {
			bookService.deleteBookById(bookId);
		} else {
			System.out.println("Book Not Found");
		}
	}

	public void searchBookById() {
		String bookId = UtilScanner.getString("ID of the Book you want to Search : ");
		Book book = bookService.searchBookById(bookId);
		if (book != null) {
			System.out.println("Found " + book.toString());
		} else {
			System.out.println("Book Not Found");
		}
	}

	public void getAllBooks() {
		List<Book> books = bookService.getAllBooks();
		if (books != null) {
			for (Book book : books) {
				System.out.println(book.toString());
			}
		}else {
			System.out.println("Database is empty!");
		}
	}

	public void getSizeOfBooks() {
		int size = bookService.getSizeOfBooks();
		System.out.println("Total Books in Database : " + size);
	}

	public void addBookQuantity() {
		String bookId = UtilScanner.getString("Book ID : ");
		Book book = bookService.searchBookById(bookId);
		if (book != null) {
			int preQuantity = book.getQuantity();
			int newQuantity = UtilScanner.getInt("New Quantity : ");
			bookService.addBookQunatity(bookId, preQuantity, newQuantity);
		} else {
			System.out.println("Book Not Found");
		}
	}
}
