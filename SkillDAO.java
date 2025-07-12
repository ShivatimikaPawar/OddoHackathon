package dao;

import model.Skill;
import java.sql.*;
import java.util.*;

public class SkillDAO {

    public boolean addSkill(Skill skill) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(
            "INSERT INTO skills (user_id, name, type) VALUES (?, ?, ?)"
        );
        ps.setInt(1, skill.getUserId());
        ps.setString(2, skill.getName());
        ps.setString(3, skill.getType());
        return ps.executeUpdate() > 0;
    }

    public List<Skill> getSkillsByUser(int userId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM skills WHERE user_id = ?");
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        List<Skill> skills = new ArrayList<>();
        while (rs.next()) {
            Skill skill = new Skill();
            skill.setSkillId(rs.getInt("skill_id"));
            skill.setUserId(rs.getInt("user_id"));
            skill.setName(rs.getString("name"));
            skill.setType(rs.getString("type"));
            skills.add(skill);
        }
        return skills;
    }

    public boolean deleteSkill(int skillId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM skills WHERE skill_id = ?");
        ps.setInt(1, skillId);
        return ps.executeUpdate() > 0;
    }
}