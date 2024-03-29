package model.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bean.Reader;
import model.bean.Ticket;
import model.bean.User;
import model.bo.ReaderBO;
import model.bo.TicketBO;
import model.bo.UserBO;

@WebServlet("/DeleteUser")
public class DeleteUserServlet extends HttpServlet {
    UserBO userBO = new UserBO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");

        if (user == null) {
            String errorString = "You need to log in first!";
            request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
            return;
        }

        int idUser = Integer.parseInt(request.getParameter("idUser"));
        try {
            boolean result= userBO.deleteUser(idUser);
            if (result) {
                String successString = "Deleted successfully";
                request.setAttribute("successString", successString);
            }
            else {
                String errorString = " Error";
                request.setAttribute("errorString", errorString);
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            String errorString = "Error: " + e.getMessage();
            request.setAttribute("errorString", errorString);
        }

        // Redirect back to the ManageReader servlet
        response.sendRedirect("ManageUser");
    	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

