package bookstore.interfaces;

import java.util.List;

import bookstore.entity.Book;
import bookstore.exception.BookStoreException;

public interface IBookService {
	
	void addBook(Book book) throws BookStoreException;
	void updateBookById(String id, Book book) throws BookStoreException;
	void deleteBookById(String id);
	Book searchBookById(String id);
	List<Book> getAllBooks();
	int getSizeOfBooks();
	void addBookQunatity(String bookId, int preQuantity, int newQuantity);
}
