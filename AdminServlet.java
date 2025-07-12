package controller;
import dao.UserDAO;
import dao.SwapDAO;
import model.User;
import model.SwapRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<SwapRequest> swaps = new SwapDAO().getAllSwaps();
            request.setAttribute("swaps", swaps);
            request.getRequestDispatcher("jsp/admin.jsp").forward(request, response);
        } 
        catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500, "Database error");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("banUser".equals(action)) {
                int userId = Integer.parseInt(request.getParameter("userId"));
                new UserDAO().banUser(userId);
                response.sendRedirect("admin");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500, "Database error");
        }
    }
}