package model;

public class User {
    private int userId;
    private String name;
    private String location;
    private String profilePhoto;
    private String availability;
    private boolean isPublic;
    private String role;
    private boolean banned;

    // 🔧 Getters
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public String getAvailability() {
        return availability;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public String getRole() {
        return role;
    }

    public boolean isBanned() {
        return banned;
    }

    // ✏️ Setters
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }
}