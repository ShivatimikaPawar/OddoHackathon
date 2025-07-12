package controller;

import dao.SwapDAO;
import model.SwapRequest;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/swap")
public class SwapServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));

        try {
            List<SwapRequest> swaps = new SwapDAO().getSwapRequestsForUser(userId);
            request.setAttribute("swaps", swaps);
            request.getRequestDispatcher("jsp/swaps.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500, "Database error");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            SwapDAO dao = new SwapDAO();

            if ("send".equals(action)) {
                int fromUserId = Integer.parseInt(request.getParameter("fromUserId"));
                int toUserId = Integer.parseInt(request.getParameter("toUserId"));
                String skill = request.getParameter("skill");
                dao.sendRequest(fromUserId, toUserId, skill);
                response.sendRedirect("swap?userId=" + fromUserId);

            } else if ("update".equals(action)) {
                int requestId = Integer.parseInt(request.getParameter("requestId"));
                String status = request.getParameter("status");
                dao.updateRequestStatus(requestId, status);
                response.sendRedirect("swap?userId=" + request.getParameter("userId"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500, "Database error");
        }
    }
}