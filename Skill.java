package model;

public class Skill {
    private int skillId;
    private int userId;
    private String name;
    private String type; // "offered" or "wanted"

    // Getters
    public int getSkillId() {
        return skillId;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    // Setters
    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }
}