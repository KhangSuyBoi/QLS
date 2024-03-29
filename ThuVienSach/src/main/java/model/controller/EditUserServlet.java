package model.controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bean.User;
import model.bo.UserBO;

@WebServlet("/EditUser")
public class EditUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String errorString = null;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userSession");
		if (user == null) {
			errorString = "You need login first!";
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		} else {
			String idUserStr = request.getParameter("idUser");
			System.out.print("idUserStr=" + idUserStr);

        if (idUserStr != null && !idUserStr.isEmpty()) {
            try {
                Integer idUser = Integer.parseInt(request.getParameter("idUser"));
                UserBO userBO = new UserBO();
                User user1 = userBO.findUser(idUser);

                if (user1 != null) {
                    request.setAttribute("user", user1);
                    request.getRequestDispatcher("/edit_user.jsp").forward(request, response);
                } else {
                    response.getWriter().println("User not found!");
                }
            } catch (NumberFormatException | ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                response.getWriter().println("An error occurred while processing your request.");
            }
        } else {
            response.getWriter().println("Invalid user ID!");
        }
    }
}
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String idUserStr = request.getParameter("idUser");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("a"+ idUserStr);
        System.out.println(username);
        System.out.println(password);
        if (idUserStr != null && !idUserStr.isEmpty()) {
            try {
                int idUser = Integer.parseInt(idUserStr);
                User user = new User();
                user.setIdUser(idUser);
                user.setUsername(username);
                user.setPassword(password);

                UserBO userBO = new UserBO();
                int result = userBO.updateUser(user);

                if (result != 0) {
                    response.sendRedirect(request.getContextPath() + "/ManageUser");
                } else {
                    response.getWriter().println("Update failed!");
                }
            } catch (NumberFormatException | ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                response.getWriter().println("An error occurred while processing your request.");
            }
        } else {
            response.getWriter().println("Invalid user ID!");
        }
    }
}
