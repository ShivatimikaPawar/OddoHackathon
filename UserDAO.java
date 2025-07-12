package dao;

import model.User;

import java.sql.*;
import java.util.*;

public class UserDAO {

	    public User getUserById(int userId) throws SQLException {
	        Connection conn = DBConnection.getConnection();
	        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE user_id = ?");
	        ps.setInt(1, userId);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            User user = new User();
	            user.setUserId(rs.getInt("user_id"));
	            user.setName(rs.getString("name"));
	            user.setLocation(rs.getString("location"));
	            user.setProfilePhoto(rs.getString("profile_photo"));
	            user.setAvailability(rs.getString("availability"));
	            user.setPublic(rs.getBoolean("is_public"));
	            user.setRole(rs.getString("role"));
	            user.setBanned(rs.getBoolean("banned"));
	            return user;
	        }
	        return null;
	    }

	    public boolean updateUserProfile(User user) throws SQLException {
	        Connection conn = DBConnection.getConnection();
	        PreparedStatement ps = conn.prepareStatement("UPDATE users SET name=?, location=?, profile_photo=?, availability=?, is_public=? WHERE user_id=?");
	        ps.setString(1, user.getName());
	        ps.setString(2, user.getLocation());
	        ps.setString(3, user.getProfilePhoto());
	        ps.setString(4, user.getAvailability());
	        ps.setBoolean(5, user.isPublic());
	        ps.setInt(6, user.getUserId());
	        return ps.executeUpdate() > 0;
	    }

	    public boolean banUser(int userId) throws SQLException {
	        Connection conn = DBConnection.getConnection();
	        PreparedStatement ps = conn.prepareStatement("UPDATE users SET banned = TRUE WHERE user_id = ?");
	        ps.setInt(1, userId);
	        return ps.executeUpdate() > 0;
	    }
	    public List<User> getUsersBySkill(String skillName) throws SQLException {
	        Connection conn = DBConnection.getConnection();
	        PreparedStatement ps = conn.prepareStatement( "SELECT DISTINCT u.* FROM users u " +"JOIN skills s ON u.user_id = s.user_id " +"WHERE s.name LIKE ? AND u.is_public = TRUE AND u.banned = FALSE");
	        ps.setString(1, "%" + skillName + "%");
	        ResultSet rs = ps.executeQuery();

	        List<User> users = new ArrayList<>();
	        while (rs.next()) {
	            User user = new User();
	            user.setUserId(rs.getInt("user_id"));
	            user.setName(rs.getString("name"));
	            user.setLocation(rs.getString("location"));
	            user.setProfilePhoto(rs.getString("profile_photo"));
	            user.setAvailability(rs.getString("availability"));
	            user.setPublic(rs.getBoolean("is_public"));
	            user.setRole(rs.getString("role"));
	            user.setBanned(rs.getBoolean("banned"));
	            users.add(user);
	        }
	        return users;
	    }
	}