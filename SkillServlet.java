package controller;

import dao.SkillDAO;
import model.Skill;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/skill")
public class SkillServlet extends HttpServlet {

    // View skills for a user (optional)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));

        try {
            List<Skill> skills = new SkillDAO().getSkillsByUser(userId);
            request.setAttribute("skills", skills);
            request.getRequestDispatcher("jsp/skills.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500, "Database error");
        }
    }

    //Add or Delete a skill
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            SkillDAO dao = new SkillDAO();

            if ("add".equals(action)) {
                Skill skill = new Skill();
                skill.setUserId(Integer.parseInt(request.getParameter("userId")));
                skill.setName(request.getParameter("name"));
                skill.setType(request.getParameter("type")); // offered or wanted

                dao.addSkill(skill);
                response.sendRedirect("user?action=profile&userId=" + skill.getUserId());

            } else if ("delete".equals(action)) {
                int skillId = Integer.parseInt(request.getParameter("skillId"));
                dao.deleteSkill(skillId);
                int userId = Integer.parseInt(request.getParameter("userId"));
                response.sendRedirect("user?action=profile&userId=" + userId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500, "Database error");
        }
    }
}