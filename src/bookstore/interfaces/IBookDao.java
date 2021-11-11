package bookstore.interfaces;

import java.util.List;

import bookstore.entity.Book;


public interface IBookDao {
	void addBook(Book book);
	void updateBookById(String id, Book book);
	void deleteBookById(String id);
	Book searchBookById(String id);
	List<Book> getAllBooks();
	int getSizeOfBooks();
	void addBookQunatity(String bookId, int newQuantity);
	String getLastId();
}
