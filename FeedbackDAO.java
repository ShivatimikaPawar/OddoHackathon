package dao;

import model.Feedback;
import java.sql.*;
import java.util.*;

public class FeedbackDAO {

    public boolean submitFeedback(Feedback feedback) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO feedback (swap_id, rating, comment) VALUES (?, ?, ?)");
        ps.setInt(1, feedback.getSwapId());
        ps.setInt(2, feedback.getRating());
        ps.setString(3, feedback.getComment());
        return ps.executeUpdate() > 0;
    }

    public List<Feedback> getFeedbackForSwap(int swapId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM feedback WHERE swap_id = ?");
        ps.setInt(1, swapId);
        ResultSet rs = ps.executeQuery();
        List<Feedback> feedbackList = new ArrayList<>();
        while (rs.next()) {
            Feedback fb = new Feedback();
            fb.setFeedbackId(rs.getInt("feedback_id"));
            fb.setSwapId(rs.getInt("swap_id"));
            fb.setRating(rs.getInt("rating"));
            fb.setComment(rs.getString("comment"));
            feedbackList.add(fb);
        }
        return feedbackList;
    }
}