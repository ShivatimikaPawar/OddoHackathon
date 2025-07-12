package controller;

import dao.UserDAO;
import dao.SkillDAO;
import model.User;
import model.Skill;



import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("search".equals(action)) {
                String skill = request.getParameter("skill");
                List<User> users = new UserDAO().getUsersBySkill(skill);
                request.setAttribute("users", users);
                request.getRequestDispatcher("jsp/search.jsp").forward(request, response);

            } else if ("profile".equals(action)) {
                int userId = Integer.parseInt(request.getParameter("userId"));
                User user = new UserDAO().getUserById(userId);
                List<Skill> skills = new SkillDAO().getSkillsByUser(userId);
                request.setAttribute("user", user);
                request.setAttribute("skills", skills);
                request.getRequestDispatcher("jsp/profile.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500, "Database error");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("updateProfile".equals(action)) {
                int userId = Integer.parseInt(request.getParameter("userId"));
                User user = new User();
                user.setUserId(userId);
                user.setName(request.getParameter("name"));
                user.setLocation(request.getParameter("location"));
                user.setProfilePhoto(request.getParameter("profilePhoto"));
                user.setAvailability(request.getParameter("availability"));
                user.setPublic("true".equals(request.getParameter("isPublic")));

                new UserDAO().updateUserProfile(user);
                response.sendRedirect("user?action=profile&userId=" + userId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500, "Database error");
        }
    }
}