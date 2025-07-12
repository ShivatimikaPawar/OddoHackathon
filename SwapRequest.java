package model;

import java.sql.Timestamp;

public class SwapRequest {
    private int requestId;
    private int fromUserId;
    private int toUserId;
    private String skill;
    private String status; // pending, accepted, rejected, cancelled
    private Timestamp timestamp;

    // Getters
    public int getRequestId() {
        return requestId;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public int getToUserId() {
        return toUserId;
    }

    public String getSkill() {
        return skill;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    // Setters
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}