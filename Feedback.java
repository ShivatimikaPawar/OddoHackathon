package model;

public class Feedback {
    private int feedbackId;
    private int swapId;
    private int rating;       // 1 to 5 stars
    private String comment;   // optional user comment

    // Getters
    public int getFeedbackId() {
        return feedbackId;
    }

    public int getSwapId() {
        return swapId;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    // Setters
    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public void setSwapId(int swapId) {
        this.swapId = swapId;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}