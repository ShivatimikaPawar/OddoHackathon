package dao;
import model.SwapRequest;

import java.sql.*;
import java.util.*;

public class SwapDAO {
	    public boolean sendRequest(int fromUserId, int toUserId, String skill) throws SQLException {
	        Connection conn = DBConnection.getConnection();
	        PreparedStatement ps = conn.prepareStatement("INSERT INTO swap_requests (from_user_id, to_user_id, skill, status) VALUES (?, ?, ?, 'pending')");
	        ps.setInt(1, fromUserId);
	        ps.setInt(2, toUserId);
	        ps.setString(3, skill);
	        return ps.executeUpdate() > 0;
	    }

	    public boolean updateRequestStatus(int requestId, String status) throws SQLException {
	        Connection conn = DBConnection.getConnection();
	        PreparedStatement ps = conn.prepareStatement("UPDATE swap_requests SET status = ? WHERE request_id = ?");
	        ps.setString(1, status);
	        ps.setInt(2, requestId);
	        return ps.executeUpdate() > 0;
	    }

	    public List<SwapRequest> getSwapRequestsForUser(int userId) throws SQLException {
	        Connection conn = DBConnection.getConnection();
	        PreparedStatement ps = conn.prepareStatement("SELECT * FROM swap_requests WHERE from_user_id = ? OR to_user_id = ?");
	        ps.setInt(1, userId);
	        ps.setInt(2, userId);
	        ResultSet rs = ps.executeQuery();
	        List<SwapRequest> swaps = new ArrayList<>();
	        while (rs.next()) {
	            SwapRequest swap = new SwapRequest();
	            swap.setRequestId(rs.getInt("request_id"));
	            swap.setFromUserId(rs.getInt("from_user_id"));
	            swap.setToUserId(rs.getInt("to_user_id"));
	            swap.setSkill(rs.getString("skill"));
	            swap.setStatus(rs.getString("status"));
	            swap.setTimestamp(rs.getTimestamp("timestamp"));
	            swaps.add(swap);
	        }
	        return swaps;
	    }
	    
	    public List<SwapRequest> getAllSwaps() throws SQLException {
	        Connection conn = DBConnection.getConnection();
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM swap_requests ORDER BY timestamp DESC");

	        List<SwapRequest> swaps = new ArrayList<>();
	        while (rs.next()) {
	            SwapRequest swap = new SwapRequest();
	            swap.setRequestId(rs.getInt("request_id"));
	            swap.setFromUserId(rs.getInt("from_user_id"));
	            swap.setToUserId(rs.getInt("to_user_id"));
	            swap.setSkill(rs.getString("skill"));
	            swap.setStatus(rs.getString("status"));
	            swap.setTimestamp(rs.getTimestamp("timestamp"));
	            swaps.add(swap);
	        }
	        return swaps;
	    }
	}