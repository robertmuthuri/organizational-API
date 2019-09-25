package models;

import java.util.Objects;

public class User {
    private int id;
    private String full_name;
    private String position;
    private String role;
    private int department_id;

    public User( String full_name, String position,  String role, int department_id) {
        this.full_name = full_name;
        this.position = position;
        this.role = role;
        this.department_id = department_id;
    }
    public int getId() { return id; }
    public String getFull_name() { return full_name; }
    public String getPosition() { return position; }
    public String getRole() { return role; }
    public int getDepartment_id() { return department_id; }

    public void setId(int id) { this.id = id; }
    public void setFull_name(String full_name) { this.full_name = full_name; }
    public void setPosition(String position) { this.position = position; }
    public void setRole(String role) { this.role = role; }
    public void setDepartment_id(int department_id) { this.department_id = department_id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                Objects.equals(getFull_name(), user.getFull_name()) &&
                Objects.equals(getPosition(), user.getPosition()) &&
                Objects.equals(getRole(), user.getRole()) &&
                Objects.equals(getDepartment_id(), user.getDepartment_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFull_name(), getPosition(), getRole(), getDepartment_id());
    }
}