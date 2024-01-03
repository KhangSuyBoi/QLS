package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Authors;
import model.bean.Book;
import model.bean.BookShelf;
import model.bean.Category;
import model.bean.Reader;
import model.bean.User;
import model.bean.User;
import model.bean.User;
import model.bo.BookBO;

public class UserDAO {
	Connection conn = null;
	Statement st = null;
	PreparedStatement preSt = null;

	public User getUser(String username, String password) throws ClassNotFoundException, SQLException {
		if (conn == null)
			conn = ConnectDatabase.getMySQLConnection();
		String sql = "Select * from user where username=? and password=?";

		PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
		pstm.setString(1, username);
		pstm.setString(2, password);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			Integer idUser = rs.getInt("idUser");
			String username1= rs.getString("username");
			String password1 = rs.getString("password");
			User user = new User();
			user.setIdUser(idUser);
			user.setUsername(username1);
			user.setPassword(password1);
			return user;
		}
		return null;
	}
	
	public User findUser(Integer idUser) throws SQLException, ClassNotFoundException{
		if(conn == null)
			conn = ConnectDatabase.getMySQLConnection();
		String sql = "Select * from user where idUser = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, idUser);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			Integer idUser1 = rs.getInt("idUser");
			String username= rs.getString("username");
			String password = rs.getString("password");
			User user = new User(idUser1, username, password);
			return user;
		}
		return null;
	}
	
	public int insertUser(User user) throws SQLException, ClassNotFoundException{
		if(conn == null)
			conn = ConnectDatabase.getMySQLConnection();
		try {
			st = conn.createStatement();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		String sql = "insert into user (username, password) values (?,?);";
		PreparedStatement pstm = conn.prepareStatement(sql);
		int result =0;
		pstm.setString(1, user.getUsername());
		pstm.setString(2, user.getPassword());
		result = pstm.executeUpdate();
		return result;
	}
	
	public int updateUser(User user) throws SQLException, ClassNotFoundException{
		int rs = 0;
		if(conn == null) {
			conn = ConnectDatabase.getMySQLConnection();
		String sql = "Update user set username =?, password =? where idUser=? ";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, user.getUsername());
		pstm.setString(2, user.getPassword());
		pstm.setInt(3, user.getIdUser());
		rs = pstm.executeUpdate();
		}
		return rs;
	}
	
	public int deleteUser(int idUser) throws SQLException, ClassNotFoundException{
		int result =0;
		if(conn == null)
			conn = ConnectDatabase.getMySQLConnection();
		String sql = "delete From user where idUser= ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, idUser);
		result = pstm.executeUpdate();
		return result;
	}
	
	public ArrayList<User> getAllUser() throws SQLException, ClassNotFoundException{
		if(conn == null)
		conn = ConnectDatabase.getMySQLConnection();
		ArrayList<User> list = new ArrayList<User>();
		String sql = "select * from user";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			int idUser = rs.getInt("idUser");
			String username = rs.getString("username");
			String password = rs.getString("password");
			User user = new User();
			user.setIdUser(idUser);
			user.setUsername(username);
			user.setPassword(password);
			list.add(user);
		}
		return list;
	}
	
	
	}