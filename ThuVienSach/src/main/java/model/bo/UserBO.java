package model.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Reader;
import model.bean.User;
import model.dao.UserDAO;

public class UserBO {
	UserDAO userDAO = new UserDAO();

	public User getAccount(String username, String password) throws ClassNotFoundException, SQLException {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		return userDAO.getUser(username, password);
	}
	
	public User findUser(Integer idUser) throws SQLException, ClassNotFoundException {
        return userDAO.findUser(idUser);
    }
    
    public int insertUser(User user) throws SQLException, ClassNotFoundException {
        return userDAO.insertUser(user);
    }
    
    public ArrayList<User> getAllUser() throws SQLException, ClassNotFoundException {
        return userDAO.getAllUser();
    }
    
    public int updateUser(User user) throws SQLException, ClassNotFoundException {
        return userDAO.updateUser(user);
    }

    public boolean deleteUser(int idUser) throws SQLException, ClassNotFoundException {
    	int result = userDAO.deleteUser(idUser);
		if (result != 0)
			return true;
		return false;
	}
}
