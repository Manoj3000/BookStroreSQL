package bookstore.services;

import java.util.List;

import bookstore.dao.BookDao;
import bookstore.entity.Book;
import bookstore.exception.BookStoreException;
import bookstore.interfaces.IBookDao;
import bookstore.interfaces.IBookService;
import bookstore.common.ErrorMessages;
import bookstore.common.UtilRegex;
import bookstore.util.UtilValidation;

public class BookServiceImpl implements IBookService {

	private IBookDao bookDao;
	private static IBookService bookService;
	
	public BookServiceImpl() {
		bookDao = new BookDao();
	}

	@Override
	public void addBook(Book book) throws BookStoreException {
		book.setBookId(getLastId());
		checkValidation(book);
		bookDao.addBook(book);
	}
	
	public static IBookService getObject() {
		if(bookService == null) {
			bookService = new BookServiceImpl();
		}
		return bookService;
	}

	
	private String getLastId() {
		String id = bookDao.getLastId();
		if(id != null) {
			id = id.substring(1);
			int newId = Integer.parseInt(id) + 1;
			String userId = "B" + Integer.toString(newId);
			return userId;
		}else {
			return "B101";
		}
	}

	@Override
	public void updateBookById(String id, Book book) throws BookStoreException {
		checkValidation(book);
		bookDao.updateBookById(id, book);
	}

	@Override
	public void deleteBookById(String id) {
		try {
			checkUserId(id);
			bookDao.deleteBookById(id);
		} catch (BookStoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Book searchBookById(String id) {
		try {
			checkUserId(id);
			Book book = bookDao.searchBookById(id);
			return book;
		} catch (BookStoreException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> books = bookDao.getAllBooks();
		return books;
	}

	@Override
	public int getSizeOfBooks() {
		int size = bookDao.getSizeOfBooks();
		return size;
	}

	@Override
	public void addBookQunatity(String bookId, int preQuantity, int newQuantity) {
		if(preQuantity <= 0) {
			preQuantity = 0;
		}
		newQuantity += preQuantity;
		bookDao.addBookQunatity(bookId, newQuantity);
	}

	private void checkUserId(String id) throws BookStoreException {
		if (id.isEmpty())
			throw new BookStoreException("Book ID" + ErrorMessages.FIELD_IS_EMPTY.getConstant());
		else if (!UtilValidation.isValid(UtilRegex.BOOKID, id))
			throw new BookStoreException(ErrorMessages.INVALID_DATA.getConstant() + "Book ID");
	}

	private void checkValidation(Book book) throws BookStoreException {

		if (book.getBookName().trim().isEmpty())
			throw new BookStoreException("Book Name" + ErrorMessages.FIELD_IS_EMPTY.getConstant());
		else if (!UtilValidation.isValid(UtilRegex.NAME, book.getBookName()))
			throw new BookStoreException(ErrorMessages.INVALID_DATA.getConstant() + "Book Name");

//		if (book.getBookId().trim().isEmpty())
//			throw new BookStoreException("Book ID" + ErrorMessages.FIELD_IS_EMPTY.getConstant());
//		else if (!UtilValidation.isValid(UtilRegex.BOOKID, book.getBookId()))
//			throw new BookStoreException(ErrorMessages.INVALID_DATA.getConstant() + "Book ID");

		if (book.getBookAuthor().trim().isEmpty())
			throw new BookStoreException("Book Author Name" + ErrorMessages.FIELD_IS_EMPTY.getConstant());
		else if (!UtilValidation.isValid(UtilRegex.NAME, book.getBookAuthor()))
			throw new BookStoreException(ErrorMessages.INVALID_DATA.getConstant() + "Book Author Name");

		if (book.getBookType().trim().isEmpty())
			throw new BookStoreException("Book Type" + ErrorMessages.FIELD_IS_EMPTY.getConstant());
		else if (!UtilValidation.isValid(UtilRegex.NAME, book.getBookType()))
			throw new BookStoreException(ErrorMessages.INVALID_DATA.getConstant() + "Book Type");

		if (book.getBookPrice().trim().isEmpty())
			throw new BookStoreException("Book Price" + ErrorMessages.FIELD_IS_EMPTY.getConstant());
		else if (!UtilValidation.isValid(UtilRegex.PRICE, book.getBookPrice()))
			throw new BookStoreException(ErrorMessages.INVALID_DATA.getConstant() + "Book Price");

		if (book.getBookDesc().trim().isEmpty())
			throw new BookStoreException("Book Description" + ErrorMessages.FIELD_IS_EMPTY.getConstant());
	}

}
