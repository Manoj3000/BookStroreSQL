package bookstore.interfaces;

import java.util.List;

import bookstore.entity.Book;
import bookstore.entity.ManageBook;
import bookstore.exception.BookStoreException;


public interface IManageBook {
	void add(ManageBook manageBook, Book book) throws BookStoreException;
	void updateById(String id);
	void deleteById(String id);
	ManageBook searchById(String id);
	List<ManageBook> getAllData();
	void returnBook(String manageId, String bookId, int returnQuantity, String totalQuantity, String totalPrice);
}
