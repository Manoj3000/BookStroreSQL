package bookstore.services;

import java.util.List;

import bookstore.common.ErrorMessages;
import bookstore.common.UtilRegex;
import bookstore.controller.BookController;
import bookstore.dao.ManageBookDao;
import bookstore.entity.Book;
import bookstore.entity.ManageBook;
import bookstore.exception.BookStoreException;
import bookstore.interfaces.IBookService;
import bookstore.interfaces.IManageBook;
import bookstore.interfaces.IManageBookDao;
import bookstore.util.UtilValidation;

public class ManageBookServiceImpl implements IManageBook {

	private IManageBookDao manageBookDao = null;

	public ManageBookServiceImpl() {
		manageBookDao = new ManageBookDao();
	}

	@Override
	public void add(ManageBook manageBook, Book book) throws BookStoreException {
		manageBook.setManageId(getLastId());
		manageBookDao.add(manageBook, book);
	}

	private String getLastId() {
		String id = manageBookDao.getLastId();
		if (id != null) {
			id = id.substring(1);
			int newId = Integer.parseInt(id) + 1;
			String userId = "M" + Integer.toString(newId);
			return userId;
		} else {
			return "M101";
		}
	}

	@Override
	public void updateById(String id) {

	}

	@Override
	public void deleteById(String id) {

	}

	@Override
	public ManageBook searchById(String id) {
		try {
			checkUserId(id);
		} catch (BookStoreException e) {
			e.printStackTrace();
		}
		return manageBookDao.searchById(id);
	}

	@Override
	public List<ManageBook> getAllData() {
		return manageBookDao.getAllData();
	}

	private void checkUserId(String id) throws BookStoreException {
		if (id.isEmpty())
			throw new BookStoreException("Book ID" + ErrorMessages.FIELD_IS_EMPTY.getConstant());
		else if (!UtilValidation.isValid(UtilRegex.MANAGEID, id))
			throw new BookStoreException(ErrorMessages.INVALID_DATA.getConstant() + "Manage ID");
	}

	@Override
	public void returnBook(String manageId, String bookId, int returnQuantity, String totalQuantity, String totalPrice) {
		IBookService bookService = BookServiceImpl.getObject();
		Book book = bookService.searchBookById(bookId);
		bookService.addBookQunatity(bookId, book.getQuantity(), returnQuantity);
		int setQuantity = Integer.parseInt(totalQuantity) - returnQuantity;
		int setPrice = Integer.parseInt(totalPrice) - (returnQuantity * Integer.parseInt(book.getBookPrice()));
		manageBookDao.returnBook(manageId, setQuantity, setPrice);
	}
}
