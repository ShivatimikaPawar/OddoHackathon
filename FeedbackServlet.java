package controller;

import dao.FeedbackDAO;
import model.Feedback;



import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/feedback")
public class FeedbackServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Feedback feedback = new Feedback();
            feedback.setSwapId(Integer.parseInt(request.getParameter("swapId")));
            feedback.setRating(Integer.parseInt(request.getParameter("rating")));
            feedback.setComment(request.getParameter("comment"));

            new FeedbackDAO().submitFeedback(feedback);
            response.sendRedirect("swap?userId=" + request.getParameter("userId"));

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500, "Database error");
        }
    }
}