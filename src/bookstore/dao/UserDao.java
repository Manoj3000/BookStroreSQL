package bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bookstore.entity.User;
import bookstore.interfaces.IUserDao;
import bookstore.util.DBConnection;

public class UserDao implements IUserDao {

	private static Connection conn = null;
	
	public UserDao() {
		conn = DBConnection.getConnection();
	}

	@Override
	public void addUser(User user) {
		String add = "insert into tb_user(user_id, user_name, user_mobile, user_email, user_address) values(?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = conn.prepareStatement(add);
			statement.setString(1, user.getUserId());
			statement.setString(2, user.getName());
			statement.setString(3, user.getMobile());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getAddress());

			int res = statement.executeUpdate();
			if (res > 0)
				System.out.println("User Inserted successfully!\nUser Id By System : " + user.getUserId());
			else
				System.out.println("User Not Inserted!!");
			
		} catch (SQLException e) {
			System.out.println("Error at creating User");
			e.printStackTrace();
		}
	}

	@Override
	public void updateUserById(String id, User user) {
		String sql = "update tb_user set user_name=?, user_mobile=?, user_email=?, user_address=? where id=?";

		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getName());
			statement.setString(2, user.getMobile());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getAddress());
			statement.setString(5, id);

			int res = statement.executeUpdate();
			if (res > 0)
				System.out.println("User Updated Successfully!");
			else
				System.out.println("User Not Updated!!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUserById(String userId) {
		String sql = "delete from tb_user where user_id=?";

		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, userId);

			int res = statement.executeUpdate();
			if (res > 0)
				System.out.println("User Deleted Successfully!");
			else
				System.out.println("User Not Deleted!!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User searchUserById(String userId) {
		String sql = "select * from tb_user where user_id='" + userId + "'";

		try {
			Statement statement = conn.createStatement();
			ResultSet res = statement.executeQuery(sql);

			while (res.next()) {
				User user = new User();
				user.setId(res.getString(1));
				user.setUserId(res.getString(2));
				user.setName(res.getString(3));
				user.setMobile(res.getString(4));
				user.setEmail(res.getString(5));
				user.setAddress(res.getString(6));
				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> getAllUser() {
		String sql = "select * from tb_user";

		try {
			Statement statement = conn.createStatement();
			ResultSet res = statement.executeQuery(sql);

			List<User> usersList = new ArrayList<User>();

			while (res.next()) {
				User user = new User();
				user.setId(res.getString(1));
				user.setUserId(res.getString(2));
				user.setName(res.getString(3));
				user.setMobile(res.getString(4));
				user.setEmail(res.getString(5));
				user.setAddress(res.getString(6));

				usersList.add(user);
			}
			return usersList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public int getSizeOfUsers() {
		try {
			Statement statement = conn.createStatement();
			ResultSet res = statement.executeQuery("SELECT COUNT(*) AS rowcount FROM tb_user");
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
	public String getLastId() {
		String sql = "SELECT user_id FROM tb_user ORDER BY id DESC LIMIT 1";
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
