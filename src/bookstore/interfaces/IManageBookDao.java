package bookstore.interfaces;

import java.util.List;

import bookstore.entity.Book;
import bookstore.entity.ManageBook;

public interface IManageBookDao {
	boolean add(ManageBook manageBook, Book book);
	void updateById(String id);
	void deleteById(String id);
	ManageBook searchById(String id);
	List<ManageBook> getAllData();
	String getLastId();
	void returnBook(String manageId, int setQuantity, int setPrice);
}
