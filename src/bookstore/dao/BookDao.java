package bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bookstore.interfaces.IBookDao;
import bookstore.util.DBConnection;
import bookstore.entity.Book;

public class BookDao implements IBookDao {

	private static Connection conn = null;

	public BookDao() {
		conn = DBConnection.getConnection();
	}

	@Override
	public void addBook(Book book) {
		String add = "insert into tb_book(book_id, book_name, auth_name, book_type, book_quantity, book_price, book_desc) values(?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = conn.prepareStatement(add);
			statement.setString(1, book.getBookId());
			statement.setString(2, book.getBookName());
			statement.setString(3, book.getBookAuthor());
			statement.setString(4, book.getBookType());
			statement.setInt(5, book.getQuantity());
			statement.setString(6, book.getBookPrice());
			statement.setString(7, book.getBookDesc());

			int res = statement.executeUpdate();
			if (res > 0)
				System.out.println("Book Inserted successfully!\nBook Id By System : " + book.getBookId());
			else
				System.out.println("Book Not Inserted!!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateBookById(String id, Book book) {

		String sql = "update tb_book set book_name=?, auth_name=?, book_type=?, book_quantity=? ,book_price=?, book_desc=? where id=?";

		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, book.getBookName());
			statement.setString(2, book.getBookAuthor());
			statement.setString(3, book.getBookType());
			statement.setInt(4, book.getQuantity());
			statement.setString(5, book.getBookPrice());
			statement.setString(6, book.getBookDesc());
			statement.setString(7, id);

			int res = statement.executeUpdate();
			if (res > 0)
				System.out.println("Book Updated Successfully!");
			else
				System.out.println("Book Not Updated!!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteBookById(String bookId) {
		String sql = "delete from tb_book where book_id=?";

		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, bookId);

			int res = statement.executeUpdate();
			if (res > 0)
				System.out.println("Book Deleted Successfully!");
			else
				System.out.println("Book Not Deleted!!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Book searchBookById(String book_id) {
		String sql = "select * from tb_book where book_id='" + book_id + "'";

		try {
			Statement statement = conn.createStatement();
			ResultSet res = statement.executeQuery(sql);

			while (res.next()) {
				Book book = new Book();
				book.setId(res.getString(1));
				book.setBookId(res.getString(2));
				book.setBookName(res.getString(3));
				book.setBookAuthor(res.getString(4));
				book.setBookType(res.getString(5));
				book.setQuantity(res.getInt(6));
				book.setBookPrice(res.getString(7));
				book.setBookDesc(res.getString(8));
				return book;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Book> getAllBooks() {
		String sql = "select * from tb_book";

		try {
			Statement statement = conn.createStatement();
			ResultSet res = statement.executeQuery(sql);

			List<Book> bookList = new ArrayList<Book>();

			while (res.next()) {
				Book book = new Book();
				book.setId(res.getString(1));
				book.setBookId(res.getString(2));
				book.setBookName(res.getString(3));
				book.setBookAuthor(res.getString(4));
				book.setBookType(res.getString(5));
				book.setQuantity(res.getInt(6));
				book.setBookPrice(res.getString(7));
				book.setBookDesc(res.getString(8));
				bookList.add(book);
			}
			return bookList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getSizeOfBooks() {
		try {
			Statement statement = conn.createStatement();
			ResultSet res = statement.executeQuery("SELECT COUNT(*) AS rowcount FROM tb_book");
			res.next();
			int count = res.getInt("rowcount");
			res.close();
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void addBookQunatity(String bookId, int newQuantity) {
		String sql = "update tb_book set book_quantity=? where book_id=? ";

		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, newQuantity);
			statement.setString(2, bookId);

			int res = statement.executeUpdate();
			if (res > 0)
				System.out.println("Quantity Updated Successfully!");
			else
				System.out.println("Quantity Not Updated!!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getLastId() {
		String sql = "SELECT book_id FROM tb_book ORDER BY id DESC LIMIT 1";
		try {
			Statement statement = conn.createStatement();
			ResultSet res = statement.executeQuery(sql);

			while (res.next()) {
				return res.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
