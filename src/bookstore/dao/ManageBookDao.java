package bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import bookstore.interfaces.IManageBookDao;
import bookstore.util.DBConnection;
import bookstore.entity.Book;
import bookstore.entity.ManageBook;

public class ManageBookDao implements IManageBookDao {

	private static Connection conn = null;

	public ManageBookDao() {
		conn = DBConnection.getConnection();
	}

	@Override
	public boolean add(ManageBook manageBook, Book book) {
		String add = "insert into tb_book_manage(manage_id, book_id, user_id, issue_date, retrun_date, quantity, total_price)"
				+ " values(?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = conn.prepareStatement(add);
			statement.setString(1, manageBook.getManageId());
			statement.setString(2, manageBook.getBookId());
			statement.setString(3, manageBook.getUserId());
			statement.setDate(4, Date.valueOf(manageBook.getDateOfIssue()));
			statement.setDate(5, Date.valueOf(manageBook.getDateOfReturn()));
			statement.setString(6, manageBook.getQuantity());
			statement.setString(7, manageBook.getTotalPrice());

			int res = statement.executeUpdate();
			if (res > 0) {
				System.out.println("Bought successfully!\nManage Id By System : " + manageBook.getManageId());

				String sql = "update tb_book set book_quantity=? where book_id=? ";
				int bookQuantity = book.getQuantity() - Integer.parseInt(manageBook.getQuantity());

				PreparedStatement statement1 = conn.prepareStatement(sql);
				statement1.setInt(1, bookQuantity);
				statement1.setString(2, book.getBookId());

				int res1 = statement1.executeUpdate();
				if (res1 > 0)
					System.out.println("Book Quantity Updated Successfully!");
				else
					System.out.println("Book Quantity Not Updated!!");

				return true;
			} else {
				System.out.println("Not Inserted!!");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void updateById(String id) {

	}

	@Override
	public void deleteById(String id) {

	}

	@Override
	public ManageBook searchById(String id) {
		String sql = "select tb_book_manage.*, tb_user.user_id as userId, tb_book.book_id as bookId " 
				+ "from tb_book_manage "
				+ "left join tb_user on tb_book_manage.user_id = tb_user.id "
				+ "left join tb_book on tb_book_manage.book_id = tb_book.id " 
				+ "where tb_book_manage.manage_id='" + id + "'";
		try {
			Statement statement = conn.createStatement();
			ResultSet res = statement.executeQuery(sql);

			while (res.next()) {
				ManageBook manageBook = new ManageBook();
				manageBook.setId(res.getString("id"));
				manageBook.setManageId(res.getString("manage_Id"));
				manageBook.setDateOfIssue(res.getDate("issue_date").toLocalDate());
				manageBook.setDateOfReturn(res.getDate("retrun_date").toLocalDate());
				manageBook.setQuantity(res.getString("quantity"));
				manageBook.setTotalPrice(res.getString("total_price"));
				manageBook.setUserId(res.getString("userId"));
				manageBook.setBookId(res.getString("bookId"));
				return manageBook;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ManageBook> getAllData() {
		String sql = "select tb_book_manage.*, tb_user.user_id as userId, tb_book.book_id as bookId " 
				+ "from tb_book_manage "
				+ "left join tb_user on tb_book_manage.user_id = tb_user.id "
				+ "left join tb_book on tb_book_manage.book_id = tb_book.id ";
		try {
			Statement statement = conn.createStatement();
			ResultSet res = statement.executeQuery(sql);

			List<ManageBook> manageBookList = new ArrayList<>();
			while (res.next()) {
				ManageBook manageBook = new ManageBook();
				manageBook.setId(res.getString(1));
				manageBook.setManageId(res.getString(2));
				manageBook.setUserId(res.getString(9));
				manageBook.setBookId(res.getString(10));
				manageBook.setDateOfIssue(res.getDate(5).toLocalDate());
				manageBook.setDateOfReturn(res.getDate(6).toLocalDate());
				manageBook.setQuantity(res.getString(7));
				manageBook.setTotalPrice(res.getString(8));
				manageBookList.add(manageBook);
			}
			return manageBookList;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getLastId() {
		String sql = "SELECT manage_id FROM tb_book_manage ORDER BY id DESC LIMIT 1";
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

	@Override
	public void returnBook(String manageId, int setQuantity, int setPrice) {
		String sql = "update tb_book_manage set quantity=?, total_price=? where manage_id=? ";

		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, setQuantity);
			statement.setInt(2, setPrice);
			statement.setString(3, manageId);

			int res = statement.executeUpdate();
			if (res > 0)
				System.out.println("Quantity Updated Successfully!");
			else
				System.out.println("Quantity Not Updated!!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
