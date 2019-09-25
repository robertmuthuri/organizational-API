package models;

import java.util.Objects;

public class Department {

    private int id;
    private String name;
    private String description;
    private int total_employees;
    private int user_id;

    public Department(String name, String description, int total_employees, String news_link,
                 int user_id) {
        this.name = name;
        this.description = description;
        this.total_employees = total_employees;
        this.user_id = user_id;
    }
    public int getId() { return id; }
    public String getDepartment() { return name; }
    public String getDescription() { return description; }
    public int getTotal_employees() { return total_employees; }
    public int getUser_post() { return user_id; }

    public void setId(int id) { this.id = id; }
    public void setDepartment_type(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setTotal_employees(int total_employees) { this.total_employees = total_employees; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return getId() == that.getId() &&
                getTotal_employees() == that.getTotal_employees() &&
                user_id == that.user_id &&
                Objects.equals(name, that.name) &&
                Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name, getDescription(), getTotal_employees(), user_id);
    }
}
