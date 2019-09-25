package models;

public class User {
    private int id;
    private String full_name;
    private String position;
    private String role;
    private String department;
    private String user_post;

    public User( String full_name, String position,  String role, String department,
 String user_post) {
        this.full_name = full_name;
        this.position = position;
        this.role = role;
        this.department = department;
        this.user_post = user_post;
    }
    public int getId() { return id; }
    public String getFull_name() { return full_name; }
    public String getPosition() { return position; }
    public String getRole() { return role; }
    public String getDepartment() { return department; }
    public String getUser_post() { return user_post; }

    public void setId(int id) { this.id = id; }
    public void setFull_name(String full_name) { this.full_name = full_name; }
    public void setPosition(String position) { this.position = position; }
    public void setRole(String role) { this.role = role; }
    public void setDepartment(String department) { this.department = department; }
    public void setUser_post(String user_post) { this.user_post = user_post; }
}